package com.pro.servlets;

import com.pro.beans.Utilisateur;
import com.pro.servlets.abstracts.AbstractServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.pro.Lib.Ref.ADMINISTRATEUR;
import static com.pro.Lib.Ref.UTILISATEUR;

public class Home extends AbstractServlet {

    public static final String CLIENT_VUE = "/WEB-INF/CompteUtilisateur.jsp";
    public static final String ADMIN_VUE = "/WEB-INF/CompteAdmin.jsp";


    public Home() {
        super(CLIENT_VUE);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Utilisateur utilisateur = (Utilisateur) session.getAttribute(UTILISATEUR);
        if(utilisateur.getPoste().equals(ADMINISTRATEUR)) {
            sendToVue(ADMIN_VUE, request, response);
        } else {
            super.doGet(request, response);
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect(request.getContextPath() + "/Identification");
    }

}
