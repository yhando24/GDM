package fr.diginamic.user.model;

public enum RoleEnum {

	USER("User"), ADMIN("Admin"), MANAGER("Manager");

	private String role = "";

//constructor
	private RoleEnum(String role) {
		this.role = role;
	}

	public String toString() {
		return role;
	}

}
