package com.pro.dao.intefaces;

import java.util.List;

import com.pro.beans.Utilisateur;
import com.pro.dao.exceptions.DAOException;

public interface UtilisateurDao {
	public Utilisateur trouver(String email, String motdepasse) throws DAOException;
	public Utilisateur insert(Utilisateur utilisateur) throws DAOException;
	public boolean changePassword(Utilisateur utilisateur, String motdepasse, boolean mustChange, int timeChange);
	public void setBlocked(String email, boolean blocked, boolean mustChange);
	public List<Utilisateur> getAllUtilisateur(String poste);


}
