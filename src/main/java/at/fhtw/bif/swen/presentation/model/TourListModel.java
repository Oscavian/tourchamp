package at.fhtw.bif.swen.presentation.model;

import at.fhtw.bif.swen.presentation.service.TourService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Objects;

public class TourListModel {

    private final TourService tourService;

    public TourListModel(TourService tourService) {
        this.tourService = tourService;
    }

    private final ObservableList<TourListItemModel> tours = FXCollections.observableArrayList();

    public ObservableList<TourListItemModel> getTours() {
        return tours;
    }

    public void addTour(TourDetailsModel tour) {
        tourService.save(tour);
        this.tours.add(TourListItemModel.From(tour));
    }

    public void removeTour(TourListItemModel tour) {
        if (this.tours.remove(tour)) {
            System.out.println("Tour " + tour.getName() + " removed");
        }
    }

    public TourDetailsModel loadDetailModel(TourListItemModel tourListItemModel) {
        return this.tourService.getById(Integer.valueOf(tourListItemModel.getId()));
    }

    public void reloadTourList() {
        tours.clear();
        tours.addAll(this.tourService.getTourList());
    }

    public void updateTour(TourDetailsModel tourDetailsModel) {
        tourService.update(tourDetailsModel);
        for (var t : tours) {
            if (Objects.equals(tourDetailsModel.getId(), t.getId())) {
                t.setName(tourDetailsModel.getName());
            }
        }
    }
}
