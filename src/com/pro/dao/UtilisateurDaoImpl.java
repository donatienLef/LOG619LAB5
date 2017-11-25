package com.pro.dao;

import static com.pro.dao.DAOUtilitaire.fermeturesSilencieuses;
import static com.pro.dao.DAOUtilitaire.initialisationRequetePreparee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import com.mysql.jdbc.Util;
import com.pro.beans.Documentation;
import com.pro.beans.Utilisateur;
import org.mindrot.jbcrypt.*;

public class UtilisateurDaoImpl implements UtilisateurDao {
	private DAOFactory daoFactory;
	private String SQL_TROUVER = "SELECT nom, email, poste, motdepasse FROM Utilisateur where email=?";
	private String SQL_INSERT = "INSERT INTO `Utilisateur`(`nom`, `email`, `poste`, `motdepasse`) VALUES (?,?,?,?)";
	private String SQL_GET_MESSAGE = "SELECT `message` FROM `Utilisateur`, `Messages` WHERE Utilisateur.id_utilisateur=Messages.id_utilisateur AND Utilisateur.email=?";
	private String SQL_ALL_UTILISATEURS = "SELECT nom, email, poste, motdepasse FROM Utilisateur";
	private String mdp;

	// permet de recupérer la connexion à la base de donnée
	UtilisateurDaoImpl(DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	public Utilisateur trouver(String email, String motdepasse) throws DAOException {
		this.mdp = motdepasse;
		return trouverPrivate(SQL_TROUVER, email);
	}

	public List<Utilisateur> getAllUtilisateur() {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Utilisateur> listUilisateur = new LinkedList<Utilisateur>();
		
		try {
			connexion = daoFactory.getConnection();

			preparedStatement = initialisationRequetePreparee(connexion, SQL_ALL_UTILISATEURS, false);
			resultSet = preparedStatement.executeQuery();
			resultSet.next(); 
			while (resultSet.next()) {
				System.out.println("un utilisateur");
				Utilisateur utilisateur = map(resultSet);
				listUilisateur.add(utilisateur);				
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			fermeturesSilencieuses(resultSet, preparedStatement, connexion);
		}
		return listUilisateur;
	}

	public Utilisateur insert(Utilisateur utilisateur) throws DAOException {
		if (utilisateur.getPoste() != "Client résidentiel") {
			return insertPrivate(SQL_INSERT, utilisateur, utilisateur.getNom(), utilisateur.getEmail(),
					utilisateur.getPoste(), utilisateur.getMotDePasse());
		}
		return insertPrivate(SQL_INSERT, utilisateur, utilisateur.getNom(), utilisateur.getEmail(),
				"Client résidentiel", utilisateur.getMotDePasse());
	}

	private Utilisateur insertPrivate(String sql, Utilisateur utilisateur, Object... objets) throws DAOException {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			/* RÃ©cupÃ©ration d'une connexion depuis la Factory */
			connexion = daoFactory.getConnection();
			/*
			 * PrÃ©paration de la requÃªte avec les objets passÃ©s en arguments
			 * (ici, uniquement une adresse email) et exÃ©cution.
			 */
			preparedStatement = initialisationRequetePreparee(connexion, sql, false, objets);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			fermeturesSilencieuses(resultSet, preparedStatement, connexion);
		}

		return utilisateur;
	}

	public String getMessageUtilisateur(Utilisateur utilisateur) {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connexion = daoFactory.getConnection();

			preparedStatement = initialisationRequetePreparee(connexion, SQL_GET_MESSAGE, false,
					utilisateur.getEmail());
			resultSet = preparedStatement.executeQuery();
			/* Parcours de la ligne de donnÃ©es retournÃ©e dans le ResultSet */
			if (resultSet.next()) {
				return resultSet.getString("message");
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			fermeturesSilencieuses(resultSet, preparedStatement, connexion);
		}
		return null;
	}

	private Utilisateur trouverPrivate(String sql, Object... objets) throws DAOException {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Utilisateur utilisateur = null;

		try {
			/* RÃ©cupÃ©ration d'une connexion depuis la Factory */
			connexion = daoFactory.getConnection();
			/*
			 * PrÃ©paration de la requÃªte avec les objets passÃ©s en arguments
			 * (ici, uniquement une adresse email) et exÃ©cution.
			 */
			preparedStatement = initialisationRequetePreparee(connexion, sql, false, objets);
			resultSet = preparedStatement.executeQuery();
			/* Parcours de la ligne de donnÃ©es retournÃ©e dans le ResultSet */
			if (resultSet.next()) {
				if (BCrypt.checkpw(mdp, resultSet.getString("motdepasse"))) {
					utilisateur = map(resultSet);
				}
				;
				utilisateur.setMessage(getMessageUtilisateur(utilisateur));
				System.out.println("message set " + utilisateur.getMessage());
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			fermeturesSilencieuses(resultSet, preparedStatement, connexion);
		}

		return utilisateur;
	}

	/*
	 * Simple mÃ©thode utilitaire permettant de faire la correspondance (le
	 * mapping) entre une ligne issue de la table des utilisateurs (un
	 * ResultSet) et un bean Utilisateur.
	 */
	private static Utilisateur map(ResultSet resultSet) throws SQLException {
		Utilisateur utilisateur = new Utilisateur();
		utilisateur.setNom(resultSet.getString("nom"));
		utilisateur.setEmail(resultSet.getString("email"));
		utilisateur.setPoste(resultSet.getString("poste"));
		return utilisateur;
	}

}
