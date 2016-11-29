package com.augmentun.exam.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
public class PageEncodingFilter implements Filter {

    private String encoding;

    public PageEncodingFilter() {
    }

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        request.setCharacterEncoding(encoding);
        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig fConfig) throws ServletException {
       if(fConfig.getInitParameter("encoding") == null || fConfig.getInitParameter("encoding") == "") {
           encoding = "UTF-8";
       } else {
           encoding = fConfig.getInitParameter("encoding");
       }
    }
}
