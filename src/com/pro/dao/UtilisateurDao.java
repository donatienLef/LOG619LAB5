package com.pro.dao;

import java.util.List;

import com.pro.beans.Utilisateur;

public interface UtilisateurDao {
	public Utilisateur trouver(String email, String motdepasse) throws DAOException;
	public Utilisateur insert(Utilisateur utilisateur) throws DAOException;
	public String getMessageUtilisateur(Utilisateur utilisateur);
	public List<Utilisateur> getAllUtilisateur();


}
