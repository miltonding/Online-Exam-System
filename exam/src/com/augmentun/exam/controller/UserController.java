package com.augmentun.exam.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import com.augmentun.exam.Constants;
import com.augmentun.exam.exception.ParamterException;
import com.augmentun.exam.exception.ServiceException;
import com.augmentun.exam.model.User;
import com.augmentun.exam.service.UserService;
import com.augmentun.exam.util.StringUtil;
@Controller
@RequestMapping("page/user")
public class UserController extends BaseController{
    @Autowired
    private UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/showLogin", method = RequestMethod.GET)
    public ModelAndView showLogin(String go) {
        if (StringUtil.validateParam(go)) {
            this.addSession("go", go);
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(Constants.USER_LOGIN_JSP);
        return modelAndView;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView login(String userName, String password) {
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        try {
            user = userService.login(userName, password);
            user.setPassword(null);
            this.addSession(Constants.USER, user);
            this.addSession(Constants.LANGUAGE, "zh");
            String go = (String)this.getSession("go");
            if (StringUtil.validateParam(go)) {
                this.removeSession("go");
                String[] split = go.split("@");
                String url = split[0] + "?" + split[1];
                modelAndView.setView(this.getRedirectView(url));
                return modelAndView;
            }
            modelAndView.setView(this.getRedirectView(Constants.QUESTION_LIST_PAGE));
        } catch (ParamterException e) {
            modelAndView.addObject(Constants.ERROR_MESSAGE, e.getErrorMessage());
            modelAndView.setViewName(Constants.USER_LOGIN_JSP);
        } catch (ServiceException e) {
            String TIP_MESSAGE = "[" +e.getCode() + "] " + e.getMessage();
            modelAndView.addObject(Constants.TIP_MESSAGE, TIP_MESSAGE);
            modelAndView.setViewName(Constants.USER_LOGIN_JSP);
        }
        return modelAndView;
    }

    @RequestMapping(value= "/logout", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView logout() {
        ModelAndView modelAndView = new ModelAndView();
        this.inValidateSession();
        modelAndView.setView(this.getRedirectView(Constants.USER_LOGIN_PAGE));
        return modelAndView;
    }

    @RequestMapping(value= "/changeLanguage", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView changeLanguage(@RequestParam(value = "language", defaultValue = "en") String language) {
        ModelAndView modelAndView = new ModelAndView();
        //change language
        if(language.equals("zh")){
            Locale locale = new Locale("zh", "CN");
            this.addSession(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME,locale);
            this.addSession(Constants.LANGUAGE, "zh");
        }
        else if(language.equals("en")){
            Locale locale = new Locale("en", "US");
            this.addSession(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME,locale);
            this.addSession(Constants.LANGUAGE, "en");
        }
        else {
            this.addSession(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME,LocaleContextHolder.getLocale());
            this.addSession(Constants.LANGUAGE, "en");
        }
        modelAndView.setView(this.getRedirectView(Constants.QUESTION_LIST_PAGE));
        return modelAndView;
    }

    @RequestMapping(value= "/viewProfile", method = RequestMethod.GET)
    public ModelAndView viewProfile() {
        ModelAndView modelAndView = new ModelAndView();
        return modelAndView;
    }
}
