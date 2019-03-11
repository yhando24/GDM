package fr.diginamic.noteDeFrais.model;

import java.time.LocalDate;

public class ExpenseAccountDTO {
	
	private Long id;
	
	
	private LocalDate date;
	
	
	private Float amount;
	
	
	private ExpenseAccountStatusEnum status;

	public ExpenseAccountDTO() {
	}
	
	
	public ExpenseAccountDTO(Long id, LocalDate date, Float amount, ExpenseAccountStatusEnum status) {
		this.id = id;
		this.date = date;
		this.amount = amount;
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Float getAmount() {
		return amount;
	}

	public void setAmount(Float amount) {
		this.amount = amount;
	}

	public ExpenseAccountStatusEnum getStatus() {
		return status;
	}

	public void setStatus(ExpenseAccountStatusEnum status) {
		this.status = status;
	}


	@Override
	public String toString() {
		return "ExpenseAccountDTO [id=" + id + ", date=" + date + ", amount=" + amount + ", status=" + status + "]";
	}
	
}
