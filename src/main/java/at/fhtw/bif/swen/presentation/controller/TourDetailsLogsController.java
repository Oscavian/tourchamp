package at.fhtw.bif.swen.presentation.controller;

import at.fhtw.bif.swen.presentation.AlertUtil;
import at.fhtw.bif.swen.presentation.model.TourDetailsModel;
import at.fhtw.bif.swen.presentation.model.TourLogModel;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.URL;
import java.time.format.DateTimeFormatter;
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
    //public TextField logDate;
    public DatePicker logDate;
    public TextField logTotalTime;
    public TextField logComment;
    public Slider logDifficulty;
    public Slider logRating;

    //models
    private final TourLogModel enterTourLogModel;
    public final TourDetailsModel tourDetailsModel;

    private TourLogModel selectedTourLogModel;
    private final Logger logger = LogManager.getLogger(getClass().getName());


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
        this.tourDetailsModel.setEstimatedTime(tourDetailsModel.getEstimatedTime());
        this.tourDetailsModel.setPopularity(tourDetailsModel.getPopularity());
        this.tourDetailsModel.setChildFriendliness(tourDetailsModel.getChildFriendliness());
        this.tourDetailsModel.setTransportTypeString(tourDetailsModel.getTransportTypeString());
        this.tourDetailsModel.setMapURL(tourDetailsModel.getMapURL());
        this.tourDetailsModel.addAllLogs(tourDetailsModel.getTourLogs());
    }

    public void addLogEntry(ActionEvent actionEvent) {
        if (enterTourLogModel.getTime().isEmpty() || enterTourLogModel.getComment().isEmpty()
            || enterTourLogModel.getDate() == null) {
            String message = "Empty input.\nFill out the form.";
            AlertUtil.alert(Alert.AlertType.ERROR, "Empty input fields.", message);
            logger.debug(message);
            return;
        }
        try {
            Integer.valueOf(enterTourLogModel.getTime());
        } catch (NumberFormatException e) {
            String message = "Invalid format for total time '" + enterTourLogModel.getTime() + "'\n";
            AlertUtil.alert(Alert.AlertType.ERROR,"Invalid format for total time.", message);
            logger.debug(message);
            return;
        }

        if (isEdit) {
            logger.debug("Edit log entry");
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
            logger.debug("Add new log entry");
            this.tourDetailsModel.addNewLog(enterTourLogModel);
        } else {
            this.tourDetailsModel.update();

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

        this.logDifficulty.setMax(10);
        this.logRating.setMax(10);
        logTable.getSelectionModel().selectedItemProperty().addListener((observableValue, tourLogModel, t1) -> {
            if(observableValue != null) {
                setSelectedTourLogModel(observableValue.getValue());
            }
        });

        //init datepicker
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        totalTimeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));
        difficultyColumn.setCellValueFactory(new PropertyValueFactory<>("difficulty"));
        ratingColumn.setCellValueFactory(new PropertyValueFactory<>("rating"));
        commentColumn.setCellValueFactory(new PropertyValueFactory<>("comment"));

        logTable.setItems(this.tourDetailsModel.getTourLogs());

        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        //data bindings for enter form
        logDate.valueProperty().bindBidirectional(enterTourLogModel.dateProperty());
        logTotalTime.textProperty().bindBidirectional(enterTourLogModel.timeProperty());
        logComment.textProperty().bindBidirectional(enterTourLogModel.commentProperty());
        logDifficulty.valueProperty().bindBidirectional(enterTourLogModel.difficultyProperty());
        logRating.valueProperty().bindBidirectional(enterTourLogModel.ratingProperty());
    }

    public void setSelectedTourLogModel(TourLogModel selectedTourLogModel) {
        this.selectedTourLogModel = selectedTourLogModel;
    }
}
