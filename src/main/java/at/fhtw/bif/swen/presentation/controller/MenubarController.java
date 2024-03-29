package at.fhtw.bif.swen.presentation.controller;

import at.fhtw.bif.swen.dto.TourDTO;
import at.fhtw.bif.swen.presentation.service.MapQuestAPIService.MapQuestAPIService;
import at.fhtw.bif.swen.presentation.service.MapQuestAPIService.TourMapData;
import at.fhtw.bif.swen.presentation.service.ReportGenerator.ReportGenerator;
import at.fhtw.bif.swen.presentation.service.ReportGenerator.SummarizeReportGenerator;
import at.fhtw.bif.swen.presentation.service.ReportGenerator.TourReportGenerator;
import at.fhtw.bif.swen.presentation.service.TourService;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ResourceBundle;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class MenubarController implements Initializable {

    private Runnable reloadListener;
    private Stage stage;
    private final TourService tourService;
    private FileChooser fileChooser;
    private Supplier<Integer> selectedTourId;

    private Consumer<String> ticketURLConsumer;
    private Node spinner;

    private String mapUrl;
    @FXML
    public MenuItem tourReport;

    @FXML
    public MenuItem browseTickets;

    private final Logger logger = LogManager.getLogger(getClass().getName());

    MenubarController(TourService tourService, Consumer<String> ticketURLConsumer) {
        this.tourService = tourService;
        this.ticketURLConsumer = ticketURLConsumer;
    }
    public void _import(ActionEvent actionEvent) {
        File selectedFile = this.fileChooser.showOpenDialog(this.stage);
        logger.debug("Menubar: Import Tours from File: '" + selectedFile.getName() + "'");
        this.tourService.importTours(selectedFile);
        this.reloadListener.run();
    }
    public void _export(ActionEvent actionEvent) {
        this.fileChooser.setInitialFileName("tours.json");
        File selectedFile = this.fileChooser.showSaveDialog(this.stage);
        logger.debug("Menubar: Export Tours to file: '" + selectedFile.getName() + "'");
        try (PrintWriter writer = new PrintWriter(selectedFile)){
            writer.write(this.tourService.exportTours());
        } catch (FileNotFoundException e) {
            logger.error("Menubar: Error writing Tours to file'" + selectedFile.getName() + "'");
            throw new RuntimeException(e);
        }
    }

    public void generateSummarizeReport() {
        this.fileChooser.setInitialFileName("summarize.pdf");
        File selectedFile = this.fileChooser.showSaveDialog(this.stage);
        logger.debug("Menubar: Generate summarize report to file: '"+ selectedFile.getName() + "'");
        SummarizeReportGenerator generator = new SummarizeReportGenerator(selectedFile, tourService.getTourDTOs());
        onSuccess(selectedFile, generator.generate(), generator);
    }

    public void generateTourReport() {
        TourDTO tour = this.tourService.getDTOById(selectedTourId.get());
        this.fileChooser.setInitialFileName(tour.getName() + ".pdf");
        File selectedFile = this.fileChooser.showSaveDialog(this.stage);
        logger.debug("Menubar: Generate summarize report to file: '"+ selectedFile.getName() + "'");
        TourReportGenerator generator = new TourReportGenerator(selectedFile, tour);
        generator.setImage(this.mapUrl);
        onSuccess(selectedFile, generator.generate(), generator);
    }

    public void browseTickets() {
        TourDTO tour = this.tourService.getDTOById(selectedTourId.get());
        String URL = "https://www.happyrail.com/en/depart?from="+
                URLEncoder.encode(tour.getStart(), StandardCharsets.UTF_8) +"&to=" +
                URLEncoder.encode(tour.getDestination(), StandardCharsets.UTF_8)+"";
        this.ticketURLConsumer.accept(URL);
    }

    private void onSuccess(File selectedFile, CompletableFuture<Void> generate, ReportGenerator generator) {
        generate.thenAccept(x -> {
            Platform.runLater(() -> {
                String message = "Report '"+ selectedFile.getName() + "' successfully generated";
                logger.debug("Menubar: " + message);
                Alert a = new Alert(Alert.AlertType.INFORMATION, message , ButtonType.OK);
                a.setTitle("Success!");
                a.setHeaderText("Export finished.");
                a.show();
            });
        });
    }

    public void setReloadListener(Runnable reloadListener) {
        this.reloadListener = reloadListener;
    }

    public void setSelectedTourId(Supplier<Integer> selectedTourId) {
        this.selectedTourId = selectedTourId;
    }

    public void setApiData(CompletableFuture<TourMapData> apiData) {
        this.tourReport.setDisable(true);
        this.setTourLoadingSpinner();
        apiData.thenAccept(a -> {
            Platform.runLater(() -> {
                if (a == null) {
                    logger.error("Error loading API data.");
                    try {
                        this.tourReport.setGraphic(new ImageView(new Image(
                                new FileInputStream("./src/main/resources/img/error.png"),
                                10, 10,false,false)));
                        this.browseTickets.setGraphic(new ImageView(new Image(
                                new FileInputStream("./src/main/resources/img/error.png"),
                                10, 10,false,false)));
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    logger.debug("Menubar: Finished loading API data.");
                    this.tourReport.setGraphic(null);
                    this.tourReport.setDisable(false);
                    this.browseTickets.setGraphic(null);
                    this.browseTickets.setDisable(false);
                    this.mapUrl = MapQuestAPIService.buildStaticMapRequest(a);
                }

            });
        });
    }

    private void setTourLoadingSpinner() {
        this.tourReport.setGraphic(this.spinner);
        this.browseTickets.setGraphic(this.spinner);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.stage = new Stage();
        this.fileChooser = new FileChooser();
        this.tourReport.setDisable(true);
        this.browseTickets.setDisable(true);

        try {
            this.spinner = new ImageView(new Image(
                    new FileInputStream("./src/main/resources/img/spinner.gif"),
                    10, 10,false,false));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


}
