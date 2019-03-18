package fr.diginamic.kind.model;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KindDTO {

	@NotBlank
	private Long id;

	@NotBlank
	private String name;

	@NotBlank
	private Float adr;

	@NotBlank
	private Float bonusPercentage;

	@NotBlank
	private LocalDateTime updatedAt;

	@NotBlank
	private Boolean bonus;

	@NotBlank
	private Float dailyCharges;

	@NotBlank
	private Boolean authorizationToExceed;

	@NotBlank
	private Boolean invoiced;

	@NotBlank
	public KindDTO() {
	}

	public KindDTO(String name, Float adr, Float bonusPercentage, LocalDateTime updatedAt, Boolean invoiced,
			Boolean bonus, Float dailyCharges, Boolean authorizationToExceed) {
		this.name = name;
		this.adr = adr;
		this.bonusPercentage = bonusPercentage;
		this.updatedAt = updatedAt;
		this.invoiced = invoiced;
		this.bonus = bonus;
		this.dailyCharges = dailyCharges;
		this.authorizationToExceed = authorizationToExceed;
	}

	@Override
	public String toString() {
		return "kind [id=" + id + ", name=" + name + ", adr=" + adr + ", bonusPercentage=" + bonusPercentage
				+ ", updatedAt=" + updatedAt + ", invoiced=" + invoiced + ", bonus=" + bonus + ", dailyCharges="
				+ dailyCharges + ", authorizationToExceed=" + authorizationToExceed + "]";
	}
}