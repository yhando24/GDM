package fr.diginamic.user.model;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	@JsonIgnore
	private Long id;

	@NotBlank
	private String firstName;

	@NotBlank
	private String lastName;

	@NotBlank
	private String password;

	@NotBlank
	private String email;

	@NotBlank
	private RoleEnum role;

	public UserDTO() {
	}

	public UserDTO(Long id, String firstName, String lastName, String password, String email, RoleEnum role) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.email = email;
		this.role = role;
	}

	@Override
	public String toString() {
		return "UserDTO [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", password=" + password
				+ ", email=" + email + ", role=" + role + "]";
	}

}
