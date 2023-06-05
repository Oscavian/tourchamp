package at.fhtw.bif.swen.presentation.controller;

import at.fhtw.bif.swen.presentation.model.TourDetailsModel;
import at.fhtw.bif.swen.presentation.model.TourLogModel;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class TourDetailsLogsController implements Initializable {

    //Table elements
    public TableView<TourLogModel> logTable;
    public TableColumn<TourLogModel, String> dateColumn;
    public TableColumn<TourLogModel, String> totalTimeColumn;
    public TableColumn<TourLogModel, String> difficultyColumn;
    public TableColumn<TourLogModel, String> ratingColumn;
    public TableColumn<TourLogModel, String> commentColumn;

    // Buttons
    public Button addLogButton;
    public Button deleteLogButton;
    public Button clearAllLogsButton;
    public VBox addLogForm;

    //fields for new data
    public TextField logDate;
    public TextField logTotalTime;
    public TextField logComment;
    public TextField logDifficulty;
    public TextField logRating;

    //models
    private final TourLogModel enterTourLogModel;
    public final TourDetailsModel tourDetailsModel;

    public TourDetailsLogsController(TourDetailsModel tourDetailsModel, TourLogModel tourLogModel) {
        this.enterTourLogModel = tourLogModel;
        this.tourDetailsModel = tourDetailsModel;
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
        this.tourDetailsModel.setTransportTypeString(tourDetailsModel.getTransportTypeString());
        this.tourDetailsModel.setMapURL(tourDetailsModel.getMapURL());
        this.tourDetailsModel.addAllLogs(tourDetailsModel.getTourLogs());
    }

    public void addLogEntry(ActionEvent actionEvent) {
        this.tourDetailsModel.addNewLog(enterTourLogModel);
        enterTourLogModel.clear();
    }

    public void deleteEntry(ActionEvent actionEvent) {

    }

    public void clearAllLogs(ActionEvent actionEvent) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        logTable.getSelectionModel().selectedItemProperty().addListener((observableValue, tourLogModel, t1) -> {
            if(observableValue != null) {

            }
        });


        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        totalTimeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));
        difficultyColumn.setCellValueFactory(new PropertyValueFactory<>("difficulty"));
        ratingColumn.setCellValueFactory(new PropertyValueFactory<>("rating"));
        commentColumn.setCellValueFactory(new PropertyValueFactory<>("comment"));

        logTable.setItems(this.tourDetailsModel.getTourLogs());

        //data bindings for enter form
        logDate.textProperty().bindBidirectional(enterTourLogModel.dateProperty());
        logTotalTime.textProperty().bindBidirectional(enterTourLogModel.timeProperty());
        logComment.textProperty().bindBidirectional(enterTourLogModel.commentProperty());
        logDifficulty.textProperty().bindBidirectional(enterTourLogModel.difficultyProperty());
        logRating.textProperty().bindBidirectional(enterTourLogModel.ratingProperty());
    }

}
