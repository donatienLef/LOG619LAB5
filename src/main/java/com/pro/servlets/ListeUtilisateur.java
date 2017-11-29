package com.pro.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.pro.beans.Utilisateur;
import com.pro.dao.DAOFactory;
import com.pro.dao.UtilisateurDao;

public class ListeUtilisateur extends HttpServlet {
	private UtilisateurDao utilisateurDao;
	public static final String CONF_DAO_FACTORY = "daofactory";
	public static final String LISTE_UTILISATEUR = "/WEB-INF/ListeUtilisateur.jsp";
	public static final String SESSION_UTILISATEUR = "utilisateur";

	public void init() throws ServletException {
		this.utilisateurDao = ((DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getUtilisateurDao();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session.getAttribute(SESSION_UTILISATEUR) != null) {
			Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");
			if (utilisateur.getPoste().equals("Administrateur")) {
				List<Utilisateur> listeUtilisateur = utilisateurDao.getAllUtilisateur();
				request.setAttribute("ListeUtilisateur", listeUtilisateur);
				this.getServletContext().getRequestDispatcher(LISTE_UTILISATEUR).forward(request, response);
			} else {
				this.getServletContext().getRequestDispatcher("/Home").forward(request, response);
			}

		} else

		{
			response.sendRedirect(request.getContextPath() + "/Identification");
		}

	}

}
