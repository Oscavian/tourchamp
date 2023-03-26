package at.fhtw.bif.swen.controller;

import at.fhtw.bif.swen.model.TourDetailsGeneralModel;
import at.fhtw.bif.swen.model.TourModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.ConstraintsBase;

import java.lang.reflect.Field;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;


public class TourDetailsGeneralController implements Initializable {

    public TextField tourDetailName;
    public TextField tourDetailDescription;
    public TextField tourDetailFrom;
    public TextField tourDetailTo;
    public TextField tourDetailTransportType;
    public TextField tourDetailTourDistance;
    public TextField tourDetailDuration;
    public TextField tourDetailRouteInfo;
    public Label tourDetailChildFriendliness;
    public Label tourDetailPopularity;

    private final TourDetailsGeneralModel tourDetailsGeneralModel;
    private Consumer<TourDetailsGeneralModel> newTourListener;

    public TourDetailsGeneralController(TourDetailsGeneralModel tourDetailsGeneralModel) {
        this.tourDetailsGeneralModel = tourDetailsGeneralModel;
    }

    public void addListener(Consumer<TourDetailsGeneralModel> newTourListener) {
        this.newTourListener = newTourListener;
    }

    public void addTour(ActionEvent actionEvent) {
        this.newTourListener.accept(this.tourDetailsGeneralModel);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        tourDetailName.textProperty().bindBidirectional(tourDetailsGeneralModel.nameProperty());
        tourDetailDescription.textProperty().bindBidirectional(tourDetailsGeneralModel.descriptionProperty());
        tourDetailFrom.textProperty().bindBidirectional(tourDetailsGeneralModel.fromProperty());
        tourDetailTo.textProperty().bindBidirectional(tourDetailsGeneralModel.toProperty());
        tourDetailTransportType.textProperty().bindBidirectional(tourDetailsGeneralModel.transportTypeProperty());
        tourDetailTourDistance.textProperty().bindBidirectional(tourDetailsGeneralModel.tourDistanceProperty());
        tourDetailDuration.textProperty().bindBidirectional(tourDetailsGeneralModel.durationProperty());
        tourDetailRouteInfo.textProperty().bindBidirectional(tourDetailsGeneralModel.routeInfoProperty());
        tourDetailChildFriendliness.textProperty().bindBidirectional(tourDetailsGeneralModel.childFriendlinessProperty());
        tourDetailPopularity.textProperty().bindBidirectional(tourDetailsGeneralModel.popularityProperty());
    }

    public void onTourDetailSave(ActionEvent actionEvent) {
        //tourDetailName.editableProperty().set(false);
    }
}
