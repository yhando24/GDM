package fr.diginamic.mission.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.constraints.NotBlank;

import fr.diginamic.kind.model.KindDTO;
import fr.diginamic.user.model.User;
import fr.diginamic.user.model.UserDTO;
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
	private MissionStatusEnum missionStatus;

	@NotBlank
	private TransportEnum transportEnum;

	@NotBlank
	private KindDTO kind;

	@NotBlank
	private BigDecimal amountOfBill;
	
	@NotBlank
	private UserDTO user;

//	CONSTRUCTEURS

	public MissionDTO(Long id, LocalDate startDate, LocalDate endDate, String departureCity, String arrivalCity,
			BigDecimal prime, MissionStatusEnum missionStatus, TransportEnum transportEnum, KindDTO kind,
			BigDecimal amountOfBill, UserDTO user) {
		super();
		this.id = id;
		this.startDate = startDate;
		this.endDate = endDate;
		this.departureCity = departureCity;
		this.arrivalCity = arrivalCity;
		this.prime = prime;
		this.missionStatus = missionStatus;
		this.transportEnum = transportEnum;
		this.kind = kind;
		this.amountOfBill = amountOfBill;
		this.user = user;
	}

	public MissionDTO() {

	}

	@Override
	public String toString() {
		return "MissionDTO [id=" + id + ", startDate=" + startDate + ", endDate=" + endDate + ", departureCity="
				+ departureCity + ", arrivalCity=" + arrivalCity + ", prime=" + prime + ", missionStatusEnum="
				+ missionStatus + ", kind=" + kind + ", amountOfBill=" + amountOfBill + "]";
	}

}
