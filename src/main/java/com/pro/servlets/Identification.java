package com.pro.servlets;

import com.pro.Lib.Ref;
import com.pro.beans.Configurations;
import com.pro.beans.Utilisateur;
import com.pro.dao.DAOFactory;
import com.pro.forms.IdentificationForm;
import com.pro.servlets.abstracts.AbstractServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

import static com.pro.Lib.Ref.*;

public class Identification extends AbstractServlet {

    public static final String ATT_FORM = "form";
    public static final String VUE = "/Identification.jsp";
    public static final String SUCCESS = "/Home";

    public Identification() {
        super(VUE);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Configurations configuration = DAOFactory.getInstance().getConfigurationDao().readConfigurationDefault();

        if ((configuration.getNbeTentativeMax() != 0) && ((Integer) session.getAttribute(Ref.TENTATIVE)) >= configuration.getNbeTentativeMax()) {
            //alors on bloque
            session.setAttribute(Ref.TENTATIVE_ERROR, "vous avez dépasser le nombre de tentative");
            session.setAttribute(TIMEOUT_START, new Date());
            request.getRequestDispatcher(VUE).forward(request, response);
        } else {
            // creation du formulaire
            IdentificationForm forms = new IdentificationForm();

            Utilisateur utilisateur ;
            utilisateur = forms.trouver(request, utilisateurDao);
            request.setAttribute(ATT_FORM, forms);
            if (utilisateur != null) {
                session.setAttribute(UTILISATEUR, utilisateur);
                response.sendRedirect(request.getContextPath() + SUCCESS);
            } else {
                session.setAttribute(Ref.TENTATIVE, (Integer) session.getAttribute(TENTATIVE) + 1);
                request.getRequestDispatcher(VUE).forward(request, response);
            }
        }
    }
}
