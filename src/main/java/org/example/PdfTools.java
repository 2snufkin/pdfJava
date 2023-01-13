package org.example;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvasConstants;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.Style;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.borders.GrooveBorder;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.VerticalAlignment;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

public class PdfTools {
    public final static DeviceRgb BLUE = new DeviceRgb(25, 118, 210);
    public static final DeviceRgb RED = new DeviceRgb(221, 55, 5);
    public static final DeviceRgb GRAY = new DeviceRgb(128, 128, 128);

    public static final float pageWidth = PageSize.A4.getWidth();


    public static void addFooter(PdfDocument pdfDocument, ArrayList<String> footerStrings, Document doc) {
        int n = pdfDocument.getNumberOfPages();
        Paragraph footerP;
        for (int page = 1; page <= n; page++) {
            String pageS = String.format("Page %s Of %s", page, n);
            footerP = new Paragraph();
            Style style = new Style().setPadding(55f);
            for (String footer : footerStrings) {
                if (footer == "page") {
                    Text pageN = new Text(pageS);
                    footerP.add(pageN);
                } else {
                    Text text = new Text(footer).addStyle(style);
                    footerP.add(text);
                }
            }
            footerP.setFontColor(GRAY).setFontSize(11f);
            doc.showTextAligned(footerP, 297.5f, 20, page,
                    TextAlignment.CENTER, VerticalAlignment.MIDDLE, 0);
        }
    }

    /**
     * Create a pdf doc with one page and return the document
     *
     * @param path_pdf the path on your drive
     * @return Document object
     * @throws FileNotFoundException if the path is wrong, it must finish with *.pdf
     */
    public static Document createA4PdfDoc(String path_pdf, Float fontSize) throws FileNotFoundException {

        PdfWriter pdfWriter = new PdfWriter(path_pdf);
        PdfDocument pdfDocument = new PdfDocument(pdfWriter);
        return new Document(pdfDocument, PageSize.A4).setFontSize(fontSize);
    }


    /**
     * add all the items in an arrayList to an itext7 List that can be added to a pdf doc as unsorted list
     *
     * @param listItems ArrayList of type String
     * @return itext7 List
     */
    public static com.itextpdf.layout.element.List addItemsToList(ArrayList<String> listItems) {
        com.itextpdf.layout.element.List listOfItems = new com.itextpdf.layout.element.List();
        for (String ele : listItems) listOfItems.add(ele);
//        attention , it will add also a space after the bullet point since I added a space
        Text symbol = new Text("\u2022 ");
        return listOfItems.setListSymbol(symbol);
    }

    /**
     * create a paragraph that serves as the main title
     *
     * @param title the string
     * @return the paragraph
     */
    public static Table createSection(String title, boolean isMain, boolean isMargin) {
        float[] width = {pageWidth};
        Paragraph paragraph = new Paragraph(title.toUpperCase()).setBold().setMarginLeft(pageWidth / 3);
        Table table = new Table(width);
        if (isMargin) {
            table.setMarginBottom(10f).setMarginTop(10f);

        }
        Cell cell = new Cell().add(paragraph);
        if (isMain) {
            Border border = new GrooveBorder(BLUE, 1);
            cell.setBorder(border);
            return table.addCell(cell);
        } else {
            Border border = new GrooveBorder(RED, 1);
            cell.setBorder(border);
            return table.addCell(cell);
//            return new Cell().setBorder(border).add(title).setFontColor(Color.WHITE);

        }

    }

    /**
     * Create a cell that serves as a container. In the cell: title + list of unsorted items
     *
     * @param title     title
     * @param listItems list of strings to be presented as unsorted list
     * @return Cell, the container
     */
    public static Cell createDivCell(String title, ArrayList<String> listItems) {
        Cell cell = new Cell().setMarginTop(5f);
        Paragraph titlePara = PdfTools.createTitle(title);
        com.itextpdf.layout.element.List listItemsPdf = PdfTools.addItemsToList(listItems);
        cell.add(titlePara).add(listItemsPdf);
        cell.setBorder(Border.NO_BORDER);
        return cell;
    }

//    public static Cell createDivCellWithBorder(String title, ArrayList<String> listItems){
//        Cell cell = new Cell() ;
//        Paragraph titlePara = PdfTools.createTitle(title);
//        com.itextpdf.layout.element.List listItemsPdf = PdfTools.addItemsToList(listItems);
//        cell.add(titlePara).add(listItemsPdf);
//        return cell.setBorder(new SolidBorder(1));
//    }

    /***
     * Create a cell that serves as a container. In the cell: title + 1 paragraph
     * @param title type String
     * @param paragraph type String
     * @return Cell object , a container
     */
    public static Cell createDivCell(String title, String paragraph) {
        Cell cellPara = new Cell();
        cellPara.setBorder(Border.NO_BORDER);
        Paragraph paragraphToAdd = new Paragraph(paragraph);
        Paragraph titlePara = PdfTools.createTitle(title);
        cellPara.add(titlePara).add(paragraphToAdd);
        return cellPara;
    }

    /***
     * Create Watermark at the bottom of the page
     * @param path a String the points to the path of the file
     * @return an Image
     */
    public static Image createWatermark(String path) {
        try {
            ImageData img = ImageDataFactory.create(path);
            Image image = new Image(img);
            return image.setFixedPosition(0, 0).setOpacity(0.3f);

        } catch (MalformedURLException e) {
            System.out.println(e);
            return null;
        }


    }

    /***
     * Create Logo in the top right corner of the file, will appear only on the first page
     * @param path
     * @return
     */
    public static Image createLogo(String path) {
        try {
            ImageData img = ImageDataFactory.create(path);
            Image image = new Image(img);
            image.setFixedPosition(PageSize.A4.getWidth() - image.getImageScaledWidth(), PageSize.A4.getHeight() - image.getImageScaledHeight());
            return image;

        } catch (MalformedURLException e) {
            System.out.println(e);
            return null;
        }
    }

    /**
     * Create subsecection, it's the same as section but with a diffrent color
     *
     * @param title
     * @return
     */
    public static Cell createSubSection(String title) {
        IBlockElement titleB = new Paragraph(title);
        return new Cell().setBackgroundColor(RED).add(titleB);
    }

    /**
     * create a paragraph that serves as a subtitle to the main title
     *
     * @param title the string
     * @return the paragraph
     */
    public static Paragraph createTitle(String title) {
        Paragraph paragraph = new Paragraph(title);
        paragraph.setFontSize(11f).setBold().setUnderline(BLUE, .75f, 0, 0, -1 / 8f, PdfCanvasConstants.LineCapStyle.BUTT)
                .setFontColor(BLUE);
        return paragraph;
    }


    /**
     * convert a TablePdf object that contains the headers and the data to a iText7 Table
     *
     * @param tableModel TablePdf Object (not a native object)
     * @return Table. to add it to the doc. call the add() method
     */


    public static Table createTableFromModel(TableModel tableModel) {
        List<String> listTitles = new ArrayList<>(tableModel.getTitles());
        int numberOfCol = listTitles.size();
        float[] colWidth = new float[numberOfCol];
        for (int i = 0; i < numberOfCol; i++) {
            colWidth[i] = 200f;
        }
//      config of the table's columns. The number of col. is calculated by the number of ele in the colWidth array
        Table tablePdfObj = new Table(colWidth);
//      all that is rest to be done is adding Cells
//      adding the first line of the table == headers
        for (String title : listTitles) {
            IBlockElement titleB = new Paragraph(title);
            tablePdfObj.addCell(new Cell().add(titleB).setBold());
        }
//        lopping the listTitles of list and adding to the table
        List<ArrayList<String>> listOfList = new ArrayList<>(tableModel.getColumnValues());
        for (ArrayList<String> listcol : listOfList) {
            com.itextpdf.layout.element.List listCollabo = addItemsToList(listcol);
            tablePdfObj.addCell(new Cell().add(listCollabo));
        }
        return tablePdfObj;
    }

    /**
     * Create a table with x cells
     *
     * @param listValues
     * @return a Table
     */
    public static Paragraph createParagraphOneRow(ArrayList<String> listValues) {
//        create a Table object and pass to its constructor the width of each col. as an arg

        Paragraph paragraph = new Paragraph();
//        create Cell Object
        for (String value : listValues) {
            Style style = new Style().setMargin(5f);
            Text blockVal = new Text(value).addStyle(style);
            paragraph.add(blockVal);
        }
        return paragraph;

    }


    //    https://kb.itextpdf.com/home/it7kb/examples/page-events-for-headers-and-footers
//    public static void addFooter(Table table, PdfDocument pdfDoc, Document doc) {
//        PdfFormXObject placeholder =  new PdfFormXObject(new Rectangle(0, 0, 20, 20));
//        PageSize ps = pdfDoc.getDefaultPageSize();
//        for (int i = 1; i <= pdfDoc.getNumberOfPages(); i++) {
//            Rectangle pageSize = pdfDoc.getPage(i).getPageSize();
//            float x = pageSize.getWidth() / 2;
//            float y = pageSize.getBottom() + 20;
//            table.setFixedPosition(doc.getLeftMargin(), doc.getBottomMargin(), ps.getWidth() - doc.getLeftMargin() - doc.getRightMargin());
//            PdfPage page  = pdfDoc.getPage(i);
//             PdfCanvas pdfCanvas = new PdfCanvas(
//                    page.getL0stContentStream(), page.getResources(), pdfDoc);
//            Canvas canvas = new Canvas(pdfCanvas, pdfDoc, pageSize);
//            Paragraph p = new Paragraph()
//                    .add("Page ").add(String.valueOf(i)).add(" of");
//            canvas.showTextAligned(p, x, y, TextAlignment.RIGHT);
//
//            pdfCanvas.addXObject(placeholder, x + 4.5f, y - 3);
//            pdfCanvas.release();
//        }
//    }


    /**
     * Create a paragraph in this format <bold>title : </bold> <p> description</p>
     *
     * @param title       the title
     * @param description the description
     * @return a Paragraph
     */
    public static Paragraph createTitleDescription(String title, String description) {
        Text titleText = new Text(title + " : ").setBold();
        Text descText = new Text(description + ".");
        Paragraph paragraph = new Paragraph();
        paragraph.add(titleText);
        paragraph.add(descText);
        return paragraph;
    }

    public static Paragraph rotateTableCell90dg(String cellContent) {
        return new Paragraph(cellContent).setRotationAngle(90 * Math.PI / 180)
                .setTextAlignment(TextAlignment.CENTER)
                .setPaddingRight(5f).setPaddingLeft(5f);
    }

    /**
     * A method to create a Div that get a Table as an argument. Every Cell of the table must contains a Paragraph that was
     * returned from the function rotateTableCell90dg
     * @param tabledWithRotatedPara the table to be rotated
     * @return a 90 degree rotated div that contains the table
     */
    public static Div createRotatedDiv(Table tabledWithRotatedPara) {
        Div divT = new Div();
        divT.add(tabledWithRotatedPara);
        divT.setRotationAngle(-Math.PI / 2f);
        return divT;
    }
    public static Table createEmptyTable(int numberOfRow){
        float[] colWidthArray = new float[numberOfRow];
        for (int i = 0; i < numberOfRow; i++) {
            colWidthArray[i] = 200f;
        }
        return  new Table(colWidthArray);
    }


}
