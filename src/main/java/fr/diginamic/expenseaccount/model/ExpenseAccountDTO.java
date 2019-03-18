package fr.diginamic.expenseaccount.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExpenseAccountDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@NotBlank
	private Long id;

	@NotBlank
	private LocalDate date;

	@NotBlank
	private Float amount;

	@NotBlank
	private ExpenseAccountStatusEnum status;

	public ExpenseAccountDTO() {
	}

	public ExpenseAccountDTO(Long id, LocalDate date, Float amount, ExpenseAccountStatusEnum status) {
		this.id = id;
		this.date = date;
		this.amount = amount;
		this.status = status;
	}

	@Override
	public String toString() {
		return "ExpenseAccountDTO [id=" + id + ", date=" + date + ", amount=" + amount + ", status=" + status + "]";
	}

}
