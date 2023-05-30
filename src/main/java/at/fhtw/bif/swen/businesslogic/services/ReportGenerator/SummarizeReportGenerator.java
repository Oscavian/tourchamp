package at.fhtw.bif.swen.businesslogic.services.ReportGenerator;

import at.fhtw.bif.swen.dto.TourDTO;
import at.fhtw.bif.swen.dto.TourLogDTO;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.ListItem;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.List;
import lombok.extern.java.Log;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class SummarizeReportGenerator extends ReportGenerator{
    private ArrayList<TourDTO> tours;
    public SummarizeReportGenerator(ArrayList<TourDTO> tours) throws FileNotFoundException {
        this.tours = tours;
        this.writer = new PdfWriter( "summarize.pdf");
        this.pdf = new PdfDocument(this.writer);
        this.document = new Document(this.pdf);
    }
    @Override
    void generate() throws IOException {
        setHeader();
        setBody();
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
        List logs = new List()
                .setSymbolIndent(12)
                .setListSymbol("\u2022")
                .setFont(PdfFontFactory.createFont(StandardFonts.TIMES_BOLD));

        for (TourDTO tour : this.tours) {
            Paragraph tourStats = new Paragraph("Tour: " + tour.getName())
                    .setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA))
                    .setFontSize(10)
                    .setBold();
            LogStats logstats = getLogStats(tour);

            logs.add(new ListItem(tourStats + "\n" + logstats.getDifficulty() + "\n" +
                    "Avg time: " + logstats.getTotalTime() + "\n" +
                    "Avg rating: " + logstats.getRating() + "\n"));

        }
        document.add(logs);
    }

    private LogStats getLogStats(TourDTO tour) {
        LogStats logStats = new LogStats();
        TourLogDTO tmp;
        int i = 0;
        for (; i < tour.getLogs().size(); i++) {
            tmp = tour.getLogs().get(i);
            logStats.setDifficulty(logStats.getDifficulty() + tmp.getDifficulty());
            logStats.setRating(logStats.getRating() + tmp.getRating());
            logStats.setTotalTime(logStats.getTotalTime() + tmp.getTotalTime());
        }
        logStats.setDifficulty(logStats.getDifficulty() / i);
        logStats.setRating(logStats.getRating() / i);
        logStats.setTotalTime(logStats.getTotalTime() / i);
        return logStats;
    }


}
