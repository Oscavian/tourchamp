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

    public Label tourDetailName;
    public Label tourDetailDescription;
    public Label tourDetailStart;
    public Label tourDetailDestination;
    public Label tourDetailTransportType;

    //Input Fields
    public TextField enterTourDetailName;
    public TextField enterTourDetailDescription;
    public TextField enterTourDetailStart;
    public TextField enterTourDetailDestination;
    public TextField enterTourDetailTransportType;

    //Calculated fields
    public Label tourDetailChildFriendliness;
    public Label tourDetailPopularity;
    public Label tourDetailTourDistance;

    //Buttons
    public HBox newTourButtons;
    public HBox editTourButtons;
    public HBox tourForm;
    public Label tourDetailEstimatedTime;

    public Label tourId;
    public HBox saveEditButtons;

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


        //bindings for enter model
        enterTourDetailName.textProperty().bindBidirectional(enterTourDetailsModel.nameProperty());
        enterTourDetailDescription.textProperty().bindBidirectional(enterTourDetailsModel.descriptionProperty());
        enterTourDetailStart.textProperty().bindBidirectional(enterTourDetailsModel.startProperty());
        enterTourDetailDestination.textProperty().bindBidirectional(enterTourDetailsModel.destinationProperty());
        enterTourDetailTransportType.textProperty().bindBidirectional(enterTourDetailsModel.transportTypeProperty());

        //hide buttons by default
        this.newTourButtons.setVisible(false);
        this.editTourButtons.setVisible(false);
        this.saveEditButtons.setVisible(false);

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
        this.tourDetailsModel.reset();
        //fire event to other tourlist view
        this.saveTourListener.accept(this.enterTourDetailsModel);

        this.tourForm.setDisable(true);
        this.newTourButtons.setVisible(false);
        //clear input fields
        this.enterTourDetailsModel.clear();
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
        this.enterTourDetailsModel.clear();
        this.tourDetailName.setCursor(Cursor.DEFAULT);
        this.newTourButtons.setVisible(true);
        this.editTourButtons.setVisible(false);
        this.tourForm.setDisable(false);
    }

    public void editTour(ActionEvent actionEvent) {
        this.tourForm.setDisable(false);
        this.editTourButtons.setVisible(false);
        this.saveEditButtons.setVisible(true);

        this.enterTourDetailsModel.setName(tourDetailsModel.getName());
        this.enterTourDetailsModel.setDestination(tourDetailsModel.getDestination());
        this.enterTourDetailsModel.setStart(tourDetailsModel.getStart());
        this.enterTourDetailsModel.setDescription(tourDetailsModel.getDescription());
        this.enterTourDetailsModel.setTransportType(tourDetailsModel.getTransportType());
    }

    public void removeTour(ActionEvent actionEvent) {
        this.removeTourListener.accept(this.tourDetailsModel);
        this.tourDetailsModel.reset();
        this.tourForm.setDisable(true);
    }

    public void saveEditedTour(ActionEvent actionEvent) {
        enterTourDetailsModel.setId(tourDetailsModel.getId());
        System.out.println("saving tour" + enterTourDetailsModel.getId());
        this.editTourListener.accept(enterTourDetailsModel);
        this.saveEditButtons.setVisible(false);
        this.tourForm.setDisable(true);
        this.editTourButtons.setVisible(true);
        this.enterTourDetailsModel.clear();
    }

    public void cancelEdit(ActionEvent actionEvent) {
        this.saveEditButtons.setVisible(false);
        this.editTourButtons.setVisible(true);
        this.tourForm.setDisable(true);
    }
}
