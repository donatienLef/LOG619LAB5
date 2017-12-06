package com.pro.dao.implementation;

import static com.pro.Lib.Ref.CLIENT;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.pro.beans.Utilisateur;
import com.pro.dao.exceptions.DAOException;
import com.pro.dao.DAOFactory;
import com.pro.dao.intefaces.UtilisateurDao;
import org.mindrot.jbcrypt.*;

public class UtilisateurDaoImpl extends BaseDao implements UtilisateurDao {
	private long MONTHS = 1000 * 60 * 60 * 24 * 30;

	private String SQL_TROUVER = "SELECT * FROM Utilisateur where email=?";
	private String SQL_INSERT = "INSERT INTO `Utilisateur`(`nom`, `email`, `poste`, `motdepasse`) VALUES (?,?,?,?)";
	private String SQL_ALL_UTILISATEURS = "SELECT * FROM Utilisateur WHERE poste LIKE ?";
	private String SQL_UPDATE_PASSWORD = "UPDATE Utilisateur SET oldpass=motdepasse, motdepasse=?, mustChange=?, passChange=? WHERE email=?";
	private String SQL_UPDATE_BLOCKED = "UPDATE Utilisateur SET blocked=?, mustChange=? WHERE email=?";

	// permet de recupérer la connexion à la base de donnée
	public UtilisateurDaoImpl(DAOFactory daoFactory) {
		super(daoFactory);
	}

	public Utilisateur insert(Utilisateur utilisateur) throws DAOException {
		updateSQL(SQL_INSERT, utilisateur.getNom(), utilisateur.getEmail(),
				!utilisateur.getPoste().equals(CLIENT) ? utilisateur.getPoste() : CLIENT, utilisateur.getMotDePasse());
		return utilisateur;
	}

	@Override
	public boolean changePassword(Utilisateur utilisateur, String motdepasse, boolean mustChange, boolean timeChange) {
		Date changeTime = new Date();
		long lTime = changeTime.getTime();
		changeTime.setTime(lTime + 3 * MONTHS);
		return updateSQL(SQL_UPDATE_PASSWORD, motdepasse, mustChange, timeChange ? changeTime : null, utilisateur.getEmail());
	}

	@Override
	public void setBlocked(String email, boolean blocked, boolean mustChange) {
		updateSQL(SQL_UPDATE_BLOCKED, blocked, mustChange, email);
	}

	public Utilisateur trouver(String email, String motdepasse) throws DAOException {
		return trouverPriver(email, motdepasse);
	}

	public List<Utilisateur> getAllUtilisateur(String poste) {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Utilisateur> listUilisateur = new LinkedList<Utilisateur>();

		try {
			connexion = daoFactory.getConnection();

			preparedStatement = initialisationRequetePreparee(connexion, SQL_ALL_UTILISATEURS, false, poste);
			resultSet = preparedStatement.executeQuery();
			resultSet.next();
			while (resultSet.next()) {
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

	private Utilisateur trouverPriver(String email, String motdepasse) {
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
			preparedStatement = initialisationRequetePreparee(connexion, SQL_TROUVER, false, email);
			resultSet = preparedStatement.executeQuery();
			/* Parcours de la ligne de donnÃ©es retournÃ©e dans le ResultSet */
			if (resultSet.next()) {
				if (BCrypt.checkpw(motdepasse, resultSet.getString("motdepasse"))) {
					utilisateur = map(resultSet);
				}
				// check last password change
				Date last = resultSet.getDate("passChange");
				boolean mustChange = resultSet.getBoolean("mustChange");

				if(mustChange || (last != null && last.after(new Date()))) {
					utilisateur.setMessage("Changez de mot de passe !");
				}

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
		utilisateur.setBlocked(resultSet.getBoolean("blocked"));
		return utilisateur;
	}

}
