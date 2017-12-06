package com.pro.servlets.abstracts;

import com.pro.beans.Configurations;
import com.pro.beans.Utilisateur;
import com.pro.dao.DAOFactory;
import com.pro.dao.intefaces.ConfigurationDao;
import com.pro.dao.intefaces.LogDao;
import com.pro.dao.intefaces.UtilisateurDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.pro.Lib.Ref.*;

public abstract class AbstractServlet extends HttpServlet {

    private static final String INVALID_VUE = "/invalid.jsp";

    protected String vue;

    protected UtilisateurDao utilisateurDao;
    protected Configurations configuration;
    protected ConfigurationDao configurationDao;
    protected LogDao logDao;

    public AbstractServlet(String vue){
        this.vue = vue;
    }

    @Override
    public void init() throws ServletException {
        super.init();
        utilisateurDao = DAOFactory.getInstance().getUtilisateurDao();
        configurationDao = DAOFactory.getInstance().getConfigurationDao();
        logDao = DAOFactory.getInstance().getLogDao();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        sendToVue(vue, req, resp);
    }

    protected void sendToVue(String view, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if(Boolean.FALSE.equals(session.getAttribute(VALID))) {
            req.getRequestDispatcher(INVALID_VUE).forward(req, resp);
            return;
        }
        Utilisateur utilisateur;
        // send var to render

        req.setAttribute(TENTATIVE, session.getAttribute(TENTATIVE));
        req.setAttribute(TENTATIVE_ERROR, session.getAttribute(TENTATIVE_ERROR));
        req.setAttribute(UTILISATEUR, session.getAttribute(UTILISATEUR));
        req.setAttribute(CONFIGURATION, session.getAttribute(CONFIGURATION));

        if(session.getAttribute(UTILISATEUR) != null) {
            utilisateur = (Utilisateur) session.getAttribute(UTILISATEUR);
            req.setAttribute(MESSAGE, utilisateur.getMessage());
        }

        req.getRequestDispatcher(view).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        sendToVue(vue, req, resp);
    }
}
