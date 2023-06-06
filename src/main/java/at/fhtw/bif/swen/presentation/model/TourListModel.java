package at.fhtw.bif.swen.presentation.model;

import at.fhtw.bif.swen.presentation.service.TourService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Objects;

public class TourListModel {

    private final TourService tourService;

    private final ObservableList<TourListItemModel> tours = FXCollections.observableArrayList();

    public TourListModel(TourService tourService) {
        this.tourService = tourService;
    }

    public ObservableList<TourListItemModel> getTours() {
        return tours;
    }

    public void addTour(TourDetailsModel tour) {
        tourService.saveTour(tour);
        this.tours.add(TourListItemModel.From(tour));
    }

    public void removeTour(TourDetailsModel tour) {
        tourService.deleteTour(tour);
        reloadTourList();
    }

    public TourDetailsModel loadDetailModel(TourListItemModel tourListItemModel) {
        return this.tourService.getById(Integer.valueOf(tourListItemModel.getId()));
    }

    public void reloadTourList() {
        tours.clear();
        tours.addAll(this.tourService.getTourList());
    }

    public void reloadTourList(String searchString) {
        tours.clear();
        tours.addAll(this.tourService.searchTours(searchString));
    }
    public void updateTour(TourDetailsModel tourDetailsModel) {
        tourService.updateTour(tourDetailsModel);
        for (var t : tours) {
            if (Objects.equals(tourDetailsModel.getId(), t.getId())) {
                t.setName(tourDetailsModel.getName());
            }
        }
    }
}
