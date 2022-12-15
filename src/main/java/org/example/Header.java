package org.example;

import com.itextpdf.kernel.events.Event;
import com.itextpdf.kernel.events.IEventHandler;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.property.TextAlignment;


public class Header implements IEventHandler {
    String header;


    public Header(String header) {
        this.header = header;
        System.out.println("pipi");
    }


    @Override
    public void handleEvent(Event event) {
        System.out.println("event handler");

        PdfDocumentEvent docEvent = (PdfDocumentEvent) event;
        System.out.println("event handler");
        PdfDocument pdf = docEvent.getDocument();
        PdfPage page = docEvent.getPage();
        if (pdf.getPageNumber(page) == 1) return;
        Rectangle pageSize = page.getPageSize();
        PdfCanvas pdfCanvas = new PdfCanvas(
                page.getLastContentStream(), page.getResources(), pdf);
        Canvas canvas = new Canvas(pdfCanvas, pdf, pageSize);
        canvas.showTextAligned(header,
                pageSize.getWidth() / 2,
                pageSize.getTop() - 30, TextAlignment.CENTER);
    }
}