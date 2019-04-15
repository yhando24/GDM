package fr.diginamic.user.service.dto;


import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;


public class AbsenceTypeDTO {

	private Integer id;
	

	private String name;
	
	public AbsenceTypeDTO(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public AbsenceTypeDTO() {
		// TODO Auto-generated constructor stub
	}
	
	@NotNull
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@NotBlank
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "AbsenceType [id=" + id + ", name=" + name + "]";
	}
		
}
