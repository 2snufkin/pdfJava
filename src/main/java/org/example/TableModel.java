package org.example;

import java.util.ArrayList;
import java.util.List;

public class TableModel {

        private List<String> titles;
        private List<ArrayList<String>> ColumnValues;

    public TableModel(List<String> titles, List<ArrayList<String>> columnValues) {
        this.titles = titles;
        ColumnValues = columnValues;
    }

    public List<String> getTitles() {
        return titles;
    }

    public void setTitles(List<String> titles) {
        this.titles = titles;
    }

    public List<ArrayList<String>> getColumnValues() {
        return ColumnValues;
    }

    public void setColumnValues(List<ArrayList<String>> columnValues) {
        ColumnValues = columnValues;
    }
}
