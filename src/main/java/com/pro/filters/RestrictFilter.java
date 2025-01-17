package com.pro.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static com.pro.Lib.Ref.UTILISATEUR;

public class RestrictFilter implements Filter {
    public static final String IDENTIFICATION     = "/Identification";
    public static final String INSCRIPTION = "/Inscription";
    public static final String PUBLIC_FOLDER = "/public/";

    public void init(FilterConfig filterConfig) throws ServletException {
	}

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
        /* Cast des objets request et response */
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        /* Récupération de la session depuis la requête */
        HttpSession session = request.getSession();

        /**
         * Si l'objet utilisateur n'existe pas dans la session en cours, alors
         * l'utilisateur n'est pas connecté.
         */
        String url = request.getRequestURL().toString();
        boolean toPublic =  url.contains(INSCRIPTION) || url.contains(PUBLIC_FOLDER) || url.contains(IDENTIFICATION);
        if ( session.getAttribute( UTILISATEUR ) == null  && !toPublic) {

            /* Redirection vers la page publique */
            response.sendRedirect( request.getContextPath() + IDENTIFICATION );
        } else {
            /* Affichage de la page restreinte */
            chain.doFilter( request, response );
        }

	}

	public void destroy() {
	}

}
