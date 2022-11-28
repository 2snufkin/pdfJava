package org.example;

import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.List;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
         String PATH = "C:\\Tech\\code\\Learn\\pdf\\test.pdf";
         String titleh1 = "Détail de l’étude";
         String maladiesTitle = "Maladies (CIM- 10)";
         String promotionTitle = "Promotion";
         String donneClinique = "Données cliniques";
         String isDonneClinique = "Oui";
        ArrayList<String> maladiesList = new ArrayList<>();
        maladiesList.add("•Code1 – libellé 1");
        maladiesList.add("Code – libellé 12");
        String[] titles = {"Coordinateur", "Chef de projet", "URC"};
        String[][] data = {
                {"Syvin Dupot", "Eren Woid", "Damien"},
                {"one", "" , "two"}};
        TablePdf tablepdf = new TablePdf(titles, data );


//        pdf creation
        try{
            Document doc = PdfTools.createPdfDoc(PATH);
//            adding h1
            Paragraph h1 = PdfTools.createH1(titleh1, "blue");
            h1.setTextAlignment(TextAlignment.CENTER);
            doc.add(h1);
//            insert donne clinique
            Paragraph donneclinique = PdfTools.createTitleDescription(donneClinique, isDonneClinique );
            doc.add(donneclinique);
//            insert compl
            Paragraph h2 = PdfTools.createH3(maladiesTitle, "black");
            doc.add(h2);
//            add list maladies
            List listMaladies = PdfTools.addItemsToList(maladiesList);
            doc.add(listMaladies);
//            add title promtion
            Paragraph promo = PdfTools.createH2(promotionTitle, "black");
            doc.add(promo);
//            add table
            Table tableToAdd = PdfTools.createTableObject(tablepdf);
            doc.add(tableToAdd);




            doc.close();


        } catch (FileNotFoundException e){
            System.out.println(e);

        }


    }
}