package org.example;

import com.itextpdf.io.font.FontConstants;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.color.DeviceRgb;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.List;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;

 public class PdfService {



     public static final String font_emb = "emb_fonts/Caveat-Regular.ttf";

    public void buildPdfWithList(String path, ArrayList<String> listToAdd) {
        try {
            List listPdf = new List();
            Document doc = createPdfDoc(path);
            for (String ele : listToAdd) {
                String elemToadd = ele.toLowerCase();
                listPdf.add(elemToadd);
            }
            doc.add(listPdf);
            doc.close();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);

        }


    }



    public PdfFont createEmbdFont(String path){
        PdfFont embdFont = null;
        try {
            embdFont = PdfFontFactory.createFont(path, true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return embdFont;
    }
    public void createPdfWithParagraph(String path, Paragraph para){
        try{
            Document doc = createPdfDoc(path);
            doc.add(para);
            closeDocument(doc);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

//     public void createPdfWithTableObject(String path, TablePdf table){
//         try{
//             Document doc = createPdfDoc(path);
//             float[] colWidth = {200f, 200f,200f};
//             int numberOfCol = colWidth.length;
////             config of the table's columns. The number of col. is caculated by the number of ele in the colWidth array
//             Border border = new GrooveBorder(new DeviceRgb(0,180,180),3f);
////             Applying the border to the table
//             Table tablePdfObj = new Table(colWidth).setBorder(border);
////             all that is rest to be done is adding Cells
//             String[] headers = table.getTitles();
//             String[][] rows = table.getRowsTables();
//             for (String title: headers){
//                 tablePdfObj.addCell(new Cell().add(title).setBold().setBackgroundColor(Color.PINK).setFontColor(Color.WHITE));
//             }
//             for (String line[]: rows){
//                 if (line.length == numberOfCol){
//                     for (String element : line){
//                         tablePdfObj.addCell(new Cell().add(element));
//                     }
//
//                 }
//                 else{
//                     int complete = numberOfCol - line.length;
//                     for (String element : line){
//                         tablePdfObj.addCell(new Cell().add(element));
//                     }
//                     for (int i=0; i< complete; i++){
//                         tablePdfObj.addCell(new Cell().add("-"));
//
//                     }
//                 }
//             }
//
//             doc.add(tablePdfObj);
//             closeDocument(doc);
//
//         } catch (FileNotFoundException e) {
//             throw new RuntimeException(e);
//         }
//     }

    public Paragraph applyFontToPara(Paragraph par, PdfFont pdfFont){
         return par.setFont(pdfFont);
    }

    public boolean buildPdfWithImageAndPara(String path, String p, int fontsize, String imagePath) {
        try {
            Document doc = createPdfDoc(path);
            Paragraph paragraph = createParagraph(p, fontsize);
            Paragraph p2 = new Paragraph("hard coded para").setFontColor(new DeviceRgb(0,255,0));

            doc.add(paragraph);
            doc.add(p2);
            doc.add(createImage(imagePath));
            closeDocument(doc);
            return true;

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void closeDocument(Document doc) {
        doc.close();
    }



    public Document createPdfDoc(String path_pdf) throws FileNotFoundException {
//        FilesMng.createAfile(path_pdf);
        PdfWriter pdfWriter = new PdfWriter(path_pdf);
//        create a pdf doc with no pages
        PdfDocument pdfDocument = new PdfDocument(pdfWriter);
//        add a page
        PdfPage page1 = pdfDocument.addNewPage();


        PdfCanvas canvas = new PdfCanvas(page1);
        canvas.rectangle(100, 100, 100, 100).fill();
//       import com.itextpdf.layout.Document the document is where I will insert the text
        Document document = new Document(pdfDocument);
        return document;

    }

    public Paragraph createParagraph(String p, int fontsize) {
        Paragraph paragraph = new Paragraph(p).setFontColor(Color.BLUE);
        paragraph.setFontSize(fontsize);
        return paragraph;
    }

    public PdfFont createTimeRomanFontItalic(){
        PdfFont font = null;
        try {
            font = PdfFontFactory.createFont(FontConstants.TIMES_ITALIC);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return font;
    }

    public Image createImage(String path) {
        try {
            ImageData imageData = ImageDataFactory.create(path);
            Image image1 = new Image(imageData);
            return image1;
        } catch (MalformedURLException e) {
            System.out.println("probleme avec the image");
            System.out.println(e);
            return null;
        }

    }




    public Paragraph addTextToParagraph(ArrayList<Text> listofText){
        Paragraph paragraph = new Paragraph();
        for (Text text : listofText){
            paragraph.add(text).setFont(createTimeRomanFontItalic());
        }
        return paragraph;
    }








    public static void main(String[] args) throws FileNotFoundException {
        PdfService service = new PdfService();
//***************************************************

//        create table
        String[] titles = {"Coordinateur", "Chef de projet", "URC"};
        String[][] data = {
                {"Syvin Dupot", "Eren Woid", "Damien"},
                {"one", "" , "two"}};
//
//***************************************************
        ArrayList<String> arrayList = new ArrayList<String>();
        arrayList.add("First");
        arrayList.add("sEcond");
        arrayList.add("third");
        arrayList.add("4");
        arrayList.add("five");
//Font and Text
        Text etude = new Text("ETUDE4 (ET4) ");
        Text dates = new Text("du 01/07/2022 au 30/06/2023 sur ");
        Text pat = new Text("10 patients. ");
        ArrayList<Text> textArry = new ArrayList<Text>();
        textArry.add(etude);
        textArry.add(dates);
        textArry.add(pat);
        Paragraph para = service.addTextToParagraph(textArry);


        service.buildPdfWithImageAndPara("xxx.pdf", "25.11", 35, "img/pic.jpg");
//        service.createPdfWithParagraph("with font.pdf", para);
        //***************************************************

//work with embd fonts
        PdfFont pdfFontmbd = service.createEmbdFont(font_emb);
        Paragraph paraWithFont = new Paragraph("let's test the new font");
//        service.createPdfWithParagraph("withEmbdFont.pdf", service.applayFontToPara(paraWithFont, pdfFontmbd));
//    service.buildPdfWithList("list.pdf", arrayList);
//    service.buildPdfWithImageAndPara("imgPara2.pdf", "this is p", 25, "img/pic.jpg");
    }




}

