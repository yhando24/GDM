package fr.diginamic.mission.model;

import java.io.Serializable;
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
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import fr.diginamic.kindversion.model.KindVersion;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MissionDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 942575388236824289L;

	private Long id;
	
	@NotBlank
	private LocalDate startDate;
	
	@NotBlank
	private LocalDate endDate;
	
	@NotBlank
	private String departureCity;
	
	@NotBlank
	private String arrivalCity;
	
	@NotBlank
	private BigDecimal prime;
	
	@NotBlank
    private MissionStatusEnum missionStatusEnum;
	
	@NotBlank
	private KindVersion kindVersion;
	
	@NotBlank
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


	@Override
	public String toString() {
		return "MissionDTO [id=" + id + ", startDate=" + startDate + ", endDate=" + endDate + ", departureCity="
				+ departureCity + ", arrivalCity=" + arrivalCity + ", prime=" + prime + ", missionStatusEnum="
				+ missionStatusEnum + ", kindVersion=" + kindVersion + ", amountOfBill=" + amountOfBill + "]";
	}
	
	
	
}
