package fr.diginamic.mission.model;

public enum TransportEnum {


  	TRAIN("Train"),
  	AVION("Avion"),
  	TAXI("Taxi"),
  	BUS("Bus"),
	BATEAU("Bateau"),
	NAVETTE_SPATIALE("Navette Spatiale"),
	VOITURE("Voiture"),
	VELO("Velo"),
	HELICOPTERE("Helicoptere");

  	
	private String name;
  	
	private TransportEnum(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
