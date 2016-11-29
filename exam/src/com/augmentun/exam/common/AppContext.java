package com.augmentun.exam.common;

import java.util.HashMap;
import java.util.Map;

import com.augmentun.exam.Constants;
import com.augmentun.exam.model.User;
import com.augmentun.exam.util.StringUtil;

public class AppContext {
    //one TOMCAT thread corresponds to AppContext
    //private static Map<Thread, AppContext> appContextMap = new HashMap<Thread, AppContext>();
    private static ThreadLocal<AppContext> threadLocal = new ThreadLocal<AppContext>();

    public static Map<String, Object> objects = new HashMap<String, Object>();

    private AppContext() {}

    public static AppContext getAppContext() {
        AppContext appContext = threadLocal.get();
        if (appContext == null) {
            threadLocal.set(new AppContext());
        }
        return threadLocal.get();
    }

    public Map<String, Object> getObjects() {
        return objects;
    }

    public void addObject(String key, Object object) {
        objects.put(key, object);
    }

    public Object getObject(String key) {
        return objects.get(key);
    }

    public void clear() {
        objects.clear();
    }

    public User getUser() {
        User user = (User)objects.get(Constants.USER);
        return user;
    }

    public int getUserId() {
        int userId = 0;
        User user = (User)objects.get(Constants.USER);
        if (user != null) {
            userId = user.getId();
        }
        return userId;
    }

    public String getUserName() {
        String userName = "";
        User user = (User)objects.get(Constants.USER);
        if (user != null) {
            userName = user.getUserName();
        }
        return userName;
    }

    public void setContextPath(String contextPath) {
        if (!StringUtil.validateParam(contextPath)) {
            objects.put(Constants.APP_CONTEXT_PATH, contextPath);
        }
    }

    public static String getContextPath() {
        String contextPath = "";
        if (objects.get(Constants.APP_CONTEXT_PATH) != null) {
            contextPath = (String)objects.get(Constants.APP_CONTEXT_PATH);
        }
        return contextPath;
    }
}
