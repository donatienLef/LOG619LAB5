package com.pro.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.pro.beans.Utilisateur;
import com.pro.servlets.abstracts.AbstractServlet;

import static com.pro.Lib.Ref.*;

public class Home extends AbstractServlet {

	public static final String VUE = "/Identification";
	public static final String CLIENT_VUE = "/WEB-INF/CompteUtilisateur.jsp";
	public static final String ADMIN_VUE = "/WEB-INF/CompteAdmin.jsp";
	public static final String ECHEC = "/WEB-INF/Echec.jsp";


	public Home() {
		super(VUE);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// si user déjà en session
		String url = request.getRequestURL().toString();
		HttpSession session = request.getSession();
			Utilisateur utilisateur = (Utilisateur) session.getAttribute(UTILISATEUR);
			switch (utilisateur.getPoste()) {
				case CLIENT :
					sendToVue(CLIENT_VUE, request, response);
					break;
				case AFFAIRE:
					sendToVue(CLIENT_VUE, request, response);
					break;
				case ADMINISTRATEUR:
					sendToVue(ADMIN_VUE, request, response);
					break;
				default:
					sendToVue(ECHEC, request, response);
					break;
			}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect(request.getContextPath() + "/Identification");
	}

}
