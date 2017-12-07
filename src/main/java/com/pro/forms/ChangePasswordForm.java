package com.pro.forms;

import com.pro.beans.Utilisateur;
import com.pro.dao.intefaces.UtilisateurDao;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class ChangePasswordForm {

    private String CHAMP_MOTDEPASSE="motdepasse";
    private String CHAMP_MOTDEPASSE2="motdepasse2";
    private String CHAMP_COMPTE="compte";
    private Map<String, String> erreurs         = new HashMap<String, String>();

    public String email;

    public boolean editPass(HttpServletRequest request, UtilisateurDao utilisateurDao, Utilisateur util, int timeChange){
        email = util.getEmail();
        String motdepasse = getValeurChamp( request, CHAMP_MOTDEPASSE );
        String motdepasse2 = getValeurChamp( request, CHAMP_MOTDEPASSE2 );
        //On check la forme des données

        if(!motdepasse.equals(motdepasse2)) {
            setErreur(CHAMP_COMPTE, "les mots de passe ne sont pas identique");
            return false;
        }

        try {
            validationMotsDePasse(motdepasse);
        } catch (Exception e) {
            setErreur( CHAMP_MOTDEPASSE, e.getMessage() );
            return false;
        }
        try{
            Utilisateur utilisateur = utilisateurDao.trouver(email, motdepasse);
            if(utilisateur != null) {
                setErreur( CHAMP_COMPTE, "Ce mot de passe est deja utilisé par vous" );
                return false;
            }
        }catch ( Exception e ) {
            setErreur(CHAMP_COMPTE, "une erreur s'est produite :/");
            return false;
        }

        try {
            utilisateurDao.changePassword(util, motdepasse, false, timeChange);
            return true;
        } catch (Exception e) {
            setErreur(CHAMP_COMPTE, "une erreur s'est produite :/");
            return false;
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
