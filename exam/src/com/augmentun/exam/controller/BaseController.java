package com.augmentun.exam.controller;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.augmentun.exam.common.AppContext;
import com.augmentun.exam.model.User;
import com.augmentun.exam.util.SessionUtil;
public class BaseController {
    private final Logger logger = Logger.getLogger(BaseController.class);

    @ExceptionHandler(Exception.class)
    public ModelAndView handleException(Exception e) {
        logger.error(e.getMessage(), e);
        ModelAndView modelAndView = new ModelAndView(this.getRedirectView("static/500.html"));
        return modelAndView;
    }

    public User getUser() {
        return AppContext.getAppContext().getUser();
    }

    public String getUserName() {
        String userName = "";
        User user = getUser();
        if (user != null) {
            userName = user.getUserName();
        }
        return userName;
    }

    public int getUserId() {
        int id = 0;
        User user = getUser();
        if (user != null) {
            id = user.getId();
        }
        return id;
    }

    protected RedirectView getRedirectView(String path) {
        return new RedirectView(AppContext.getContextPath() + "/" + path);
    }

    protected void addSession(String key, Object object) {
        SessionUtil.addSession(key, object);
    }

    protected Object getSession(String key) {
        return SessionUtil.getSession(key);
    }

    protected void removeSession(String key) {
        SessionUtil.removeSession(key);
    }

    protected void inValidateSession() {
        SessionUtil.invalidate();
    }
}
