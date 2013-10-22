package com.ibukanov.chat.controller;

import com.ibukanov.chat.util.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
public class LoginController {

    @RequestMapping(value = Constants.LOGIN, method = RequestMethod.GET)
    public String login() {
        return Constants.VIEW_LOGIN;
    }

    @RequestMapping(value = Constants.LOGOUT, method = RequestMethod.GET)
    public String logout() {
        return Constants.VIEW_LOGIN;
    }

    @RequestMapping(value = Constants.LOGIN_FAILED, method = RequestMethod.GET)
    public ModelAndView loginFailed() {
        LoginController.log.info("login failed");
        return new ModelAndView(Constants.VIEW_LOGIN, "userNotFound", true);
    }
}
