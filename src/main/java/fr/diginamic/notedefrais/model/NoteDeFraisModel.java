package fr.diginamic.notedefrais.model;

import java.util.List;


public class NoteDeFraisModel {	
	private Integer id;
	

	public NoteDeFraisModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public NoteDeFraisModel(Integer id2, List<LigneDeFraisModel> items2) {
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	private List<NoteDeFraisModel> items;

	public List<NoteDeFraisModel> getItems() {
		return items;
	}

	public void setItems(List<NoteDeFraisModel> items) {
		this.items = items;
	}

}