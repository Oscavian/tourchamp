package at.fhtw.bif.swen.presentation.service.ReportGenerator;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;

import java.io.IOException;

public abstract class ReportGenerator {
    protected PdfWriter writer;
    protected PdfDocument pdf;
    protected Document document;


    public ReportGenerator() {

    }

    abstract void generate() throws IOException;
    abstract void setHeader() throws IOException;
    abstract void setBody() throws IOException;
}
