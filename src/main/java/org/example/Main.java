package org.example;

import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.border.SolidBorder;
import com.itextpdf.layout.element.AreaBreak;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toCollection;

public class Main {
    public static void main(String[] args) {


//        TODO set font
//        MISE eN PLACE
        String PATH = "C:\\Users\\Pini\\IdeaProjects\\pdfJava\\test.pdf";
        String sectionDetails = "Détail de l’étude";
        String etudeName = "Hibiscus(HUB)";
        String donnesCliniqueT = "Données cliniques";
        String detailsDate = "du 01/07/2022 au 30/06/2023 sur 10 patients et 4 Visites.";
        String donnescliniquesData = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. " +
                "Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown" +
                " printer took a galley of type and scrambled it to";
        String maladiesTitle = "Maladies (CIM- 10)";
        String promotionTitle = "Promotion";
        String collectionTitle = "Collections";
        ArrayList<String> maladiesList = new ArrayList<>();
        maladiesList.add("F40- f48 : troubles névrotiques, troubles liés à des facteurs de stress et troubles somatoformes");
        maladiesList.add("F35- f18 : troubles névrotiques");
        java.util.List<String> listCollection = Stream.generate(() -> "Collection").limit(3).collect(toCollection(ArrayList::new));
        ArrayList<String> arraylistCollection = new ArrayList<>(listCollection.size());
        arraylistCollection.addAll(listCollection);
        String[] titles = {"Coordinateur", "Chef de project", "URC"};
        java.util.List<String> titlesArray = (java.util.List<String>) Arrays.asList(titles);
        ArrayList<String> listCoordin = new ArrayList<>();
        listCoordin.add("Tomhas OJFHGS");
        listCoordin.add("Vladi ORUIGH");
        listCoordin.add("Marian THGF");
        ArrayList<String> listChef = new ArrayList<>();
        listChef.add("Fatima OJFHGS");
        listChef.add("Nico ORUIGH");
        ArrayList<String> listURC = new ArrayList<>();
        java.util.List<ArrayList<String>> listOfCordon = new ArrayList<>();
        listOfCordon.add(listCoordin);
        listOfCordon.add(listChef);
        listOfCordon.add(listURC);
        java.util.List<String> listCRO = new ArrayList<>();
        listCRO.add("CH: Covance");
        ArrayList<String> footer = new ArrayList<>();
        footer.add("29.11.22");
        footer.add("Etude name");
        footer.add("1/1");

        TableModel tableModel = new TableModel(titlesArray, listOfCordon);


//        pdf creation
        try {

            PdfWriter pdfWriter = new PdfWriter(PATH);
            PdfDocument pdfDocument = new PdfDocument(pdfWriter);
            Header headerEvent =     new Header("The Strange Case of Dr. Jekyll and Mr. Hyde");
            PageXofY pageNo =     new PageXofY(pdfDocument);
            pdfDocument.addEventHandler(PdfDocumentEvent.START_PAGE, headerEvent);
            pdfDocument.addEventHandler(PdfDocumentEvent.END_PAGE, pageNo);
            Document doc = new Document(pdfDocument);


//  **********************************ETUDE  details section******************************************




            Table cell = PdfTools.createSection(sectionDetails, true, false);

//            Image watermark = PdfTools.createLogo("C:\\Tech\\code\\Learn\\pdf\\img\\logo_gatsby.png");
//            doc.add(watermark);
            doc.add(cell);
//          General details
            Paragraph general = PdfTools.createTitleDescription(etudeName, detailsDate);
            doc.add(general);
//          Donne clinique title
            Paragraph donneCliniique = PdfTools.createTitleDescription(donnesCliniqueT, "oui");
            doc.add(donneCliniique);
            doc.add(new AreaBreak());
            //Complem
            Cell donneComp= PdfTools.createDivCell("Complém", donnescliniquesData);
            doc.add(donneComp);

//          Maladies
            Cell maladiesCell = PdfTools.createDivCell(maladiesTitle, maladiesList);
            doc.add(maladiesCell);


//          Collection
            Cell collectionCell = PdfTools.createDivCell(collectionTitle, arraylistCollection);
            doc.add(collectionCell);
//  **********************************PROMOTION  section******************************************
            Table promotion = PdfTools.createSection(promotionTitle, true, true);
            doc.add(promotion);

            Table table = PdfTools.createTableFromModel(tableModel);
            //            add table
            doc.add(table);
//            add CRO Title
            Cell cro = PdfTools.createDivCell("CRO", maladiesList);
            doc.add(cro);

//            add autre info
            Cell autreInfoCell = PdfTools.createDivCell("Autres informations", donnescliniquesData);
            doc.add(autreInfoCell);
            //  **********************************Promotion2******************************************
            Table promotion2 = PdfTools.createSection(promotionTitle, true, true);
            doc.add(promotion2);

            Table table2 = PdfTools.createTableFromModel(tableModel);
            //            add table
            doc.add(table2);
//            add CRO Title
            Cell cro2 = PdfTools.createDivCell("CRO", maladiesList);
            doc.add(cro2);

//            add autre info
            Cell autreInfoCell2 = PdfTools.createDivCell("Autres informations", donnescliniquesData);
            doc.add(autreInfoCell2);


//  **********************************SITES INVISTE  section******************************************
            doc.add(PdfTools.createSection("Sites Inviste", true, false));
            doc.add(PdfTools.createSection("01: CH bress", false, false));
            doc.add(PdfTools.createDivCell("Gynécologie Obstétrique", listCoordin));
            doc.add(PdfTools.createDivCell("Gynécologie Non-Obstétrique", listCoordin));
            doc.add(PdfTools.createSection("01: CH Lyon", false, false));
            doc.add(PdfTools.createDivCell("Gynécologie Obstétrique", listCoordin));
            doc.add(PdfTools.createDivCell("Gynécologie Non-Obstétrique", listCoordin));
            Border border = new SolidBorder(1);
            float widthTable[] = {200f};
            Table tbl = new Table(widthTable);
            tbl.addCell(new Cell().setBorder(border));
            doc.add(tbl);

            Table tableFooter = PdfTools.createTableOneRow(footer);
            Paragraph p = new Paragraph();

//            add section sites invest
//            event.writeTotal(pdfDocument);
            pageNo.writeTotal(pdfDocument);
            doc.close();

//            add semi title


        } catch (FileNotFoundException e) {
            System.out.println(e);

        }


    }



}