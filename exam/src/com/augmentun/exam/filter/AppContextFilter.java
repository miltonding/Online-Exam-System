package com.augmentun.exam.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.augmentun.exam.Constants;
import com.augmentun.exam.common.AppContext;
import com.augmentun.exam.model.User;
public class AppContextFilter implements Filter {

    public AppContextFilter() {
    }

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest ServletRequest, ServletResponse ServletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)ServletRequest;
        HttpServletResponse response = (HttpServletResponse)ServletResponse;
        String uri = request.getRequestURI();
        String contextPath = request.getContextPath();
        uri = uri.substring(contextPath.length() + 1);
        if (uri.endsWith("showLogin")) {
            chain.doFilter(request, response);
            return;
        }
        AppContext appContext = AppContext.getAppContext();
        appContext.addObject(Constants.APP_CONTEXT_PATH, contextPath);

        HttpSession session = request.getSession();
        appContext.addObject(Constants.APP_HTTP_SESSION, session);

        User user = (User)session.getAttribute(Constants.USER);
        appContext.addObject(Constants.USER, user);
        try {
            chain.doFilter(request, response);
        } finally {
            appContext.clear();
        }
    }

    @Override
    public void init(FilterConfig fConfig) throws ServletException {
    }

}
