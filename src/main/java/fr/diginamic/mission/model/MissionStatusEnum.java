package fr.diginamic.mission.model;

public enum MissionStatusEnum {
	

	
	
	  	EN_ATTENTE("En attente"),
	  	VALIDE("Validé"),
	  	REJETE("Rejeté"),
	  	INITIAL("Initial"),
		ANNULE("Annulé");

	  	
		private String name;
	  	
		private MissionStatusEnum(String name) {
			this.name = name;
		}
		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
}
