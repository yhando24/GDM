package fr.diginamic.user.service.dto;


import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;


public class StatusDTO {


	private Integer id;
	

	private String name;
	
	
	
	public StatusDTO() {
		super();
	}

	public StatusDTO(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	public StatusDTO(Integer id) {
	
		this.id = id;

	}

	@NotNull
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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
		return "Status [id=" + id + ", name=" + name + "]";
	}
	
	
}
