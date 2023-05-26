package at.fhtw.bif.swen.presentation.controller;

import at.fhtw.bif.swen.presentation.model.TourLogListModel;
import at.fhtw.bif.swen.presentation.model.TourLogModel;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.time.Duration;
import java.util.Date;
import java.util.ResourceBundle;

public class TourDetailsLogsController implements Initializable {

    //Table elements
    public TableView<TourLogModel> logTable;
    public TableColumn<TourLogModel, Date> dateColumn;
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
    public DatePicker logDate;
    public TextField logTotalTime;
    public TextField logComment;
    public TextField logDifficulty;
    public TextField logRating;

    //models
    private final TourLogListModel tourLogListModel;
    private final TourLogModel enterTourLogModel;

    public TourDetailsLogsController(TourLogListModel tourLogListModel, TourLogModel tourLogModel) {
        this.tourLogListModel = tourLogListModel;
        this.enterTourLogModel = tourLogModel;
    }

    public void addLogEntry(ActionEvent actionEvent) {
        tourLogListModel.addNewLog(enterTourLogModel);
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

        logTable.setItems(tourLogListModel.getTourLogs());

        //data bindings for enter form
        logDate.promptTextProperty().bindBidirectional(enterTourLogModel.dateProperty());
        logTotalTime.textProperty().bindBidirectional(enterTourLogModel.timeProperty());
        logComment.textProperty().bindBidirectional(enterTourLogModel.commentProperty());
        logDifficulty.textProperty().bindBidirectional(enterTourLogModel.difficultyProperty());
        logRating.textProperty().bindBidirectional(enterTourLogModel.ratingProperty());
    }
}
