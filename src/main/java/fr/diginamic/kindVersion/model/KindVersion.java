package fr.diginamic.kindVersion.model;



import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import fr.diginamic.kind.model.Kind;
import fr.diginamic.mission.model.Mission;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "kind_version")
@Getter
@Setter
public class KindVersion {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	
	@Column ( nullable=false)
	private String name;
	
	@Column ( nullable=false)
	private Float adr;
	
	@Column ( nullable=false)
	private Float bonusPercentage;
	
	@Column ( nullable=false)
	private Boolean invoiced;
	
	@Column ( nullable=false)
	private Boolean bonus;
	
	@Column ( nullable=false)
	private Float dailyCharges; 
	
	@Column ( nullable=false)
	private Boolean authorizationToExceed;
	
	@ManyToOne
	@JoinColumn(name="id_kind")
	private Kind kind;
	
	@Column ( nullable=false)
	private Long Version;
	
	@Column ( nullable=false)
	private LocalDate UpdatedAt;
	
	@OneToMany(mappedBy = "kindVersion")
	private List <Mission> mission = new ArrayList<>();


	
//	CONSTRUCTEURS
	
	public KindVersion(Long id, String name, Float adr, Float bonusPercentage, Boolean invoiced, Boolean bonus,
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
	
	
	public KindVersion() {

	}
	
	
	
	
	

}
