package com.pro.forms;

import com.pro.beans.Configurations;
import com.pro.dao.intefaces.ConfigurationDao;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;


public final class ConfigurationForm {
    private static final String CHAMP_MDP_PATTERN  = "mdpPattern";
    private static final String CHAMP_NBE_TENTATIVE_MAX   = "nbeTentativeMax";
    private static final String CHAMP_NBE_MINUTES_ENTRE_TENTATIVE   = "nbeMinutesEntreTentative";
    private static final String CHAMP_BLOCAGE_IS_POSSIBLE    = "blocageIsPossible";
    private static final String CHAMP_CHANGE_PASSWORD_AFTER_N_TENTATIVE   = "changePasswordAfterNTentative";
    private static final String CHAMP_CHANGE_PASSWORD_AFTER_FORGET   = "changePasswordAfterForget";

    private String              resultat;
    private Map<String, String> erreurs      = new HashMap<String, String>();

    public String getResultat() {
        return resultat;
    }

    public Map<String, String> getErreurs() {
        return erreurs;
    }

    public Configurations setConfiguration(HttpServletRequest request, ConfigurationDao configurationDao){
    	String mdpPattern = getValeurChamp( request, CHAMP_MDP_PATTERN );
    	Integer nbeTentativeMax = Integer.parseInt(getValeurChamp( request, CHAMP_NBE_TENTATIVE_MAX ));
    	Integer nbeMinutesEntreTentative = Integer.parseInt(getValeurChamp( request, CHAMP_NBE_MINUTES_ENTRE_TENTATIVE ));
    	Boolean blocageIsPossible = Boolean.parseBoolean(getValeurChamp( request, CHAMP_BLOCAGE_IS_POSSIBLE ));
    	Boolean changePasswordAfterNTentative = Boolean.parseBoolean(getValeurChamp( request, CHAMP_CHANGE_PASSWORD_AFTER_N_TENTATIVE ));
    	Boolean changePasswordAfterForget = Boolean.parseBoolean(getValeurChamp( request, CHAMP_CHANGE_PASSWORD_AFTER_FORGET ));

    	Configurations configuration = new Configurations();
    	configuration.setMdpPattern(mdpPattern);
    	configuration.setNbeTentativeMax(nbeTentativeMax);
    	configuration.setNbeMinutesEntreTentative(nbeMinutesEntreTentative);
    	configuration.setBlocageIsPossible(blocageIsPossible);
    	configuration.setChangePasswordAfterNTentative(changePasswordAfterNTentative);
    	configuration.setChangePasswordAfterForget(changePasswordAfterForget);
    	configurationDao.updateConfigurationDefault(configuration);
    	return configuration;
    }
    

    /*
     * Méthode utilitaire qui retourne null si un champ est vide, et son contenu
     * sinon.
     */
    private static String getValeurChamp( HttpServletRequest request, String nomChamp ) {
        String valeur = request.getParameter( nomChamp );
        return valeur;
    }
}