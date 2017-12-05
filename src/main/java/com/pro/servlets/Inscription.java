package com.pro.servlets;

import java.io.IOException;

import com.pro.forms.InscriptionForm;
import com.pro.beans.Configurations;
import com.pro.beans.Utilisateur;
import com.pro.dao.intefaces.ConfigurationDao;
import com.pro.dao.implementation.ConfigurationDaoImpl;
import com.pro.dao.DAOFactory;
import com.pro.dao.intefaces.UtilisateurDao;
import com.pro.servlets.abstracts.AbstractServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@SuppressWarnings("serial")

public class Inscription extends AbstractServlet {

	public static final String ATT_USER = "utilisateur";
    public static final String ATT_FORM = "form";
    public static final String VUE = "/Inscription.jsp";

	public Inscription() {
		super(VUE);
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