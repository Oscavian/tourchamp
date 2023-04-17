package at.fhtw.bif.swen.presentation.controller;

import at.fhtw.bif.swen.presentation.model.TourModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import java.util.function.Consumer;

public class TourListItemController {
    private TourModel tour;

    private Consumer<TourModel> onDeleteTourConsumer;

    @FXML
    public Label name;

    @FXML
    public Node box = new HBox();

    public TourListItemController() {}

    public Node getTourItemBox() {
        return box;
    }

    public void setTour(TourModel tour) {
        this.tour = tour;
        this.name.textProperty().bindBidirectional(this.tour.nameProperty());
    }

    public void onDeleteTour(ActionEvent actionEvent) {
        this.onDeleteTourConsumer.accept(this.tour);
    }

    public void addListenerForDeleteTour(Consumer<TourModel> listener) {
        this.onDeleteTourConsumer = listener;
    }
}
