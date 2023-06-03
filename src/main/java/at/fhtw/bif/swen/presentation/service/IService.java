package at.fhtw.bif.swen.presentation.service;

import at.fhtw.bif.swen.presentation.model.TourDetailsModel;
import at.fhtw.bif.swen.presentation.model.TourListItemModel;
import at.fhtw.bif.swen.presentation.model.TourLogModel;

import java.io.File;
import java.util.List;

public interface IService {
    TourDetailsModel getById(Integer id);
    void saveTour(TourDetailsModel t);
    void updateTour(TourDetailsModel t);
    void deleteTour(TourDetailsModel t);

    void importTours(File tourFile);
    String exportTours();

}
