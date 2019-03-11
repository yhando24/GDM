package fr.diginamic.expenseAccount.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name="expense_account")
@Getter
@Setter
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

	@Override
	public String toString() {
		return "ExpenseAccount [id=" + id + ", date=" + date + ", amount=" + amount + ", status=" + status + "]";
	}
	
	
	
	
}
