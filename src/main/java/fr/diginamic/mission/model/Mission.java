package fr.diginamic.mission.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import fr.diginamic.kindVersion.model.KindVersion;
import lombok.Getter;
import lombok.Setter;



@Entity
@Table(name = "mission")
@Getter
@Setter
public class Mission {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	
	@Column ( nullable=false)
	private LocalDate startDate;
	
	@Column ( nullable=false)
	private LocalDate endDate;
	
	@Column ( nullable=false, length = 30 )
	private String departureCity;
	
	@Column ( nullable=false, length = 30 )
	private String arrivalCity;
	
	@Column( nullable=true, length = 45)
	private BigDecimal prime;
	
	@Enumerated(EnumType.STRING)
    private MissionStatusEnum missionStatusEnum;
	
	@ManyToOne
	private KindVersion kindVersion;
	
	@Column
	private BigDecimal amountOfBill;

	
//	CONSTRUCTEURS
	

	public Mission(Long id, LocalDate startDate, LocalDate endDate, String departureCity, String arrivalCity,
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
	

	public Mission() {

	}
	
	
}
