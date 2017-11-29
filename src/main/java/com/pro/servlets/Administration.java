package com.pro.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.pro.forms.ConfigurationForm;
import com.pro.forms.InscriptionForm;
import com.pro.forms.NouveauUtilisateurForm;
import com.pro.beans.Configurations;
import com.pro.beans.Utilisateur;
import com.pro.dao.ConfigurationDao;
import com.pro.dao.ConfigurationDaoImpl;
import com.pro.dao.DAOFactory;
import com.pro.dao.UtilisateurDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@SuppressWarnings("serial")

public class Administration extends HttpServlet {
	public static final String ATT_USER = "utilisateur";
	public static final String ATT_FORM = "form";
	public static final String VUE = "/WEB-INF/Configurations.jsp";
	public static final String CONF_DAO_FACTORY = "daofactory";
	public static final String ADMINISTRATION = "/WEB-INF/Administration.jsp";
	public static final String SESSION_CONFIGURATION = "configuration";

	public static final String SESSION_UTILISATEUR = "utilisateur";
	private ConfigurationDao configurationDao;

	private UtilisateurDao utilisateurDao;

	public void init() throws ServletException {
		/* Récupération d'une instance de notre DAO Utilisateur */
		this.utilisateurDao = ((DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getUtilisateurDao();
		configurationDao = new ConfigurationDaoImpl(((DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)));
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("bonjour");
		HttpSession session = request.getSession();
		if (session.getAttribute(SESSION_UTILISATEUR) != null) {
			Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");
			if (utilisateur.getPoste().equals("Administrateur")) {
				Configurations configurationDefault = configurationDao.readConfigurationDefault();
				session.setAttribute(SESSION_CONFIGURATION, configurationDefault);
				this.getServletContext().getRequestDispatcher(ADMINISTRATION).forward(request, response);
			} else {
				this.getServletContext().getRequestDispatcher("/Home").forward(request, response);
			}

		} else

		{
			response.sendRedirect(request.getContextPath() + "/Identification");
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ConfigurationForm formConfig = new ConfigurationForm();
		formConfig.setConfiguration(request, configurationDao);
		Configurations configurationDefault = configurationDao.readConfigurationDefault();
		System.out.println(configurationDefault.getBlocageIsPossible());
		HttpSession session = request.getSession();
		session.setAttribute(SESSION_CONFIGURATION, configurationDefault);
		this.getServletContext().getRequestDispatcher(ADMINISTRATION).forward(request, response);

		/*
		 * Préparation de l'objet formulaire NouveauUtilisateurForm form = new
		 * NouveauUtilisateurForm(); Utilisateur utilisateur =
		 * form.inscrireUtilisateur( request, utilisateurDao );
		 * if(!form.getErreurs().isEmpty()){ request.setAttribute("form", form);
		 * this.getServletContext().getRequestDispatcher(VUE).forward(request,
		 * response); }
		 * 
		 * request.setAttribute( ATT_FORM, form ); request.setAttribute(
		 * ATT_USER, utilisateur);
		 */
		//response.sendRedirect(request.getContextPath() + "/Home");
	}
}