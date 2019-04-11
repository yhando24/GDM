package fr.diginamic.kind.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

import lombok.Getter;
import lombok.Setter;

@Entity
@Audited
@Table
@Getter
@Setter
public class Kind {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private Float adr;

	@Column(nullable = false)
	private Float bonusPercentage;

	@Column(nullable = false)
	private LocalDateTime updatedAt;

	@Column(nullable = false)
	private Boolean invoiced;

	@Column(nullable = false)
	private Boolean bonus;

	@Column(nullable = false)
	private Float dailyCharges;

	@Column(nullable = false)
	private Boolean authorizationToExceed;
	
	@Column (nullable = false)
	private Boolean active;

	public Kind() {
	}
	
	public Kind(String name, Float adr, Float bonusPercentage, LocalDateTime updatedAt, Boolean invoiced, Boolean bonus,
			Float dailyCharges, Boolean authorizationToExceed, Boolean active) {
		super();
		this.name = name;
		this.adr = adr;
		this.bonusPercentage = bonusPercentage;
		this.updatedAt = updatedAt;
		this.invoiced = invoiced;
		this.bonus = bonus;
		this.dailyCharges = dailyCharges;
		this.authorizationToExceed = authorizationToExceed;
		this.active = active;
		
	}

	public Kind(Long id,String name, Float adr, Float bonusPercentage, LocalDateTime updatedAt, Boolean invoiced, Boolean bonus,
			Float dailyCharges, Boolean authorizationToExceed, Boolean active) {
		this.id = id ;
		this.name = name;
		this.adr = adr;
		this.bonusPercentage = bonusPercentage;
		this.updatedAt = updatedAt;
		this.invoiced = invoiced;
		this.bonus = bonus;
		this.dailyCharges = dailyCharges;
		this.authorizationToExceed = authorizationToExceed;
		this.active = active;
	}

	@Override
	public String toString() {
		return "kind [id=" + id + ", name=" + name + ", adr=" + adr + ", bonusPercentage=" + bonusPercentage
				+ ", updatedAt=" + updatedAt + ", invoiced=" + invoiced + ", bonus=" + bonus + ", dailyCharges="
				+ dailyCharges + ", authorizationToExceed=" + authorizationToExceed + ", active=" + active + "]";
	}



	

}
