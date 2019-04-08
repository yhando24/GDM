package fr.diginamic.notedefrais.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class LigneDeFrais {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "DATE")
	private LocalDate date;

	@Column(name = "NATURE")
	private String nature;

	@Column(name = "MONTANT")
	private BigDecimal montant;

	@ManyToOne
	@JoinColumn(name = "ID_NOTE_DE_FRAIS")
	private NoteDeFrais noteDeFrais;

	public LigneDeFrais() {
		super();
	}

	public LigneDeFrais(String nature, LocalDate date, BigDecimal montant, NoteDeFrais noteDeFrais) {
		super();
		this.date = date;
		this.nature = nature;
		this.montant = montant;
		this.noteDeFrais = noteDeFrais;
	}

	public LigneDeFrais(String nature2, LocalDate parse, BigDecimal bigDecimal, Optional<NoteDeFrais> note) {

	}

	public Integer getId() {
		return id;
	}

	public LocalDate getDate() {
		return date;
	}

	public String getNature() {
		return nature;
	}

	public BigDecimal getMontant() {
		return montant;
	}

	public NoteDeFrais getNoteDeFrais() {
		return noteDeFrais;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public void setNature(String nature) {
		this.nature = nature;
	}

	public void setMontant(BigDecimal montant) {
		this.montant = montant;
	}

	public void setNoteDeFrais(NoteDeFrais noteDeFrais) {
		this.noteDeFrais = noteDeFrais;
	}

}
