package fr.diginamic.expenseaccount.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import fr.diginamic.mission.model.Mission;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Entity
@Table(name = "expense_account")
@Getter
@Setter
public class ExpenseAccount implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	@NonNull
	private String type;
	
	@Column
	@NonNull
	private LocalDate date;

	@Column
	@NonNull
	private Float amount;

	@Enumerated(EnumType.STRING)
	@NonNull
	private ExpenseAccountStatusEnum status;

	@ManyToOne
	@NonNull
	private Mission mission;

	public ExpenseAccount() {
	}

	public ExpenseAccount(Long id, LocalDate date, Float amount, ExpenseAccountStatusEnum status, Mission mission) {
		this.id = id;
		this.date = date;
		this.amount = amount;
		this.status = status;
		this.mission = mission;
	}

	@Override
	public String toString() {
		return "ExpenseAccount [id=" + id + ", date=" + date + ", amount=" + amount + ", status=" + status
				+ ", mission=" + mission + "]";
	}

	

}
