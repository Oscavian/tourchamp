package at.fhtw.bif.swen.presentation.controller;

import at.fhtw.bif.swen.presentation.model.TourDetailsGeneralModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;


public class TourDetailsGeneralController implements Initializable {

    //Input Fields
    public TextField tourDetailName;
    public TextField tourDetailDescription;
    public TextField tourDetailStart;
    public TextField tourDetailDestination;
    public TextField tourDetailTransportType;
    public TextField tourDetailTourDistance;

    //Calculated fields
    public Label tourDetailChildFriendliness;
    public Label tourDetailPopularity;

    //Buttons
    public HBox newTourButtons;
    public HBox editTourButtons;
    public VBox tourForm;
    public Label tourDetailEstimatedTime;
    public Label tourTitle;


    //Event listener
    private Consumer<TourDetailsGeneralModel> saveTourListener;

    private Consumer<TourDetailsGeneralModel> editTourListener;

    private Consumer<TourDetailsGeneralModel> removeTourListener;
    private Runnable cancelListener;
    public TourDetailsGeneralModel tourDetailsGeneralModel;

    public void setTourDetailsGeneralModel(TourDetailsGeneralModel tourDetailsGeneralModel) {
        this.tourDetailsGeneralModel.setName(tourDetailsGeneralModel.getName());
        this.tourDetailsGeneralModel.setTourDistance(tourDetailsGeneralModel.getTourDistance());
        this.tourDetailsGeneralModel.setDestination(tourDetailsGeneralModel.getDestination());
        this.tourDetailsGeneralModel.setStart(tourDetailsGeneralModel.getStart());
        this.tourDetailsGeneralModel.setDescription(tourDetailsGeneralModel.getDescription());
        this.tourDetailsGeneralModel.setDuration(tourDetailsGeneralModel.getDuration());
        this.tourDetailsGeneralModel.setPopularity(tourDetailsGeneralModel.getPopularity());
        this.tourDetailsGeneralModel.setChildFriendliness(tourDetailsGeneralModel.getChildFriendliness());
        this.tourDetailsGeneralModel.setTransportType(tourDetailsGeneralModel.getTransportType());
    }

    public TourDetailsGeneralController(TourDetailsGeneralModel tourDetailsGeneralModel) {
        this.tourDetailsGeneralModel = tourDetailsGeneralModel;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tourDetailName.textProperty().bindBidirectional(tourDetailsGeneralModel.nameProperty());
        tourDetailDescription.textProperty().bindBidirectional(tourDetailsGeneralModel.descriptionProperty());
        tourDetailStart.textProperty().bindBidirectional(tourDetailsGeneralModel.startProperty());
        tourDetailDestination.textProperty().bindBidirectional(tourDetailsGeneralModel.destinationProperty());
        tourDetailTransportType.textProperty().bindBidirectional(tourDetailsGeneralModel.transportTypeProperty());
        tourDetailTourDistance.textProperty().bindBidirectional(tourDetailsGeneralModel.tourDistanceProperty());
        tourDetailEstimatedTime.textProperty().bindBidirectional(tourDetailsGeneralModel.durationProperty());
        tourDetailChildFriendliness.textProperty().bindBidirectional(tourDetailsGeneralModel.childFriendlinessProperty());
        tourDetailPopularity.textProperty().bindBidirectional(tourDetailsGeneralModel.popularityProperty());

        this.newTourButtons.setVisible(false);
        this.editTourButtons.setVisible(false);

    }

    // Listeners
    public void setSaveListener(Consumer<TourDetailsGeneralModel> saveTourListener) {
        this.saveTourListener = saveTourListener;
    }

    public void setEditListener(Consumer<TourDetailsGeneralModel> editTourListener) {
        this.editTourListener = editTourListener;
    }

    public void setRemoveListener(Consumer<TourDetailsGeneralModel> removeTourListener) {
        this.removeTourListener = removeTourListener;
    }

    public void setCancelListener(Runnable cancelListener) {
        this.cancelListener = cancelListener;
    }

    //Action events
    public void saveTour(ActionEvent actionEvent) {
        this.saveTourListener.accept(this.tourDetailsGeneralModel);
        this.tourForm.setDisable(true);
        this.newTourButtons.setVisible(false);

        //clear input fields
        this.tourDetailsGeneralModel.reset();
    }

    public void cancel(ActionEvent actionEvent) {
        this.newTourButtons.setVisible(false);
        this.editTourButtons.setVisible(false);

        //TODO: add reset to previous values when editing
        this.tourDetailsGeneralModel.reset();

        this.cancelListener.run();
    }

    public void initNewTour(){
        this.tourDetailsGeneralModel.reset();
        this.tourDetailName.setCursor(Cursor.DEFAULT);
        this.newTourButtons.setVisible(true);
        this.editTourButtons.setVisible(false);
        this.tourForm.setDisable(false);
    }

    public void editTour(ActionEvent actionEvent) {
        this.tourForm.setDisable(false);
        this.editTourButtons.setVisible(false);
        this.newTourButtons.setVisible(true);
    }

    public void removeTour(ActionEvent actionEvent) {
        this.removeTourListener.accept(this.tourDetailsGeneralModel);
        this.tourDetailsGeneralModel.reset();
        this.tourForm.setDisable(true);
    }
}
