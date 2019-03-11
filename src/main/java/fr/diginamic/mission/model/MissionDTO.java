package fr.diginamic.mission.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import fr.diginamic.kindVersion.model.KindVersion;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MissionDTO {
	
	private Long id;
	
	@NotNull
	private LocalDate startDate;
	
	@NotNull
	private LocalDate endDate;
	
	@NotNull
	private String departureCity;
	
	@NotNull
	private String arrivalCity;
	
	@NotNull
	private BigDecimal prime;
	
	@NotNull
    private MissionStatusEnum missionStatusEnum;
	
	@NotNull
	private KindVersion kindVersion;
	
	@NotNull
	private BigDecimal amountOfBill;

	
	
//	CONSTRUCTEURS
	
	
	public MissionDTO(Long id, LocalDate startDate, LocalDate endDate, String departureCity, String arrivalCity,
			BigDecimal prime, MissionStatusEnum missionStatusEnum, KindVersion kindVersion, BigDecimal amountOfBill) {
		super();
		this.id = id;
		this.startDate = startDate;
		this.endDate = endDate;
		this.departureCity = departureCity;
		this.arrivalCity = arrivalCity;
		this.prime = prime;
		this.missionStatusEnum = missionStatusEnum;
		this.kindVersion = kindVersion;
		this.amountOfBill = amountOfBill;
	}
	

	public MissionDTO() {

	}
	
	
	
}
