package fr.diginamic.WorkBook.entities;

import java.time.LocalDate;


import fr.diginamic.WorkBook.annotations.ExcelField;
import fr.diginamic.WorkBook.annotations.ExcelObject;
import fr.diginamic.WorkBook.annotations.ParseType;

@ExcelObject(parseType = ParseType.ROW, start = 8)
public class MissionExcel {

	

	
	public MissionExcel() {
		super();
	}

	public MissionExcel(Long id, LocalDate startDate, LocalDate endDate, String departureCity, String arrivalCity,
			 String missionStatus, String transportEnum, String nature, String user) {
		super();
		this.id = id;
		this.startDate = startDate;
		this.endDate = endDate;
		this.departureCity = departureCity;
		this.arrivalCity = arrivalCity;
		this.missionStatus = missionStatus;
		this.transportEnum = transportEnum;
		this.nature = nature;
		this.user = user;
	
	}

	@ExcelField(position = 7, name = "id")
	private Long id;
	
	@ExcelField(position = 8, name = "Date de debut de mission")
	private LocalDate startDate;
	
	@ExcelField(position = 9, name = "Date de fin de mission")
	private LocalDate endDate;
	
	@ExcelField(position = 10, name = "Ville de depart")
	private String departureCity;
	
	@ExcelField(position = 11, name = "Ville d'arrivée")
	private String arrivalCity;
	
	@ExcelField(position = 12, name = "Prime")
	private String prime;
	
	@ExcelField(position = 13, name = "Statut")
    private String missionStatus;
	
	@ExcelField(position = 14, name = "Type de transport")
	private String transportEnum;
	
	@ExcelField(position = 15, name = "Nature")
	private String nature;
	
	@ExcelField(position = 16, name = "Mail de l'utilisateurs")
	private String user;
	
	@ExcelField(position = 17, name = "Montant de la facture")
	private String amountOfBill;

	
	
	
	
	
	
	
	
	
	@Override
	public String toString() {
		return "MissionExcel [id=" + id + ", startDate=" + startDate + ", endDate=" + endDate + ", departureCity="
				+ departureCity + ", arrivalCity=" + arrivalCity + ", prime=" + prime + ", missionStatus="
				+ missionStatus + ", transportEnum=" + transportEnum + ", nature=" + nature + ", user=" + user
				+ ", montant facture=" + amountOfBill + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public String getDepartureCity() {
		return departureCity;
	}

	public void setDepartureCity(String departureCity) {
		this.departureCity = departureCity;
	}

	public String getArrivalCity() {
		return arrivalCity;
	}

	public void setArrivalCity(String arrivalCity) {
		this.arrivalCity = arrivalCity;
	}

	public String getPrime() {
		return prime;
	}

	public void setPrime(String prime) {
		this.prime = prime;
	}

	public String getMissionStatus() {
		return missionStatus;
	}

	public void setMissionStatus(String missionStatus) {
		this.missionStatus = missionStatus;
	}

	public String getTransportEnum() {
		return transportEnum;
	}

	public void setTransportEnum(String transportEnum) {
		this.transportEnum = transportEnum;
	}

	public String getNature() {
		return nature;
	}

	public void setNature(String nature) {
		this.nature = nature;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getAmountOfBill() {
		return amountOfBill;
	}

	public void setAmountOfBill(String amountOfBill) {
		this.amountOfBill = amountOfBill;
	}
	
	
	
	
}
