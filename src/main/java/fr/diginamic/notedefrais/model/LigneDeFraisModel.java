package fr.diginamic.notedefrais.model;

public class LigneDeFraisModel {

	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	private String date;

	private String nature;

	private String montant;

	public LigneDeFraisModel() {
		super();
	}


	public LigneDeFraisModel(String id, String date, String nature, String montant) {
		super();
		this.id = id;
		this.date = date;
		this.nature = nature;
		this.montant = montant;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getNature() {
		return nature;
	}

	public void setNature(String nature) {
		this.nature = nature;
	}

	public String getMontant() {
		return montant;
	}

	public void setMontant(String montant) {
		this.montant = montant;
	}

}
