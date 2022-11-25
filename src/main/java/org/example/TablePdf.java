package org.example;

public class TablePdf {
    private String[] titles;
    private String[][] rowsTables;

    public TablePdf(String[] titles, String[][] rowsTables) {
        this.titles = titles;
        this.rowsTables = rowsTables;
    }

    public String[] getTitles() {
        return titles;
    }

    public void setTitles(String[] titles) {
        this.titles = titles;
    }

    public String[][] getRowsTables() {
        return rowsTables;
    }

    public void setRowsTables(String[][] rowsTables) {
        this.rowsTables = rowsTables;
    }
}
