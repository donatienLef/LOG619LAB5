package com.pro.beans;

public class Utilisateur {
	private String nom;
	private String email;
	private String motdepasse;
	private String poste;
	private String message;

	public boolean isBlocked() {
		return blocked;
	}

	public void setBlocked(boolean blocked) {
		this.blocked = blocked;
	}

	private boolean blocked;

	// accesseur
	public void setNom(String nom) {
		this.nom = nom;
	}
	public void setMessage(String message){
		this.message = message;
	}
	public String getMessage(){
		return this.message;
	}

	public String getNom() {
		return nom;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public String getMotDePasse() {
		return motdepasse;
	}

	public void setMotDePasse(String string) {
		this.motdepasse = string;

	}
	public void setPoste(String poste){
		this.poste=poste;
	}
	public String getPoste(){
		return this.poste;
	}
	

	// muttateur
}
