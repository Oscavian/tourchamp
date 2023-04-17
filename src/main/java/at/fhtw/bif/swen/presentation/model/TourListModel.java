package at.fhtw.bif.swen.presentation.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TourListModel {

    private ObservableList<TourModel> tours = FXCollections.observableArrayList();

    public ObservableList<TourModel> getTours() {
        return tours;
    }

    public void addTour(TourModel tour) {this.tours.add(tour);}

    public void removeTour(TourModel tour) {this.tours.remove(tour);}
}
