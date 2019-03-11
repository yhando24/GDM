package fr.diginamic.expenseAccount.model;

public enum ExpenseAccountStatusEnum {
	EN_ATTENTE("En attente"),
	VALIDEE("Validée"),
	REJETEE("Rejetée"),
	INITIAL("Inititial"),
	ANNULEE("Annulée");
	
	private String e;

	ExpenseAccountStatusEnum(String e) {
        this.e = e;
    }

    public String url() {
        return e;
    }
}
