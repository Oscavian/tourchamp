package at.fhtw.bif.swen.presentation.model;

import at.fhtw.bif.swen.presentation.service.TourService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;

public class TourListModel {

    private final TourService tourService;

    private final ObservableList<TourListItemModel> tours = FXCollections.observableArrayList();

    private final Logger logger = LogManager.getLogger(getClass().getName());

    public TourListModel(TourService tourService) {
        this.tourService = tourService;
    }

    public ObservableList<TourListItemModel> getTours() {
        return tours;
    }

    public void addTour(TourDetailsModel tour) {
        logger.debug("Save tour '" + tour.getName() + "'");
        tourService.saveTour(tour);
        this.tours.add(TourListItemModel.From(tour));
    }

    public void removeTour(TourDetailsModel tour) {
        logger.debug("Delete tour '" + tour.getName() + "'");
        tourService.deleteTour(tour);
        reloadTourList();
    }

    public TourDetailsModel loadDetailModel(TourListItemModel tourListItemModel) {
        logger.debug("Load details of tour '" + tourListItemModel.getName() + "'");
        return this.tourService.getById(Integer.valueOf(tourListItemModel.getId()));
    }

    public void reloadTourList() {
        logger.debug("Reload tour list");
        tours.clear();
        tours.addAll(this.tourService.getTourList());
    }

    public void reloadTourList(String searchString) {
        logger.debug("Reload tour list with filter '" + searchString + "'");
        tours.clear();
        tours.addAll(this.tourService.searchTours(searchString));
    }
    public void updateTour(TourDetailsModel tourDetailsModel) {
        logger.debug("Update Tour '" + tourDetailsModel.getName() + "'");
        tourService.updateTour(tourDetailsModel);
        for (var t : tours) {
            if (Objects.equals(tourDetailsModel.getId(), t.getId())) {
                t.setName(tourDetailsModel.getName());
            }
        }
    }
}
