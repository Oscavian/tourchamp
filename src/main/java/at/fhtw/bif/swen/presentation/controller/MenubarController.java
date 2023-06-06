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

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.util.ResourceBundle;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public class MenubarController implements Initializable {

    private Runnable reloadListener;
    private Stage stage;
    private final TourService tourService;
    private FileChooser fileChooser;
    private Supplier<Integer> selectedTourId;
    private Node spinner;

    private String mapUrl;
    @FXML
    public MenuItem tourReport;
    MenubarController(TourService tourService) {
        this.tourService = tourService;
    }
    public void _import(ActionEvent actionEvent) {
        File selectedFile = this.fileChooser.showOpenDialog(this.stage);
        this.tourService.importTours(selectedFile);
        this.reloadListener.run();
    }
    public void _export(ActionEvent actionEvent) {
        this.fileChooser.setInitialFileName("tours.json");
        File selectedFile = this.fileChooser.showSaveDialog(this.stage);

        try (PrintWriter writer = new PrintWriter(selectedFile)){
            writer.write(this.tourService.exportTours());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void generateSummarizeReport() {
        this.fileChooser.setInitialFileName("summarize.pdf");
        File selectedFile = this.fileChooser.showSaveDialog(this.stage);

        SummarizeReportGenerator generator = new SummarizeReportGenerator(selectedFile, tourService.getTourDTOs());
        onSuccess(selectedFile, generator.generate(), generator);
    }

    public void generateTourReport() {
        TourDTO tour = this.tourService.getDTOById(selectedTourId.get());
        this.fileChooser.setInitialFileName(tour.getName() + ".pdf");
        File selectedFile = this.fileChooser.showSaveDialog(this.stage);
        TourReportGenerator generator = new TourReportGenerator(selectedFile, tour);
        generator.setImage(this.mapUrl);
        onSuccess(selectedFile, generator.generate(), generator);

    }

    private void onSuccess(File selectedFile, CompletableFuture<Void> generate, ReportGenerator generator) {
        generate.thenAccept(x -> {
            Platform.runLater(() -> {
                String message = "Report '"+ selectedFile.getName() + "' successfully generated";
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
        this.setTourReportSpinner();
        apiData.thenAccept(a -> {
            Platform.runLater(() -> {
                this.tourReport.setGraphic(null);
                this.tourReport.setDisable(false);
                this.mapUrl = MapQuestAPIService.buildStaticMapRequest(a);
            });
        });
    }

    private void setTourReportSpinner() {
        this.tourReport.setGraphic(this.spinner);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.stage = new Stage();
        this.fileChooser = new FileChooser();
        this.fileChooser.setInitialFileName("tours.json");
        this.tourReport.setDisable(true);

        try {
            this.spinner = new ImageView(new Image(
                    new FileInputStream("./src/main/resources/img/spinner.gif"),
                    10, 10,false,false));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


}
