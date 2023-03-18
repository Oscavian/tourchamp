package at.fhtw.bif.swen.controller;

import at.fhtw.bif.swen.model.TourListModel;
import at.fhtw.bif.swen.model.TourModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;

public class TourListController implements Initializable {

    private TourListModel tourListModel;

    private Consumer<TourModel> newTourListener;

    @FXML
    public ListView tourList = new ListView<>();

    public void addTour(ActionEvent actionEvent) {
        this.newTourListener.accept(null);
    }

    public void addTourListener(Consumer<TourModel> listenToNewTour) {
        this.newTourListener = listenToNewTour;
    }

    public TourListController(TourListModel tourListModel) {
        this.tourListModel = tourListModel;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.addTourListener(p -> this.tourListModel.addTour(new TourModel()));
    }
}
