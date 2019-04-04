package fr.diginamic.user.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import fr.diginamic.mission.model.Mission;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "user_")
@Getter
@Setter
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id_user")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToMany(mappedBy = "user")
	private List<Mission> missions = new ArrayList();

	@Column
	@NotBlank
	private String firstName;

	@Column
	@NotBlank
	private String lastName;

	@Column
	@NotBlank
	private String password;

	@Column(unique = true)
	@NotBlank
	private String email;

	@Enumerated(EnumType.STRING)
	private RoleEnum role;

	public User() {
	}

	public User(Long id, String firstName, String lastName, String password, String email, RoleEnum role) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.email = email;
		this.role = role;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", password=" + password
				+ ", email=" + email + ", role=" + role + "]";
	}

}
