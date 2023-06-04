package at.fhtw.bif.swen.presentation.controller;

import at.fhtw.bif.swen.presentation.model.TourListItemModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import java.util.function.Consumer;

public class TourListItemController {
    private TourListItemModel tour;

    private Consumer<TourListItemModel> onDeleteTourConsumer;

    @FXML
    public Label name;

    public Label id = new Label();

    @FXML
    public Node box = new HBox();

    public TourListItemController() {}

    public Node getTourItemBox() {
        return box;
    }

    public void setTour(TourListItemModel tour) {
        this.tour = tour;
        this.name.textProperty().bindBidirectional(this.tour.nameProperty());
        this.id.textProperty().bindBidirectional(this.tour.idProperty());
    }
}
