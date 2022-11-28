package org.example;

import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.color.DeviceRgb;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.border.GrooveBorder;
import com.itextpdf.layout.element.*;

import java.util.ArrayList;

public   class PdfTools {

    /**
     * add all the items in an arrayList to a itext7 List that can be added to a pdf doc as unsorted list
     * @param listItems ArrayList of type String
     * @return itext7 List
     */
    public static List addItemsToList(ArrayList<String> listItems){
        List listOfItems = new List();
        for (String ele: listItems) listOfItems.add(ele);
        return listOfItems;
    }

    /**
     * create a paragraph that serves as the main title
     *
     * @param title the string
     * @param color the color of the paragraph. blue or black
     * @return the paragraph
     */
    public static  Paragraph createH1(String title, String color) {
        Paragraph paragraph = new Paragraph(title);
        paragraph.setFontSize(25f);
        return applyColor(paragraph, color);
    }

    /**
     * create a paragraph that serves as a subtitle to the main title
     *
     * @param title the string
     * @param color the color of the paragraph. blue or black
     * @return the paragraph
     */
    public static  Paragraph createH2(String title, String color) {
        Paragraph paragraph = new Paragraph(title);
        paragraph.setFontSize(22.5f);
        return applyColor(paragraph, color);
    }
    /**
     * create a paragraph that serves as a subtitle to the subtitle
     *
     * @param title the string
     * @param color the color of the paragraph. blue or black
     * @return the paragraph
     */
    public static  Paragraph createH3(String title, String color) {
        Paragraph paragraph = new Paragraph(title);
        paragraph.setFontSize(20f);
        return applyColor(paragraph, color);
    }

    /**
     * convert a TablePdf object that contains the headers and the data to a iText7 Table
     *
     * @param table TablePdf Object (not a native object)
     * @return Table. to add it to the doc. call the add() method
     */
    public static  Table createTableObject(TablePdf table) {
        int numberOfCol = table.getTitles().length;
        float[] colWidth = new float[numberOfCol];
        for (int i = 0; i < numberOfCol; i++) {
            colWidth[i] = 200f;
        }

//             config of the table's columns. The number of col. is caculated by the number of ele in the colWidth array
        Border border = new GrooveBorder(new DeviceRgb(0, 180, 180), 3f);
//             Applying the border to the table
        Table tablePdfObj = new Table(colWidth).setBorder(border);
//             all that is rest to be done is adding Cells
        String[] headers = table.getTitles();
        String[][] rows = table.getRowsTables();
        for (String title : headers) {
            tablePdfObj.addCell(new Cell().add(title).setBold().setBackgroundColor(Color.PINK).setFontColor(Color.WHITE));
        }
        for (String line[] : rows) {
            if (line.length == numberOfCol) {
                for (String element : line) {
                    tablePdfObj.addCell(new Cell().add(element));
                }

            } else {
                int complete = numberOfCol - line.length;
                for (String element : line) {
                    tablePdfObj.addCell(new Cell().add(element));
                }
                for (int i = 0; i < complete; i++) {
                    tablePdfObj.addCell(new Cell().add("-"));

                }
            }
        }
        return tablePdfObj;

    }

    /**
     * Create a paragraph in this format <bold>title : </bold> <p> description</p>
     * @param title the title
     * @param description the description
     * @return a Paragraph
     */
    public static   Paragraph createTitleDescription(String title, String description){
        Text titleText  = new Text(title + " : ").setBold();
        Text descText  = new Text(description + ".");
        Paragraph paragraph = new Paragraph();
        paragraph.add(titleText);
        paragraph.add(descText);
        return paragraph;
    }

    public static  Paragraph applyColor(Paragraph paragraph, String color) {
        String colorInput = color.toLowerCase();
        switch (colorInput) {
            case "black": {
                paragraph.setFontColor(Color.BLACK);
                break;
            }
            case "blue": {
                paragraph.setFontColor(Color.BLUE);
                break;
            }
            default:
                paragraph.setFontColor(Color.BLACK);

        }
        return paragraph;
    }

}
