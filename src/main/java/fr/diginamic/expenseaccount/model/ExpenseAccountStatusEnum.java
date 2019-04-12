package fr.diginamic.expenseaccount.model;

public enum ExpenseAccountStatusEnum {
	ATTENTE("Attente"),
	VALIDEE("Validée"),
	REJETEE("Rejetée"),
	INITIAL("Initial"),
	ANNULEE("Annulée");
	
	private String e;

	ExpenseAccountStatusEnum(String e) {
        this.e = e;
    }

    public String url() {
        return e;
    }
}

