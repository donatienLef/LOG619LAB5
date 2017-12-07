package com.pro.servlets.abstracts;

import com.pro.beans.Utilisateur;
import com.pro.dao.DAOFactory;
import com.pro.dao.intefaces.UtilisateurDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.pro.Lib.Ref.*;

public abstract class DoubleAuthServlet extends AbstractServlet{

    private static final String AUTH_VIEW = "/WEB-INF/doubleAuth.jsp";
    public DoubleAuthServlet(String view){
        super(view);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession sess = req.getSession();
        sess.setAttribute(REQUIRES_DOUBLE_AUTH, Boolean.TRUE);
        req.setAttribute("TARGET", req.getRequestURL());
        sendToVue(AUTH_VIEW, req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession sess = req.getSession();
        if(sess.getAttribute(UTILISATEUR) == null) {
            resp.sendRedirect("/Indentification");
            return;
        }else if(Boolean.TRUE.equals(sess.getAttribute(REQUIRES_DOUBLE_AUTH))){
            Utilisateur utilisateur = (Utilisateur) sess.getAttribute(UTILISATEUR);

            String password = req.getParameter(PASSWORD);
            String email = utilisateur.getEmail();

            UtilisateurDao utilsateurDao = DAOFactory.getInstance().getUtilisateurDao();
            Utilisateur utilFound = utilsateurDao.trouver(email, password);
            if(utilFound != null) {
                sess.setAttribute(REQUIRES_DOUBLE_AUTH, Boolean.FALSE);
                sendToVue(this.vue, req, resp);
            }else{
                doGet(req, resp);
            }
            return;
        }
        // double auth is done, you can process post request
    }
}
