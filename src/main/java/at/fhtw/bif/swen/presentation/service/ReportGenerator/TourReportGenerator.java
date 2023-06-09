package at.fhtw.bif.swen.presentation.service.ReportGenerator;

import at.fhtw.bif.swen.dto.TourDTO;
import at.fhtw.bif.swen.dto.TourLogDTO;
import at.fhtw.bif.swen.presentation.service.MapQuestAPIService.TourMapData;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.List;
import com.itextpdf.layout.element.ListItem;
import com.itextpdf.layout.element.Paragraph;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.CompletableFuture;

public class TourReportGenerator extends ReportGenerator {

    private TourDTO tour;
    private Image image;
    private final Logger logger = LogManager.getLogger(getClass().getName());


    public TourReportGenerator (File file, TourDTO tourDTO) {
        this.tour = tourDTO;
        try {
            this.writer = new PdfWriter(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        this.pdf = new PdfDocument(this.writer);
        this.document = new Document(this.pdf);
    }

    @Override
    void setHeader() throws IOException {
        Paragraph header = new Paragraph(tour.getName()).
                setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA))
                .setFontSize(18)
                .setBold();
        document.add(header);
        document.add(this.image);
    }

    @Override
    void setBody() throws IOException {
        this.setTourDetails();
        this.setTourLogs();
    }

    public void setImage(String imageUrl) {
        ImageData imageData = null;
        try {
            imageData = ImageDataFactory.createJpeg(new URL(imageUrl));
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        this.image = new Image(imageData);
    }

    private void setTourDetails() throws IOException {
        Paragraph tourDetailsHeader = new Paragraph("Details").
                setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA))
                .setFontSize(14)
                .setBold();
        document.add(tourDetailsHeader);

        Paragraph tourDetails = new Paragraph("Name: " + tour.getName() + "\n" +
                "Description: " + tour.getDescription() + "\n" +
                "Start: " + tour.getStart() + "\n" +
                "Destination: " + tour.getDestination() + "\n" +
                "Transport type: " + tour.getTransportType() + "\n" +
                "Child friendliness: " + tour.getChildFriendliness() + "\n" +
                "Popularity: " + tour.getPopularity())
                .setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA))
                .setFontSize(10);
        document.add(tourDetails);
    }

    private void setTourLogs() throws IOException {
        Paragraph logHeader = new Paragraph("Tourlogs: ")
                .setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA))
                .setFontSize(14);
        document.add(logHeader);

        List logs = new List()
                .setSymbolIndent(12)
                .setListSymbol("\u2022")
                .setFont(PdfFontFactory.createFont(StandardFonts.TIMES_BOLD));

        for (TourLogDTO log : this.tour.getLogs()) {
            logs.add(new ListItem(log.getComment() + " " + log.getTimestamp()));
        }
        document.add(logs);

    }
}
