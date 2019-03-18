package fr.diginamic.mission.model;

import java.io.Serializable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import fr.diginamic.expenseAccount.model.ExpenseAccount;
import fr.diginamic.kindVersion.model.KindVersion;
import fr.diginamic.user.model.User;
import lombok.Getter;
import lombok.Setter;



@Entity
@Table(name = "mission")
@Getter
@Setter
public class Mission implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7927345980525760782L;


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
	private Float prime;
	
	@Enumerated(EnumType.STRING)
    private MissionStatusEnum missionStatus;
	
	@Enumerated(EnumType.STRING)
	private TransportEnum transportEnum;
	
	@ManyToOne
	@NotNull
	@JoinColumn(name="id_kindVersion")
	private KindVersion kindVersion;
	
	@ManyToOne
	@NotNull
	@JoinColumn(name="id_user")
	private User user;
	
	@Column
	private Float amountOfBill;
	
	@OneToMany(mappedBy = "mission", fetch = FetchType.LAZY)
	private List<ExpenseAccount> expenseAccounts = new ArrayList<>();
	
	
//	CONSTRUCTEURS
	

	public Mission(Long id, LocalDate startDate, LocalDate endDate, String departureCity, String arrivalCity,
			Float prime, MissionStatusEnum missionStatus, KindVersion kindVersion, Float amountOfBill) {
		super();
		this.id = id;
		this.startDate = startDate;
		this.endDate = endDate;
		this.departureCity = departureCity;
		this.arrivalCity = arrivalCity;
		this.prime = prime;
		this.missionStatus = missionStatus;

		this.kindVersion = kindVersion;
		this.amountOfBill = amountOfBill;
	}
	

	public Mission() {

	}

	
	

	@Override
	public String toString() {
		return "Mission [id=" + id + ", startDate=" + startDate + ", endDate=" + endDate + ", departureCity="
				+ departureCity + ", arrivalCity=" + arrivalCity + ", prime=" + prime + ", missionStatus="
				+ missionStatus + ", kindVersion=" + kindVersion + ", user=" + ", amountOfBill=" + amountOfBill
				+ ", expenseAccounts=" + expenseAccounts + "]";
	}


	public Mission(LocalDate startDate, LocalDate endDate, String departureCity, String arrivalCity, Float prime,
			MissionStatusEnum missionStatus, TransportEnum transportEnum, @NotNull KindVersion kindVersion,
			@NotNull User user, Float amountOfBill, List<ExpenseAccount> expenseAccounts) {
		super();
		this.startDate = startDate;
		this.endDate = endDate;
		this.departureCity = departureCity;
		this.arrivalCity = arrivalCity;
		this.prime = prime;
		this.missionStatus = missionStatus;
		this.transportEnum = transportEnum;
		this.kindVersion = kindVersion;
		this.user = user;
		this.amountOfBill = amountOfBill;
		this.expenseAccounts = expenseAccounts;
	}


	public Mission(LocalDate now, LocalDate plusDays, String string, String string2, float f, MissionStatusEnum initial,
			TransportEnum bus, Optional<User> user2, KindVersion kindVersion2, float g, float h) {
		// TODO Auto-generated constructor stub
	}
	
	
}
