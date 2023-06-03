package at.fhtw.bif.swen.presentation.controller;

import at.fhtw.bif.swen.presentation.service.TourService;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.Writer;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;

public class MenubarController implements Initializable {

    private Runnable reloadListener;
    private Stage stage;
    private final TourService tourService;
    private FileChooser fileChooser;

    MenubarController(TourService tourService) {
        this.tourService = tourService;
    }
    public void _import(ActionEvent actionEvent) {
        File selectedFile = this.fileChooser.showOpenDialog(this.stage);
        this.tourService.importTours(selectedFile);
        this.reloadListener.run();
    }
    public void _export(ActionEvent actionEvent) {
        File selectedFile = this.fileChooser.showSaveDialog(this.stage);

        try (PrintWriter writer = new PrintWriter(selectedFile)){
            writer.write(this.tourService.exportTours());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void setReloadListener(Runnable reloadListener) {
        this.reloadListener = reloadListener;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.stage = new Stage();
        this.fileChooser = new FileChooser();
        this.fileChooser.setInitialFileName("tours.json");
    }


}
