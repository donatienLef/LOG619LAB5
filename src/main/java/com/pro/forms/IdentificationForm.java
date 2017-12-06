package com.pro.forms;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.pro.beans.Utilisateur;
import com.pro.dao.intefaces.UtilisateurDao;

public class IdentificationForm {
	private String CHAMP_EMAIL="email";
	private String CHAMP_MOTDEPASSE="motdepasse";
	private String CHAMP_COMPTE="compte";
    private Map<String, String> erreurs         = new HashMap<String, String>();

    public String email;

	public Utilisateur trouver(HttpServletRequest request, UtilisateurDao utilisateurDao){
		Utilisateur utilisateur=null;
		email = getValeurChamp( request, CHAMP_EMAIL );
		String motdepasse = getValeurChamp( request, CHAMP_MOTDEPASSE );
		//On check la forme des données
		try {
			validationEmail(email);
		} catch (Exception e) {
            setErreur( CHAMP_EMAIL, e.getMessage() );
		}
		try {
			validationMotsDePasse(motdepasse);
		} catch (Exception e) {
            setErreur( CHAMP_MOTDEPASSE, e.getMessage() );
		}

		try{
			utilisateur = utilisateurDao.trouver(email, motdepasse);
		}catch ( Exception e ) {
            setErreur( CHAMP_COMPTE, "Email ou mot de passe invalide" );
        }

        if(utilisateur != null && utilisateur.isBlocked()) {
			setErreur(CHAMP_COMPTE, "Votre compte est bloqué, contacter admin");
			utilisateur = null;
		}

		return utilisateur;
	}


	private void validationEmail( String email ) throws Exception {
	    if ( email != null && email.trim().length() != 0 ) {
	        if ( !email.matches( "([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)" ) ) {
	            throw new Exception( "Merci de saisir une adresse mail valide." );
	        }
	    } else {
	        throw new Exception( "Merci de saisir une adresse mail." );
	    }
	}
	private void validationMotsDePasse( String motDePasse) throws Exception{
	    if (motDePasse != null && motDePasse.trim().length() != 0 ) {
	        if (motDePasse.trim().length() < 3) {
	            throw new Exception("Les mots de passe doivent contenir au moins 3 caractères.");
	        }
	    } else {
	        throw new Exception("Merci de saisir votre mot de passe.");
	    }
	}
	public static String getValeurChamp( HttpServletRequest request, String nomChamp ) {
        String valeur = request.getParameter( nomChamp );
        if ( valeur == null || valeur.trim().length() == 0 ) {
            return null;
        } else {
            return valeur;
        }
    }
	   /*
     * Ajoute un message correspondant au champ spécifié à la map des erreurs.
     */
    public void setErreur( String champ, String message ) {
        erreurs.put( champ, message );
    }
    public Map<String, String> getErreurs() {
        return erreurs;
    }

    
}
