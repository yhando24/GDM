package fr.diginamic.kind.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class KindDTO {
	
	private Long id;

	private String name;

	private Float adr;

	private Float bonusPercentage;

	private LocalDate updatedAt;
	
	private Boolean bonus;

	private Float dailyCharges;

	private Boolean authorizationToExceed;
	
	private Boolean invoiced;
	
	public KindDTO(){
	}
	

	public KindDTO(Long id, String name, Float adr, Float bonusPercentage, LocalDate updatedAt, Boolean invoiced,
			Boolean bonus, Float dailyCharges, Boolean authorizationToExceed) {
		this.id = id;
		this.name = name;
		this.adr = adr;
		this.bonusPercentage = bonusPercentage;
		this.updatedAt = updatedAt;
		this.invoiced = invoiced;
		this.bonus = bonus;
		this.dailyCharges = dailyCharges;
		this.authorizationToExceed = authorizationToExceed;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Float getAdr() {
		return adr;
	}

	public void setAdr(Float adr) {
		this.adr = adr;
	}

	public Float getBonusPercentage() {
		return bonusPercentage;
	}

	public void setBonusPercentage(Float bonusPercentage) {
		this.bonusPercentage = bonusPercentage;
	}

	public LocalDate getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDate updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Boolean getInvoiced() {
		return invoiced;
	}

	public void setInvoiced(Boolean invoiced) {
		this.invoiced = invoiced;
	}

	public Boolean getBonus() {
		return bonus;
	}

	public void setBonus(Boolean bonus) {
		this.bonus = bonus;
	}

	public Float getDailyCharges() {
		return dailyCharges;
	}

	public void setDailyCharges(Float dailyCharges) {
		this.dailyCharges = dailyCharges;
	}

	public Boolean getAuthorizationToExceed() {
		return authorizationToExceed;
	}

	public void setAuthorizationToExceed(Boolean authorizationToExceed) {
		this.authorizationToExceed = authorizationToExceed;
	}


	@Override
	public String toString() {
		return "kind [id=" + id + ", name=" + name + ", adr=" + adr + ", bonusPercentage=" + bonusPercentage
				+ ", updatedAt=" + updatedAt + ", invoiced=" + invoiced + ", bonus=" + bonus + ", dailyCharges="
				+ dailyCharges + ", authorizationToExceed=" + authorizationToExceed + "]";
	}
}
