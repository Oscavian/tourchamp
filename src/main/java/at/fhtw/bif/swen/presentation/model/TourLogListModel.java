package at.fhtw.bif.swen.presentation.model;

import at.fhtw.bif.swen.dto.TourLogDTO;
import at.fhtw.bif.swen.presentation.service.TourLogService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import lombok.Getter;

public class TourLogListModel {
    private final TourLogService tourLogService;

    @Getter
    private final ObservableList<TourLogModel> tourLogs = FXCollections.observableArrayList(
            new TourLogModel("test", "12345", "000", "test", "50", "100")
    );

    public TourLogListModel(TourLogService tourLogService) {
        this.tourLogService = tourLogService;
    }

    public void addNewLog(TourLogModel tourLogModel){
        this.tourLogs.add(TourLogModel.newInstance(tourLogModel));
    }

    public void deleteLog() {
    }

    public void updateLog() {

    }
}
