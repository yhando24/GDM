package fr.diginamic.kindversion.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;

import fr.diginamic.kind.model.KindDTO;
import fr.diginamic.mission.model.Mission;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KindVersionDTO implements Serializable  {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -2197196861954702653L;

	private Long id;
	
	@NotBlank
	private String name;
	
	private Float adr;
	
	@NotBlank
	private Float bonusPercentage;
	
	@NotBlank
	private Boolean invoiced;
	
	@NotBlank
	private Boolean bonus;
	
	@NotBlank
	private Float dailyCharges; 
	
	@NotBlank
	private Boolean authorizationToExceed;
	
	@NotBlank
	private KindDTO kind;
	
	@NotBlank
	private Long Version;
	
	@NotBlank
	private LocalDateTime UpdatedAt;
	
//	@NotBlank
//	private List <Mission> mission = new ArrayList<>();

	
//	CONSTRUCTEURS
	
	
	public KindVersionDTO(Long id, String name, Float adr, Float bonusPercentage, Boolean invoiced, Boolean bonus,
			Float dailyCharges, Boolean authorizationToExceed, KindDTO kind, Long version, LocalDateTime updatedAt) {
		super();
		this.id = id;
		this.name = name;
		this.adr = adr;
		this.bonusPercentage = bonusPercentage;
		this.invoiced = invoiced;
		this.bonus = bonus;
		this.dailyCharges = dailyCharges;
		this.authorizationToExceed = authorizationToExceed;
		this.kind = kind;
		Version = version;
		UpdatedAt = updatedAt;
	}
	
	
	public KindVersionDTO() {

	}
	
}
