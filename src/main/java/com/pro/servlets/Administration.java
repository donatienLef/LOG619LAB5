package com.pro.servlets;

import java.io.IOException;

import com.pro.Lib.Ref;
import com.pro.dao.DAOFactory;
import com.pro.forms.ConfigurationForm;
import com.pro.servlets.abstracts.DoubleAuthServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static com.pro.Lib.Ref.VALID;

public class Administration extends DoubleAuthServlet {
	public static final String ATT_FORM = "form";
	public static final String VUE = "/WEB-INF/Configurations.jsp";
	public static final String ADMINISTRATION = "/WEB-INF/Administration.jsp";

	public Administration() {
		super(ADMINISTRATION);
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doPost(request, response);

		ConfigurationForm formConfig = new ConfigurationForm();
		formConfig.setConfiguration(request, DAOFactory.getInstance().getConfigurationDao());
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