package org.example;
import org.example.PdfTools;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
         String PATH = "C:\\Users\\pzrouya\\Documents";
         String titleh1 = "Détail de l’étude";
         String maladiesTitle = "Maladies (CIM- 10)";
         String promotionTitle = "Promotion";
         String donneClinique = "Données cliniques";
         String isDonneClinique = "Oui";
        ArrayList<String> maladiesList = new ArrayList<String>();
        maladiesList.add("•Code1 – libellé 1");
        maladiesList.add("Code – libellé 12");
        String[] titles = {"Coordinateur", "Chef de projet", "URC"};
        String[][] data = {
                {"Syvin Dupot", "Eren Woid", "Damien"},
                {"one", "" , "two"}};
        TablePdf tablepdf = new TablePdf(titles, data );
        TablePdf table1Pdf = new TablePdf(titles, data);



    }
}