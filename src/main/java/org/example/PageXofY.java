package org.example;

import com.itextpdf.kernel.events.Event;
import com.itextpdf.kernel.events.IEventHandler;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.kernel.pdf.xobject.PdfFormXObject;

import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.VerticalAlignment;

import java.util.ArrayList;

public class PageXofY implements IEventHandler {

 protected PdfFormXObject placeholder;

// the width and the height of the placeholder
protected float side = 20;
protected float x = 300;
 protected float y = 25;
// push the total page number
 protected float space = 4.5f;
 protected float descent = 3;

 protected String study;
 protected String date;
 public PageXofY(String date, String study)  {
        this.study = study;
        this.date = date;
        placeholder =
                new PdfFormXObject(new Rectangle(0, 0, side, side));
    }
    @Override
    public void handleEvent(Event event) {
//     getting the event and casting to PdfDOcumeneVENT
        PdfDocumentEvent docEvent = (PdfDocumentEvent) event;
//        Getting the doc from the event
        PdfDocument pdf = docEvent.getDocument();
//        getting the current page, it's important for creating a Canvas
        PdfPage page = docEvent.getPage();
        int pageNumber = pdf.getPageNumber(page);
        Rectangle pageSize = page.getPageSize();
        PdfCanvas pdfCanvas = new PdfCanvas(
                page.getLastContentStream(), page.getResources(), pdf);
//         create a Canvas object using the PdfPage, the PdfDocument and the rectangle
        Canvas canvas = new Canvas(pdfCanvas,  pageSize);
        String pageNo = pageNumber + "/";
        ArrayList<String> footerList = new ArrayList<String>();
        footerList.add(study);
        footerList.add(date);
        footerList.add(pageNo);

        Paragraph oneRow = PdfTools.createParagraphOneRow(footerList);
//        adding the row TextAlignment.RIGHT is for the total number
        canvas.showTextAligned(oneRow, x, y, TextAlignment.RIGHT);
//        placing the placeholder
        pdfCanvas.addXObjectAt(placeholder, x + space, y - descent);

        pdfCanvas.release();
    }


    public void writeTotal(PdfDocument pdf) {
        Canvas canvas = new Canvas(placeholder, pdf);
        canvas.showTextAligned(String.valueOf(pdf.getNumberOfPages()),
                0, descent, TextAlignment.LEFT);
    }
}