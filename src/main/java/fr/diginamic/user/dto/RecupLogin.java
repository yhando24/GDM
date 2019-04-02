package fr.diginamic.user.dto;

import java.io.Serializable;

public class RecupLogin implements Serializable {

	private static final long serialVersionUID = 2669910408626104248L;

	private String email;
	private String password;
	private boolean actif;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isActif() {
		return actif;
	}

	public void setActif(boolean actif) {
		this.actif = actif;
	}

}
