package com.pro.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.pro.forms.InscriptionForm;
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

public class Inscription extends HttpServlet {
	public static final String ATT_USER = "utilisateur";
    public static final String ATT_FORM = "form";
    public static final String VUE = "/Inscription.jsp";
	public static final String CONF_DAO_FACTORY = "daofactory";
	private ConfigurationDao configurationDao;
    
    private UtilisateurDao utilisateurDao;

	public void init() throws ServletException {
		/* Récupération d'une instance de notre DAO Utilisateur */
		this.utilisateurDao = ((DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getUtilisateurDao();
		configurationDao = new ConfigurationDaoImpl(((DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)));

	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* Affichage de la page d'inscription */
		//on récupère la configuration
		Configurations configurationDefault = configurationDao.readConfigurationDefault();
		HttpSession session = request.getSession();
		session.setAttribute("configuration", configurationDefault);		
		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
	}

	public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
        InscriptionForm form = new InscriptionForm();
        Configurations configurationDefault = configurationDao.readConfigurationDefault();

        Utilisateur utilisateur = form.inscrireUtilisateur( request, utilisateurDao, configurationDefault );
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