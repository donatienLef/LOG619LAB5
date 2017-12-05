package com.pro.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

public class LogFilter implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // TODO log connections attempt
        System.out.println("log !");
        filterChain.doFilter(servletRequest, servletResponse);
    }

    public void destroy() {

    }
}
