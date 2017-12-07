package com.pro.filters;

import com.pro.dao.DAOFactory;
import com.pro.dao.intefaces.ConfigurationDao;
import com.pro.dao.intefaces.UtilisateurDao;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.pro.Lib.Ref.*;

public class InitFilter implements Filter {
    private ConfigurationDao configurationDao;
    private UtilisateurDao utilisateurDao;

    public void init(FilterConfig filterConfig) throws ServletException {
        this.utilisateurDao = DAOFactory.getInstance().getUtilisateurDao();
        this.configurationDao = DAOFactory.getInstance().getConfigurationDao();
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession();

        if (session.getAttribute(TENTATIVE) == null) {
            session.setAttribute(TENTATIVE, 1);
        }

        if (session.getAttribute(VALID) == null) {
            session.setAttribute(VALID, Boolean.TRUE);
        }

        session.setAttribute(TENTATIVE_ERROR, null);
        session.setAttribute(CONFIGURATION, configurationDao.readConfigurationDefault());

        filterChain.doFilter(servletRequest, servletResponse);
    }

    public void destroy() {

    }
}
