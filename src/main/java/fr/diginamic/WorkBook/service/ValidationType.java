package fr.diginamic.WorkBook.service;

public class ValidationType {
    private int column;
    private String[] values;

    public ValidationType() {
        super();
    }

    public ValidationType(int column, String[] values) {
        super();
        this.column = column;
        this.values = values;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public String[] getValues() {
        return values;
    }

    public void setValues(String[] values) {
        this.values = values;
    }
}