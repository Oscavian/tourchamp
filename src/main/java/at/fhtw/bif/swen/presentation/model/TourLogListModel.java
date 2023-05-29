package at.fhtw.bif.swen.presentation.model;

import at.fhtw.bif.swen.dto.TourLogDTO;
import at.fhtw.bif.swen.presentation.service.TourService;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import lombok.Getter;

public class TourLogListModel {
    private final TourService tourService;

    private TourListItemModel selectedTour;

    @Getter
    private final ObservableList<TourLogModel> tourLogs = FXCollections.observableArrayList();

    public TourLogListModel(TourService tourService) {
        this.tourService = tourService;

        //update tour logs every time the list changes
        this.tourLogs.addListener((ListChangeListener<TourLogModel>) change -> {
            System.out.println("table changed!");
            tourService.updateTourLogs(tourLogs, selectedTour);
        });
    }

    public void setSelectedTour(TourListItemModel selectedTour) {
        this.selectedTour = selectedTour;
        this.tourLogs.clear();
    }

    public void addNewLog(TourLogModel tourLogModel){
        var model = TourLogModel.newInstance(tourLogModel);
        this.tourLogs.add(model);
    }

    public void deleteLog(TourLogModel tourLogModel) {
        this.tourLogs.remove(tourLogModel);
    }

    public void updateLog() {

    }
}
