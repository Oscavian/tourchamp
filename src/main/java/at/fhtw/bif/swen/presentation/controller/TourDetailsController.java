package at.fhtw.bif.swen.presentation.controller;

import at.fhtw.bif.swen.presentation.model.TourListModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class TourDetailsController implements Initializable {
    @FXML // we need that to here, so we can access it from the main controller
    public TourDetailsGeneralController tourDetailsGeneralController;
    @FXML // reference to disable the input form
    public AnchorPane tourDetailsGeneral;

    private final TourListModel tourListModel;

    public TourDetailsController(TourListModel tourListModel) {
        this.tourListModel = tourListModel;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.tourDetailsGeneral.setDisable(true);
    }


}
