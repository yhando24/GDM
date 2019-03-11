package fr.diginamic.kindVersion.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import fr.diginamic.kind.model.Kind;
import fr.diginamic.mission.model.Mission;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KindVersionDTO {
	

	private Long id;
	
	@NotNull
	private String name;
	
	private Float adr;
	
	@NotNull
	private Float bonusPercentage;
	
	@NotNull
	private Boolean invoiced;
	
	@NotNull
	private Boolean bonus;
	
	@NotNull
	private Float dailyCharges; 
	
	@NotNull
	private Boolean authorizationToExceed;
	
	@NotNull
	private Kind kind;
	
	@NotNull
	private Long Version;
	
	@NotNull
	private LocalDate UpdatedAt;
	
	@NotNull
	private List <Mission> mission = new ArrayList<>();

	
//	CONSTRUCTEURS
	
	
	public KindVersionDTO(Long id, String name, Float adr, Float bonusPercentage, Boolean invoiced, Boolean bonus,
			Float dailyCharges, Boolean authorizationToExceed, Kind kind, Long version, LocalDate updatedAt,
			List<Mission> mission) {
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
		this.mission = mission;
	}
	
	
	public KindVersionDTO() {

	}
	
}
