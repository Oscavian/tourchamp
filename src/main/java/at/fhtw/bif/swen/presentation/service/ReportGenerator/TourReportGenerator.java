package at.fhtw.bif.swen.presentation.service.ReportGenerator;

import at.fhtw.bif.swen.dto.TourDTO;
import at.fhtw.bif.swen.dto.TourLogDTO;
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

import java.io.FileNotFoundException;
import java.io.IOException;

public class TourReportGenerator extends ReportGenerator {

    private TourDTO tour;

    public TourReportGenerator (TourDTO tourDTO) throws FileNotFoundException {
        this.tour = tourDTO;
        this.writer = new PdfWriter(tour.getName() + ".pdf");
        this.pdf = new PdfDocument(this.writer);
        this.document = new Document(this.pdf);
    }

    @Override
    void generate() throws IOException {
        this.setHeader();
        this.setBody();
    }

    @Override
    void setHeader() throws IOException {
        Paragraph header = new Paragraph(tour.getName()).
                setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA))
                .setFontSize(18)
                .setBold();
        document.add(header);
    }

    @Override
    void setBody() throws IOException {
        this.setImage();
        this.setTourDetails();
        this.setTourLogs();
    }

    private void setImage() {
        //ImageData imageData = ImageDataFactory.createJpeg(this.tour.getImage());
        //document.add(new Image(imageData));
    }

    private void setTourDetails() throws IOException {
        Paragraph tourDetailsHeader = new Paragraph("Details").
                setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA))
                .setFontSize(14)
                .setBold();
        document.add(tourDetailsHeader);

        Paragraph tourDetails = new Paragraph("Name: " + tour.getName() +
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
                .setFontSize(14)
                .setBold();
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
