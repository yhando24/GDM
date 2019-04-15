package fr.diginamic.user.service.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotBlank;


public class AbsenceDTO {


	private Integer id;

	private LocalDate startDate;


	private LocalDate endDate;


	private String reason;

	private AbsenceTypeDTO absenceType;


	private StatusDTO status;

	public AbsenceDTO() {
		super();
	}

	public AbsenceDTO(Integer id, LocalDate startDate, LocalDate endDate, String reason, AbsenceTypeDTO absenceType,
			StatusDTO status) {
		super();
		this.id = id;
		this.startDate = startDate;
		this.endDate = endDate;
		this.reason = reason;
		this.absenceType = absenceType;
		this.status = status;
	}

	@NotNull
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Past
	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	@Past
	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	@NotBlank
	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public AbsenceTypeDTO getAbsenceType() {
		return absenceType;
	}

	public void setAbsenceType(AbsenceTypeDTO absenceType) {
		this.absenceType = absenceType;
	}

	public StatusDTO getStatus() {
		return status;
	}

	public void setStatus(StatusDTO status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Absence [id=" + id + ", startDate=" + startDate + ", endDate=" + endDate + ", reason=" + reason
				+ ", idAbsenceType=" + absenceType + ", idStatus=" + status + "]";
	}

}
