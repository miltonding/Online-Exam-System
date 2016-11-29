package com.augmentun.exam.util;

import java.lang.reflect.Method;

import com.augmentun.exam.Constants;
import com.augmentun.exam.common.AppContext;

public class SessionUtil {

    private static Object getSessionInThread() {
        Object session = AppContext.getAppContext().getObject(Constants.APP_HTTP_SESSION);
        return session;
    }

    public static void addSession(String key, Object object) {
        Object session = getSessionInThread();
        //reflect
        try {
            Class<?>[] param = new Class[2];
            param[0] = String.class;
            param[1] = Object.class;
            //Get method
            Method method = session.getClass().getMethod("setAttribute", param);
            Object[] objects = new Object[2];
            objects[0] = key;
            objects[1] = object;
            //Invoke method
            method.invoke(session, objects);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public static Object getSession(String key) {
        Object object = null;
        Object session = getSessionInThread();
        //reflect
        try {
            Class<?> param = String.class;
            //Get method
            Method method = session.getClass().getMethod("getAttribute", param);
            //Invoke method
            object = method.invoke(session, key);
        } catch (Exception e) {
            throw new RuntimeException();
        }
        return object;
    }

    public static void removeSession(String key) {
        Object session = getSessionInThread();
        //reflect
        try {
            Class<?> param = String.class;
            //Get method
            Method method = session.getClass().getMethod("removeAttribute", param);
            //Invoke method
            method.invoke(session, key);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public static void invalidate() {
        Object session = getSessionInThread();
        if (session != null) {
            try {
                Method method = session.getClass().getMethod("invalidate");
                method.invoke(session);
            } catch (Exception e) {
                throw new RuntimeException();
            }
        }
    }

}
