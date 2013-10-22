package com.ibukanov.chat.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

import static com.ibukanov.chat.util.JavaUtils.invokeConstructorQuietly;
import static org.apache.commons.lang3.StringUtils.uncapitalize;

@NoArgsConstructor(access = AccessLevel.NONE)
public final class MvcUtil {

    public static ModelAndView modelAndViewWithForm(String viewName, Class<?> formClazz) {
        return new ModelAndView(viewName, getUncapitalizedSimpleClassName(formClazz),
                invokeConstructorQuietly(formClazz));
    }

    public static ModelAndView modelAndViewWithForm(String viewName, Object formObject, ModelMap map) {
        return modelAndViewWithForm(viewName, formObject).addAllObjects(map);
    }

    public static ModelAndView modelAndViewWithForm(String viewName, Object formObject) {
        return new ModelAndView(viewName, getUncapitalizedSimpleClassName(formObject.getClass()), formObject);
    }

    public static String getUncapitalizedSimpleClassName(Class<?> clazz) {
        return uncapitalize(clazz.getSimpleName());
    }

    public static ModelAndView modelAndView(String viewName, BindingResult bindingResult) {
        return modelAndView(viewName, bindingResult.getModel());
    }

    public static ModelAndView modelAndView(String viewName, Map<String, Object> model) {
        return new ModelAndView(viewName, model);
    }
}
