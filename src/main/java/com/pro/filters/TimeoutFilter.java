package com.pro.filters;

import com.pro.dao.DAOFactory;
import com.pro.dao.intefaces.ConfigurationDao;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

import static com.pro.Lib.Ref.TIMEOUT;
import static com.pro.Lib.Ref.TIMEOUT_START;
import static com.pro.Lib.Ref.VALID;

public class TimeoutFilter implements Filter {


    private static final long MILLIS_TO_MIN = 1000 * 60;

    public void init(FilterConfig filterConfig) throws ServletException {
        
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpSession session = ((HttpServletRequest)servletRequest).getSession();

        if(session.getAttribute(TIMEOUT_START) != null) {
            Date date = (Date) session.getAttribute(TIMEOUT_START);

            Date now = new Date();
            Long time = date.getTime() + DAOFactory.getInstance().getConfigurationDao().readConfigurationDefault().getNbeMinutesEntreTentative() * MILLIS_TO_MIN;
            date.setTime(time);
            if(now.after(date)) {
                session.removeAttribute(TIMEOUT_START);
            } else {
                session.setAttribute(VALID, Boolean.FALSE);
            }
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    public void destroy() {

    }
}
