package at.fhtw.bif.swen.presentation.service.ReportGenerator;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.concurrent.CompletableFuture;

public abstract class ReportGenerator {
    protected PdfWriter writer;
    protected PdfDocument pdf;
    protected Document document;


    public ReportGenerator() {

    }

    public CompletableFuture<Void> generate() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                this.setHeader();
                this.setBody();
                this.document.close();
                this.pdf.close();
                this.writer.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return null;
        });
    }
    abstract void setHeader() throws IOException;
    abstract void setBody() throws IOException;
}
