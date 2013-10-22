package com.ibukanov.chat.controller;

import com.ibukanov.chat.model.Record;
import com.ibukanov.chat.service.RecordService;
import com.ibukanov.chat.service.SecurityService;
import com.ibukanov.chat.service.UserService;
import com.ibukanov.chat.util.Constants;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;

import static org.joda.time.DateTime.now;

@Slf4j
@Controller
public class HomeController {

    @Autowired
    private RecordService recordService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserService userService;

	@RequestMapping(value = Constants.HOME, method = RequestMethod.GET)
	public String home() {
		HomeController.log.info("Welcome to chat!");
        return Constants.VIEW_HOME;
	}

    @RequestMapping(value = "/ajax/{count}", method = RequestMethod.GET)
    public @ResponseBody
	void updateChat(@PathVariable("count") Integer count, HttpServletResponse resp) throws IOException {
		OutputStream respOut = resp.getOutputStream();
		resp.setContentType("text/html");
		resp.setCharacterEncoding("UTF-8");
		respOut.write(convertRecordsToChatFormat(recordService.getLastRecords(count)).getBytes("UTF-8"));
	}

    @RequestMapping(value = "/save-record/", method = RequestMethod.POST)
    public String saveRecord(String message) {
		HomeController.log.info("saveRecord " + message);

		Record record = Record.of(userService.findByLogin
                (securityService.getUsername()), message);
        recordService.save(record);

        HomeController.log.info("saveRecord " + message + " successfully");
        return "redirect:/";
    }

    private static String convertRecordsToChatFormat(List<Record> records) {
        StringBuilder chatRecords = new StringBuilder();

        for (Record record : records) {
            DateTime recordDateTime = new DateTime(record.getDate().getTime());
            Hours hours = Hours.hoursBetween(recordDateTime , now());
            if (hours.getHours() > 0) {
                Years years = Years.yearsBetween(recordDateTime , now());
                MonthDay day = MonthDay.fromDateFields(new Date(now().getMillis() - recordDateTime.getMillis()));
                int hour = Hours.hoursBetween(recordDateTime, now()).getHours() % 24;
                if (years.getYears() == 1) {
                    chatRecords.append(" 1 year ");
                }else if(years.getYears() > 1){
                    chatRecords.append(years.getYears()).append(" years ");
                }
                if (day.getMonthOfYear() == 2) {
                    chatRecords.append(" 1 month ");
                } else if (day.getMonthOfYear() > 2) {
                    chatRecords.append(day.getMonthOfYear()).append(" months ");
                }
                if (day.getDayOfMonth() > 1) {
                    chatRecords.append(" 1 day ");
                } else if (day.getDayOfMonth() > 2) {
                    chatRecords.append(day.getDayOfMonth()).append(" days ");
                }
                if (hour == 1) {
                    chatRecords.append(" 1 hour ");
                } else if (hour > 1) {
                    chatRecords.append(hour).append(" hours ");
                }
                chatRecords.append("old");
            } else {
                chatRecords.append("now");
            }

            chatRecords.append(" ( ").append(record.getUser().getNickname()).append(" ) : ");
			chatRecords.append(record.getMessage());

			chatRecords.append("</br>");
        }

        return chatRecords.toString();
    }
}
