package at.fhtw.bif.swen.controller;

import at.fhtw.bif.swen.model.TourListModel;
import at.fhtw.bif.swen.model.TourModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    private TourListModel tourListModel;

    @FXML
    private TourListController tourListController;

   // public MainController(TourListModel tourListModel) {this.tourListModel = tourListModel;}
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
      //  this.tourListController.addListener(p -> this.tourListModel.addTour(new TourModel()));
    }
}
