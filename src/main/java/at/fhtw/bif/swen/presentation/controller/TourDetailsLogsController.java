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
    public Button editLogButton;
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

    private TourLogModel selectedTourLogModel;

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
        if (isEdit) {
            this.selectedTourLogModel.setDate(enterTourLogModel.getDate());
            this.selectedTourLogModel.setComment(enterTourLogModel.getComment());
            this.selectedTourLogModel.setDifficulty(enterTourLogModel.getDifficulty());
            this.selectedTourLogModel.setTime(enterTourLogModel.getTime());
            this.selectedTourLogModel.setRating(enterTourLogModel.getRating());

            this.editLogButton.setDisable(false);
            this.clearAllLogsButton.setDisable(false);
            this.deleteLogButton.setDisable(false);
        }
        this.logTable.setDisable(false);
        if (!isEdit) {
            this.tourDetailsModel.addNewLog(enterTourLogModel);
        }
        enterTourLogModel.clear();
        isEdit = false;
    }

    public void deleteEntry(ActionEvent actionEvent) {
        this.tourDetailsModel.deleteLog(this.selectedTourLogModel);
    }

    public void clearAllLogs(ActionEvent actionEvent) {
        this.tourDetailsModel.clearLogs();
    }

    private boolean isEdit = false;

    public void editLogEntry(ActionEvent actionEvent) {
        //move values to enter model
        if (selectedTourLogModel == null) {
            return;
        }
        this.logTable.setDisable(true);
        this.editLogButton.setDisable(true);
        this.deleteLogButton.setDisable(true);
        this.clearAllLogsButton.setDisable(true);
        isEdit = true;
        this.enterTourLogModel.setDate(selectedTourLogModel.getDate());
        this.enterTourLogModel.setComment(selectedTourLogModel.getComment());
        this.enterTourLogModel.setDifficulty(selectedTourLogModel.getDifficulty());
        this.enterTourLogModel.setTime(selectedTourLogModel.getTime());
        this.enterTourLogModel.setRating(selectedTourLogModel.getRating());
    }
    @Override

    public void initialize(URL url, ResourceBundle resourceBundle) {

        logTable.getSelectionModel().selectedItemProperty().addListener((observableValue, tourLogModel, t1) -> {
            if(observableValue != null) {
                setSelectedTourLogModel(observableValue.getValue());
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

    public void setSelectedTourLogModel(TourLogModel selectedTourLogModel) {
        this.selectedTourLogModel = selectedTourLogModel;
    }
}
