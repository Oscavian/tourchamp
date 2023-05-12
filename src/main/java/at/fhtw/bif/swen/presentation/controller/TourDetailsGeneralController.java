package at.fhtw.bif.swen.presentation.controller;

import at.fhtw.bif.swen.presentation.model.EnterTourDetailsModel;
import at.fhtw.bif.swen.presentation.model.TourDetailsModel;
import at.fhtw.bif.swen.presentation.service.TourService;
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
    public Label tourId;

    //Event listener
    private Consumer<EnterTourDetailsModel> saveTourListener;

    private Consumer<EnterTourDetailsModel> editTourListener;

    private Consumer<TourDetailsModel> removeTourListener;
    private Runnable cancelListener;
    public TourDetailsModel tourDetailsModel;

    public EnterTourDetailsModel enterTourDetailsModel;


    private TourService tourService;

    public TourDetailsGeneralController(TourDetailsModel tourDetailsModel, EnterTourDetailsModel enterTourDetailsModel) {
        this.tourDetailsModel = tourDetailsModel;
        this.enterTourDetailsModel = enterTourDetailsModel;
    }

    public void setTourDetailsModel(TourDetailsModel tourDetailsModel) {
        this.tourDetailsModel.setId(tourDetailsModel.getId());
        this.tourDetailsModel.setName(tourDetailsModel.getName());
        this.tourDetailsModel.setTourDistance(tourDetailsModel.getTourDistance());
        this.tourDetailsModel.setDestination(tourDetailsModel.getDestination());
        this.tourDetailsModel.setStart(tourDetailsModel.getStart());
        this.tourDetailsModel.setDescription(tourDetailsModel.getDescription());
        this.tourDetailsModel.setDuration(tourDetailsModel.getDuration());
        this.tourDetailsModel.setPopularity(tourDetailsModel.getPopularity());
        this.tourDetailsModel.setChildFriendliness(tourDetailsModel.getChildFriendliness());
        this.tourDetailsModel.setTransportType(tourDetailsModel.getTransportType());
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //bindings for detail model
        tourId.textProperty().bindBidirectional(tourDetailsModel.idProperty());
        tourDetailName.textProperty().bindBidirectional(tourDetailsModel.nameProperty());
        tourDetailDescription.textProperty().bindBidirectional(tourDetailsModel.descriptionProperty());
        tourDetailStart.textProperty().bindBidirectional(tourDetailsModel.startProperty());
        tourDetailDestination.textProperty().bindBidirectional(tourDetailsModel.destinationProperty());
        tourDetailTransportType.textProperty().bindBidirectional(tourDetailsModel.transportTypeProperty());
        tourDetailTourDistance.textProperty().bindBidirectional(tourDetailsModel.tourDistanceProperty());
        tourDetailEstimatedTime.textProperty().bindBidirectional(tourDetailsModel.durationProperty());
        tourDetailChildFriendliness.textProperty().bindBidirectional(tourDetailsModel.childFriendlinessProperty());
        tourDetailPopularity.textProperty().bindBidirectional(tourDetailsModel.popularityProperty());

        //title in detail view
        tourTitle.textProperty().bindBidirectional(tourDetailsModel.nameProperty());

        //bindings for enter model
        tourDetailName.textProperty().bindBidirectional(enterTourDetailsModel.nameProperty());
        tourDetailDescription.textProperty().bindBidirectional(enterTourDetailsModel.descriptionProperty());
        tourDetailStart.textProperty().bindBidirectional(enterTourDetailsModel.startProperty());
        tourDetailDestination.textProperty().bindBidirectional(enterTourDetailsModel.destinationProperty());
        tourDetailTransportType.textProperty().bindBidirectional(enterTourDetailsModel.transportTypeProperty());
        tourDetailTourDistance.textProperty().bindBidirectional(enterTourDetailsModel.tourDistanceProperty());
        tourDetailEstimatedTime.textProperty().bindBidirectional(enterTourDetailsModel.durationProperty());

        //hide buttons by default
        this.newTourButtons.setVisible(false);
        this.editTourButtons.setVisible(false);

    }

    // Listeners
    public void setSaveListener(Consumer<EnterTourDetailsModel> saveTourListener) {
        this.saveTourListener = saveTourListener;
    }

    public void setEditListener(Consumer<EnterTourDetailsModel> editTourListener) {
        this.editTourListener = editTourListener;
    }

    public void setRemoveListener(Consumer<TourDetailsModel> removeTourListener) {
        this.removeTourListener = removeTourListener;
    }

    public void setCancelListener(Runnable cancelListener) {
        this.cancelListener = cancelListener;
    }

    //Action events
    public void saveTour(ActionEvent actionEvent) {
        //fire event to other tourlist view
        this.saveTourListener.accept(this.enterTourDetailsModel);

        this.tourForm.setDisable(true);
        this.newTourButtons.setVisible(false);
        //clear input fields
        this.tourDetailsModel.reset();
    }

    public void cancel(ActionEvent actionEvent) {
        this.newTourButtons.setVisible(false);
        this.editTourButtons.setVisible(false);

        //TODO: add reset to previous values when editing
        this.tourDetailsModel.reset();

        this.cancelListener.run();
    }

    public void initNewTour(){
        this.tourDetailsModel.reset();
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
        this.removeTourListener.accept(this.tourDetailsModel);
        this.tourDetailsModel.reset();
        this.tourForm.setDisable(true);
    }
}
