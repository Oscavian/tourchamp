package at.fhtw.bif.swen.controller;

import at.fhtw.bif.swen.model.TourListModel;
import at.fhtw.bif.swen.model.TourModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
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
