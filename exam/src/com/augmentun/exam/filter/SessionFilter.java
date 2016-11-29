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
import com.augmentun.exam.model.User;
import com.augmentun.exam.util.StringUtil;
public class SessionFilter implements Filter {

    public SessionFilter() {
    }

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;
        HttpSession session = request.getSession();
           String uri = request.getRequestURI();
           String path = request.getContextPath();
           uri = uri.substring(path.length() + 1);
           if (uri.endsWith("showLogin") || uri.endsWith("login")) {
               chain.doFilter(request, response);
           } else {
               User user = (User)session.getAttribute(Constants.USER);
               if (user == null){
                   //session fail
                   if (request.getMethod().toUpperCase().equals("GET")) {
                         String go= "";
                         String queryString = request.getQueryString();
                         if (StringUtil.validateParam(queryString)) {
                             go = uri + "@" + queryString;
                         }
                       response.sendRedirect(request.getContextPath() + "/page/user/showLogin?go=" + go);
                   } else {
                       response.sendRedirect(request.getContextPath() + "/page/user/showLogin");
                   }
               } else {
                   chain.doFilter(request, response);
               }
           }
    }

    @Override
    public void init(FilterConfig fConfig) throws ServletException {
    }

}
