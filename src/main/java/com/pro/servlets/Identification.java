package com.pro.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.pro.beans.Configurations;
import com.pro.beans.Utilisateur;
import com.pro.dao.ConfigurationDao;
import com.pro.dao.ConfigurationDaoImpl;
import com.pro.dao.DAOException;
import com.pro.dao.DAOFactory;
import com.pro.dao.UtilisateurDao;
import com.pro.forms.IdentificationForm;

public class Identification extends HttpServlet {
	public static final String CONF_DAO_FACTORY = "daofactory";

	public static final String ATT_FORM = "form";

	public static final String VUE = "/Identification.jsp";
	public static final String SUCCESS = "/Home";
	public static final String ECHEC = "/WEB-INF/Echec.jsp";

	public static final String SESSION_UTILISATEUR = "utilisateur";
	private UtilisateurDao utilisateurDao;
	private Configurations configuration = new Configurations();
	private ConfigurationDao configurationDao;

	public void init() throws ServletException {
		/* Récupération d'une instance de notre DAO Utilisateur */
		this.utilisateurDao = ((DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getUtilisateurDao();
		configurationDao = new ConfigurationDaoImpl(((DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)));
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.setAttribute("configuration", configuration);
		if(session.getAttribute("Tentative")==null){
			session.setAttribute("Tentative", 0);
			session.setAttribute("TentativeError", null);
		}
		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Configurations configuration = configurationDao.readConfigurationDefault();
		HttpSession session = request.getSession();
		if((configuration.getNbeTentativeMax() != 0) && ((Integer) session.getAttribute("Tentative") >= configuration.getNbeTentativeMax())){
			//alors on bloque
			session.setAttribute("TentativeError", "vous avez dépasser le nombre de tentative");
			request.getRequestDispatcher(VUE).forward(request, response);
		}
		else{
			// creation du formulaire
			IdentificationForm forms = new IdentificationForm();

			Utilisateur utilisateur = null;
			utilisateur = forms.trouver(request, utilisateurDao);
			request.setAttribute(ATT_FORM, forms);
			if (utilisateur != null) {
				session.setAttribute(SESSION_UTILISATEUR, utilisateur);
				response.sendRedirect(request.getContextPath() + "/Home");
			}
			else if(session.getAttribute("Tentative")!=null){
				session.setAttribute("Tentative", (Integer) session.getAttribute("Tentative")+1);
				request.getRequestDispatcher(VUE).forward(request, response);
			}
			else{
				response.sendRedirect(request.getContextPath() + "/Identification");
			}
			
		}
	}
}
