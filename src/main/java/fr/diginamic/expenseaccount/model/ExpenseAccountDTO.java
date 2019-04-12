package fr.diginamic.expenseaccount.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.validation.constraints.NotBlank;

import fr.diginamic.mission.model.Mission;
import fr.diginamic.mission.model.MissionDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExpenseAccountDTO implements Serializable {
	
	private static final long serialVersionUID = -6579035398796136954L;

	@NotBlank
	private Long id;
	
	@NotBlank
	private String type;

	@NotBlank
	private LocalDate date;

	@NotBlank
	private Float amount;
	
	@NotBlank
	private MissionDTO mission;

	@NotBlank
	private ExpenseAccountStatusEnum status;

	public ExpenseAccountDTO() {
	}

	@Override
	public String toString() {
		return "ExpenseAccountDTO [id=" + id + ", type=" + type + ", date=" + date + ", amount=" + amount + ", missionDTO="
				+ mission + ", status=" + status + "]";
	}
	
	public ExpenseAccountDTO(@NotBlank Long id, @NotBlank String type, @NotBlank LocalDate date, @NotBlank Float amount,
			@NotBlank MissionDTO mission, @NotBlank ExpenseAccountStatusEnum status) {
		super();
		this.id = id;
		this.type = type;
		this.date = date;
		this.amount = amount;
		this.mission = mission;
		this.status = status;
	}

}
