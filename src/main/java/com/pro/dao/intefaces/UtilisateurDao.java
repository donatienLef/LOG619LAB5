package com.pro.dao.intefaces;

import java.util.List;

import com.pro.beans.Utilisateur;
import com.pro.dao.exceptions.DAOException;

public interface UtilisateurDao {
	public Utilisateur trouver(String email, String motdepasse) throws DAOException;
	public Utilisateur insert(Utilisateur utilisateur) throws DAOException;
	public String getMessageUtilisateur(Utilisateur utilisateur);
	public List<Utilisateur> getAllUtilisateur();


}
