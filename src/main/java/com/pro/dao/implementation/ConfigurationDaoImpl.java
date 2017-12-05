package com.pro.dao.implementation;

import static com.pro.dao.DAOUtilitaire.fermeturesSilencieuses;
import static com.pro.dao.DAOUtilitaire.initialisationRequetePreparee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import com.pro.beans.Configurations;
import com.pro.dao.intefaces.ConfigurationDao;
import com.pro.dao.exceptions.DAOException;
import com.pro.dao.DAOFactory;

public class ConfigurationDaoImpl implements ConfigurationDao {
	private DAOFactory daoFactory;
	private static final String CHAMP_MDP_PATTERN  = "mdpPattern";
    private static final String CHAMP_NBE_TENTATIVE_MAX   = "nbeTentativeMax";
    private static final String CHAMP_NBE_MINUTES_ENTRE_TENTATIVE   = "nbeMinutesEntreTentative";
    private static final String CHAMP_BLOCAGE_IS_POSSIBLE    = "blocageIsPossible";
    private static final String CHAMP_CHANGE_PASSWORD_AFTER_N_TENTATIVE   = "changePasswordAfterNTentative";
    private static final String CHAMP_CHANGE_PASSWORD_AFTER_FORGET   = "changePasswordAfterForget";
	private String SQL_READDEFAULT="SELECT `idConfiguration`, `nomConfiguration`, `mdpPattern`, `nbeTentativeMax`, `nbeMinutesEntreTentative`, `blocageIsPossible`, `changePasswordAfterNTentative`, `changePasswordAfterForget` FROM `Configurations` where 1";
	private String SQL_UPDATE_DEFAUlT = "UPDATE `Configurations` SET `mdpPattern`=?,`nbeTentativeMax`=?,`nbeMinutesEntreTentative`=?,`blocageIsPossible`=?,`changePasswordAfterNTentative`=?,`changePasswordAfterForget`=? WHERE 1";

	// permet de recupérer la connexion à la base de donnée
	public ConfigurationDaoImpl(DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	public Configurations readConfigurationDefault() throws DAOException {
		Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;  
        Configurations configurationDefault = null;
        try {
            /* RÃ©cupÃ©ration d'une connexion depuis la Factory */
            connexion = daoFactory.getConnection();
            /*
             * PrÃ©paration de la requÃªte avec les objets passÃ©s en arguments
             * (ici, uniquement une adresse email) et exÃ©cution.
             */
            preparedStatement = initialisationRequetePreparee( connexion, SQL_READDEFAULT, false );
            resultSet = preparedStatement.executeQuery();
            /* Parcours de la ligne de donnÃ©es retournÃ©e dans le ResultSet */
            if ( resultSet.next() ) {
                    configurationDefault = map( resultSet ); 
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } 
        finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
        }
      
		
		return configurationDefault;
	}

	public Configurations updateConfigurationDefault(Configurations configuration) throws DAOException {
		updateConfigurationDefaultPrivate(SQL_UPDATE_DEFAUlT, configuration.getMdpPattern(), configuration.getNbeTentativeMax(), configuration.getNbeMinutesEntreTentative(), configuration.getBlocageIsPossible(), configuration.getChangePasswordAfterNTentative(), configuration.getChangePasswordAfterForget());
		return null;
	}
	private Configurations updateConfigurationDefaultPrivate(String sql, Object... objets){
		Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;  
        try {
            connexion = daoFactory.getConnection();

            preparedStatement = initialisationRequetePreparee( connexion, sql, false, objets );
            preparedStatement.executeUpdate();

        } catch ( SQLException e ) {
            throw new DAOException( e );
        } 
        finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
        }
		return null;
	}
	
	private static Configurations map( ResultSet resultSet ) throws SQLException {
		Configurations config = new Configurations();
		config.setMdpPattern(resultSet.getString(CHAMP_MDP_PATTERN));
		config.setNbeTentativeMax(Integer.parseInt(resultSet.getString(CHAMP_NBE_TENTATIVE_MAX)));
		config.setNbeMinutesEntreTentative(Integer.parseInt(resultSet.getString(CHAMP_NBE_MINUTES_ENTRE_TENTATIVE)));
		config.setBlocageIsPossible(customParseBoolean(resultSet.getString(CHAMP_BLOCAGE_IS_POSSIBLE)));
		config.setChangePasswordAfterNTentative(Integer.parseInt(resultSet.getString(CHAMP_CHANGE_PASSWORD_AFTER_N_TENTATIVE)));
		config.setChangePasswordAfterForget(customParseBoolean(resultSet.getString(CHAMP_CHANGE_PASSWORD_AFTER_FORGET)));
        return config;
    }
	private static Boolean customParseBoolean(String value){
		if(value.equals("0")){
			return false;
		}
		else{
			return true;
		}
	}

}
