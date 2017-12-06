package com.pro.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.pro.Lib.Ref;
import com.pro.beans.Utilisateur;
import com.pro.dao.DAOFactory;
import com.pro.dao.intefaces.UtilisateurDao;
import com.pro.servlets.abstracts.AbstractServlet;

import static com.pro.Lib.Ref.*;

public class ListeUtilisateur extends AbstractServlet {

	private static final String VUE = "/WEB-INF/ListeUtilisateur.jsp";
	private static final String HOME = "/home";

	public ListeUtilisateur() {
		super(VUE);
	}


	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
			Utilisateur utilisateur = (Utilisateur) session.getAttribute(UTILISATEUR);
		List<Utilisateur> listeUtilisateur = new ArrayList<>();
		switch (utilisateur.getPoste()){
				case ADMINISTRATEUR:
					listeUtilisateur = utilisateurDao.getAllUtilisateur("%");
					break;
				case ADM_AFFAIRE:
					listeUtilisateur = utilisateurDao.getAllUtilisateur(AFFAIRE);
					break;
				case ADM_RESIDENTIEL:
					listeUtilisateur = utilisateurDao.getAllUtilisateur(RESIDENTIEL);
					break;
				default:

			}
		request.setAttribute(Ref.LISTE_UTILISATEUR, listeUtilisateur);
		sendToVue(VUE, request, response);
	}

}
