package fr.diginamic.noteDeFrais.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="expense_account")
public class ExpenseAccount {

	@Id
	@Column
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column
	private LocalDate date;
	
	@Column 
	private Float amount;
	
	@Enumerated
	private ExpenseAccountStatusEnum status;

	public ExpenseAccount() {
	}
	
	
	public ExpenseAccount(Long id, LocalDate date, Float amount, ExpenseAccountStatusEnum status) {
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
		return "ExpenseAccount [id=" + id + ", date=" + date + ", amount=" + amount + ", status=" + status + "]";
	}
	
	
	
	
}
