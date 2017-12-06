package com.pro.servlets;

import com.pro.Lib.Ref;
import com.pro.beans.Configurations;
import com.pro.beans.Utilisateur;
import com.pro.dao.DAOFactory;
import com.pro.dao.intefaces.LogDao;
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

    public Identification() {
        super(VUE);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Configurations configuration = DAOFactory.getInstance().getConfigurationDao().readConfigurationDefault();
        int tentatives = (Integer) session.getAttribute(Ref.TENTATIVE);
        if ((configuration.getNbeTentativeMax() != 0) && (tentatives > configuration.getNbeTentativeMax())) {
            //alors on bloque
            session.setAttribute(TIMEOUT_START, new Date());
            response.sendRedirect(HOME);
        } else {
            // creation du formulaire
            IdentificationForm forms = new IdentificationForm();

            Utilisateur utilisateur ;
            utilisateur = forms.trouver(request, utilisateurDao);
            String email = forms.email;

            logDao.addLog(email, "Tentative de connection #"+ session.getAttribute(TENTATIVE));

            request.setAttribute(ATT_FORM, forms);
            if (utilisateur != null) {
                logDao.addLog(email, "Connection OK");
                session.setAttribute(UTILISATEUR, utilisateur);
                response.sendRedirect(HOME);
            } else {
                logDao.addLog(email, "Connection NOK");
                session.setAttribute(Ref.TENTATIVE, tentatives + 1);

                if(configuration.getBlocageIsPossible() && configuration.getNbeTentativeMax() != 0 && tentatives >= configuration.getNbeTentativeMax()) {
                    // on verrouille le compte
                    utilisateurDao.setBlocked(email, true, configuration.getChangePasswordAfterNTentative());
                }

                request.getRequestDispatcher(VUE).forward(request, response);
            }
        }
    }
}
