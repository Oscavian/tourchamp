package at.fhtw.bif.swen.businesslogic.services.ReportGenerator;

import at.fhtw.bif.swen.dto.TourDTO;
import at.fhtw.bif.swen.dto.TourLogDTO;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import lombok.extern.java.Log;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.List;

public class SummarizeReportGenerator extends ReportGenerator{
    private List<TourDTO> tours;
    public SummarizeReportGenerator(List<TourDTO> tours) throws FileNotFoundException {
        this.tours = tours;
        this.writer = new PdfWriter( "summarize.pdf");
        this.pdf = new PdfDocument(this.writer);
        this.document = new Document(this.pdf);
    }
    @Override
    void generate() throws IOException {

    }

    @Override
    void setHeader() throws IOException {
        Paragraph header = new Paragraph("Tour log statistics")
                .setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA))
                .setFontSize(18)
                .setBold();
        document.add(header);
    }

    @Override
    void setBody() throws IOException {

    }

    private LogStats getLogStats(TourDTO tour) {
        LogStats logStats = new LogStats();
        TourLogDTO tmp;
        for (int i = 0; i < tour.getLogs().size(); i++) {
            tmp = tour.getLogs().get(i);
            logStats.setDifficulty(logStats.getDifficulty() + tmp.getDifficulty());
            logStats.setRating(logStats.getRating() + tmp.getRating());
            logStats.setTotalTime(Duration.of(logStats.getTotalTime().getSeconds() + tmp.getTotalTime().getSeconds(), ChronoUnit.SECONDS));
        }
        return logStats;
    }


}
