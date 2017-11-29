package com.pro.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.pro.beans.Utilisateur;
import com.pro.beans.Configurations;
import com.pro.dao.DAOException;
import com.pro.dao.DAOFactory;
import com.pro.dao.UtilisateurDao;
import com.pro.forms.IdentificationForm;

public class Home extends HttpServlet {
	public static final String CONF_DAO_FACTORY = "daofactory";

	public static final String ATT_FORM = "form";

	public static final String VUE = "/Identification";
	public static final String CLIENT = "/WEB-INF/CompteUtilisateur.jsp";
	public static final String ADMIN = "/WEB-INF/CompteAdmin.jsp";
	public static final String NOUVEAUUTILISATEUR = "/WEB-INF/NouveauUtilisateur.jsp";
	public static final String ADMINISTRATION = "/WEB-INF/Administration.jsp";
	public static final String ADMINISTRATIONSERVLET = "/Home/Administration";
	public static final String ECHEC = "/WEB-INF/Echec.jsp";
	public static final String SESSION_CONFIGURATION = "configuration";

	public static final String SESSION_UTILISATEUR = "utilisateur";
	private UtilisateurDao utilisateurDao;
	private Configurations Configuration = new Configurations();

	public void init() throws ServletException {
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// si user déjà en session
		String url = request.getRequestURL().toString();
		HttpSession session = request.getSession();
		if (session.getAttribute(SESSION_UTILISATEUR) != null) {
			Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");
			// Filtre selon le poste
			System.out.println(utilisateur.getMessage());
			request.setAttribute("Message", utilisateur.getMessage());
			if (utilisateur.getPoste().equals("Client résidentiel")) {
				System.out.println("client");
				this.getServletContext().getRequestDispatcher(CLIENT).forward(request, response);
			}
			if (utilisateur.getPoste().equals("Client affaire")) {
				this.getServletContext().getRequestDispatcher(CLIENT).forward(request, response);
			}
			if (utilisateur.getPoste().equals("Administrateur")) {

				this.getServletContext().getRequestDispatcher(ADMIN).forward(request, response);

			}
		} else {
			response.sendRedirect(request.getContextPath() + "/Identification");

		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect(request.getContextPath() + "/Identification");
	}

}
