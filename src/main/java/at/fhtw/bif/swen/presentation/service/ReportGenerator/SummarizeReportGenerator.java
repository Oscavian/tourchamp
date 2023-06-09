package at.fhtw.bif.swen.presentation.service.ReportGenerator;

import at.fhtw.bif.swen.dto.TourDTO;
import at.fhtw.bif.swen.dto.TourLogDTO;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.List;
import com.itextpdf.layout.element.ListItem;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;
import javafx.scene.text.Font;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

public class SummarizeReportGenerator extends ReportGenerator {
    private ArrayList<TourDTO> tours;
    private final Logger logger = LogManager.getLogger(getClass().getName());

    public SummarizeReportGenerator(File file, ArrayList<TourDTO> tours) {
        this.tours = tours;
        try {
            this.writer = new PdfWriter( file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        this.pdf = new PdfDocument(this.writer);
        this.document = new Document(this.pdf);
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
        Paragraph tourStats = null;
        PdfFont font = PdfFontFactory.createFont(StandardFonts.HELVETICA);
        for (TourDTO tour : this.tours) {
            tourStats = new Paragraph();
            Text heading = new Text("Tour: " + tour.getName() + "\n");
            heading.setFont(font).setBold();
            tourStats.add(heading);
            LogStats logstats = getLogStats(tour);
            Text text = new Text("Avg difficulty: " + logstats.getDifficulty() + "\n" +
                    "Avg time: " + logstats.getTotalTime() + "\n" +
                    "Avg rating: " + logstats.getRating() + "\n" +
                    "Popularity: "+ tour.getPopularity() +"\n" +
                    "ChildFriendliness: "+ tour.getChildFriendliness()+"\n\n");
            text.setFont(font);
            tourStats.add(text);
            document.add(tourStats);
        }
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
        if (i == 0) {
            return logStats;
        }
        logStats.setDifficulty(logStats.getDifficulty() / i);
        logStats.setRating(logStats.getRating() / i);
        logStats.setTotalTime(logStats.getTotalTime() / i);
        return logStats;
    }


}
