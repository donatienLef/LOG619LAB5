package com.pro.servlets;

import com.pro.beans.Utilisateur;
import com.pro.dao.intefaces.ConfigurationDao;
import com.pro.forms.ChangePasswordForm;
import com.pro.servlets.abstracts.DoubleAuthServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.pro.Lib.Ref.HOME;
import static com.pro.Lib.Ref.UTILISATEUR;

public class ChangePassword extends DoubleAuthServlet {

    private static final String VUE = "/WEB-INF/ChangePassword.jsp";
    public static final String ATT_FORM = "form";


    public ChangePassword() {
        super(VUE);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);

        HttpSession session = req.getSession();
        ChangePasswordForm form = new ChangePasswordForm();
        Utilisateur util = (Utilisateur) session.getAttribute(UTILISATEUR);
        boolean result = form.editPass(req, utilisateurDao, util , configurationDao.readConfigurationDefault().getChangePasswordPeriod());
        logDao.addLog(util.getEmail(), "Mot de passe modifié : " + result);

        req.setAttribute(ATT_FORM, form);
        if(result) {
            resp.sendRedirect(HOME);
        } else {
            sendToVue(VUE, req, resp);
        }


    }
}
