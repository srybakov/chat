package com.ibukanov.chat.controller;

import com.ibukanov.chat.form.RegisterForm;
import com.ibukanov.chat.model.User;
import com.ibukanov.chat.service.UserService;
import com.ibukanov.chat.service.exceptions.DuplicateEntityException;
import com.ibukanov.chat.util.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

import static com.ibukanov.chat.util.MvcUtil.modelAndViewWithForm;

@Slf4j
@Controller
public class RegisterController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = Constants.REGISTER, method = RequestMethod.GET)
	public ModelAndView index() {
        return modelAndViewWithForm(Constants.VIEW_REGISTER, RegisterForm.class);
	}

    @RequestMapping(value = Constants.REGISTER, method = RequestMethod.POST)
    public ModelAndView register(@Valid RegisterForm form) {
        RegisterController.log.info("register " + form);
        try {
            userService.save(User.of(form.getFullName(), form.getNickname(),
                    form.getLogin(), form.getEmail(), form.getPassword()));
        } catch (DuplicateEntityException exception) {
            return new ModelAndView(Constants.VIEW_REGISTER, "duplicateEmail", true);
        }
        RegisterController.log.info("register " + form + " successfully");
        return new ModelAndView(Constants.VIEW_HOME);
    }
}
