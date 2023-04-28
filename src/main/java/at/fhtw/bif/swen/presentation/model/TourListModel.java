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

    public void addTour(TourModel tour) {

        //editing is done by identifying via name, TODO improve edit save behaviour
        //editing & saving with different name creates a new tour
        tours.stream()
                .filter((t) -> t.getName().equals(tour.getName()))
                .findAny().ifPresent(duplicate -> this.tours.remove(duplicate));

        this.tours.add(tour);
    }

    public void removeTour(TourModel tour) {
        if (this.tours.remove(tour)) {
            System.out.println("Tour " + tour.getName() + " removed");
        }
    }
}
