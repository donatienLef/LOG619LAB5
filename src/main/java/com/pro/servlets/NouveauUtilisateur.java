package com.pro.servlets;

import java.io.IOException;

import com.pro.forms.NouveauUtilisateurForm;
import com.pro.beans.Configurations;
import com.pro.beans.Utilisateur;
import com.pro.dao.DAOFactory;
import com.pro.dao.intefaces.UtilisateurDao;
import com.pro.servlets.abstracts.AbstractServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static com.pro.Lib.Ref.ADMINISTRATEUR;
import static com.pro.Lib.Ref.UTILISATEUR;

@SuppressWarnings("serial")

public class NouveauUtilisateur extends AbstractServlet {
	public static final String ATT_USER = "utilisateur";
    public static final String ATT_FORM = "form";
    public static final String VUE = "/WEB-INF/NouveauUtilisateur.jsp";
	public static final String NOUVEAU_UTILISATEUR = "/WEB-INF/NouveauUtilisateur.jsp";
	public static final String HOME_VUE = "/Home";

	public NouveauUtilisateur() {
		super(VUE);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* Affichage de la page d'inscription */
		HttpSession session = request.getSession();
		Utilisateur utilisateur = (Utilisateur) session.getAttribute(UTILISATEUR);
		if (utilisateur.getPoste().equals(ADMINISTRATEUR)) {
			sendToVue(NOUVEAU_UTILISATEUR, request, response);
		} else {
			sendToVue(HOME_VUE, request, response);
		}
	}

	public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
        /* Préparation de l'objet formulaire */
		NouveauUtilisateurForm form = new NouveauUtilisateurForm();
        
        /* Appel au traitement et à la validation de la requête, et récupération du bean en résultant */
        Utilisateur utilisateur = form.inscrireUtilisateur( request, utilisateurDao );
        if(!form.getErreurs().isEmpty()){
        	request.setAttribute(ATT_FORM, form);
    		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
        }
                
        /* Stockage du formulaire et du bean dans l'objet request */
        request.setAttribute( ATT_FORM, form );
        request.setAttribute( ATT_USER, utilisateur);
        
		response.sendRedirect(request.getContextPath() + HOME_VUE);
	}
}