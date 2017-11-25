package com.pro.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.pro.forms.InscriptionForm;
import com.pro.forms.NouveauUtilisateurForm;
import com.pro.beans.Configurations;
import com.pro.beans.Utilisateur;
import com.pro.dao.DAOFactory;
import com.pro.dao.UtilisateurDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@SuppressWarnings("serial")

public class NouveauUtilisateur extends HttpServlet {
	public static final String ATT_USER = "utilisateur";
    public static final String ATT_FORM = "form";
    public static final String VUE = "/WEB-INF/NouveauUtilisateur.jsp";
	public static final String CONF_DAO_FACTORY = "daofactory";
	public static final String NOUVEAU_UTILISATEUR = "/WEB-INF/NouveauUtilisateur.jsp";
	public static final String SESSION_CONFIGURATION = "configuration";

	public static final String SESSION_UTILISATEUR = "utilisateur";
	private Configurations Configuration = new Configurations();

    
    private UtilisateurDao utilisateurDao;

	public void init() throws ServletException {
		/* Récupération d'une instance de notre DAO Utilisateur */
		this.utilisateurDao = ((DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getUtilisateurDao();

	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* Affichage de la page d'inscription */
		HttpSession session = request.getSession();
		if (session.getAttribute(SESSION_UTILISATEUR) != null) {
			Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");
			if (utilisateur.getPoste().equals("Administrateur")) {
				session.setAttribute(SESSION_CONFIGURATION, Configuration);
				this.getServletContext().getRequestDispatcher(NOUVEAU_UTILISATEUR).forward(request, response);
			} else {
				this.getServletContext().getRequestDispatcher("/Home").forward(request, response);
			}

		} else

		{
			response.sendRedirect(request.getContextPath() + "/Identification");
		}
	}

	public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
        /* Préparation de l'objet formulaire */
		NouveauUtilisateurForm form = new NouveauUtilisateurForm();
        
        /* Appel au traitement et à la validation de la requête, et récupération du bean en résultant */
        Utilisateur utilisateur = form.inscrireUtilisateur( request, utilisateurDao );
        if(!form.getErreurs().isEmpty()){
        	request.setAttribute("form", form);
    		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
        }
                
        /* Stockage du formulaire et du bean dans l'objet request */
        request.setAttribute( ATT_FORM, form );
        request.setAttribute( ATT_USER, utilisateur);
        
		response.sendRedirect(request.getContextPath() + "/Home");
	}
}