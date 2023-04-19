package at.fhtw.bif.swen.presentation.model;

import at.fhtw.bif.swen.presentation.service.TourService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TourListModel {

    private final TourService tourService;

    public TourListModel(TourService tourService) {
        this.tourService = tourService;
    }

    private ObservableList<TourModel> tours = FXCollections.observableArrayList();

    public ObservableList<TourModel> getTours() {
        return tours;
    }

    public void addTour(TourModel tour) {this.tours.add(tour);}

    public void removeTour(TourModel tour) {this.tours.remove(tour);}
}
