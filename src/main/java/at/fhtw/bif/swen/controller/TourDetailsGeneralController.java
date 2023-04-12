package at.fhtw.bif.swen.controller;

import at.fhtw.bif.swen.model.TourDetailsGeneralModel;
import at.fhtw.bif.swen.model.TourModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.ConstraintsBase;
import javafx.scene.layout.HBox;

import java.lang.reflect.Field;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.Callable;
import java.util.function.Consumer;
import java.util.function.Supplier;


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
    public HBox buttons;

    public TourDetailsGeneralModel tourDetailsGeneralModel;
    private Consumer<TourDetailsGeneralModel> saveTourListener;
    private Runnable cancelListener;

    public TourDetailsGeneralController(TourDetailsGeneralModel tourDetailsGeneralModel) {
        this.tourDetailsGeneralModel = tourDetailsGeneralModel;
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

        this.buttons.setVisible(false);
    }

    public void addListener(Consumer<TourDetailsGeneralModel> saveTourListener) {
        this.saveTourListener = saveTourListener;
    }

    public void saveTour(ActionEvent actionEvent) {
        this.saveTourListener.accept(this.tourDetailsGeneralModel);
        this.buttons.setVisible(false);
    }

    public void setCancelListener(Runnable cancelListener) {
        this.cancelListener = cancelListener;
    }

    public void cancel(ActionEvent actionEvent) {
        this.buttons.setVisible(false);
        this.cancelListener.run();
    }

    public void initNewTour(){
       // this.tourDetailsGeneralModel = new TourDetailsGeneralModel();
        this.tourDetailName.setCursor(Cursor.DEFAULT);
        this.buttons.setVisible(true);
    }
}
