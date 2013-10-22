package com.ibukanov.chat.controller;

import com.ibukanov.chat.form.UserForm;
import com.ibukanov.chat.model.User;
import com.ibukanov.chat.service.SecurityService;
import com.ibukanov.chat.service.UserService;
import com.ibukanov.chat.service.exceptions.DuplicateEntityException;
import com.ibukanov.chat.util.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

import static com.ibukanov.chat.util.MvcUtil.modelAndView;
import static com.ibukanov.chat.util.MvcUtil.modelAndViewWithForm;

@Slf4j
@Controller
public class UserCabinetController {

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @RequestMapping(value = Constants.USER_CABINET, method = RequestMethod.GET)
    public ModelAndView index(ModelMap model) {
        UserCabinetController.log.info(Constants.USER_CABINET);

        User user = userService.findByLogin(securityService.getUsername());
        putUserToModel(user, model);

        return modelAndViewWithForm(Constants.VIEW_USER_CABINET, UserForm.class);
    }

    @RequestMapping(value = Constants.USER_CABINET, method = RequestMethod.POST)
    public ModelAndView change(ModelMap model, @Valid UserForm form, BindingResult bindingResult) {
        UserCabinetController.log.info("change " + form);

        User user = userService.findByLogin(securityService.getUsername());
        try {
            userService.change(user, form.getNickname(), form.getPassword());
        } catch (DuplicateEntityException exception) {
            putUserToModel(user, model);
            return modelAndView(Constants.VIEW_USER_CABINET, bindingResult).addObject("duplicateNickname", true);
        }

        UserCabinetController.log.info("change " + form + " successfully");
        return new ModelAndView(Constants.VIEW_HOME, bindingResult.getModel());
    }

    private void putUserToModel(User user, ModelMap model) {
        model.put("fullName", user.getFullName());
        model.put("nickname", user.getNickname());
        model.put("email", user.getEmail());
        model.put("login", user.getLogin());
    }
}
