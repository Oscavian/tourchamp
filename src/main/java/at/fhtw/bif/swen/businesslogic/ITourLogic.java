package at.fhtw.bif.swen.businesslogic;

import at.fhtw.bif.swen.dto.TourDTO;

import java.io.File;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

public interface ITourLogic {

    void saveTour(TourDTO tourDTO);

    void updateTour(TourDTO tourDTO);

    void deleteTour(TourDTO tourDTO);

    //fetch TourListItems
    List<TourDTO> getAll();

    TourDTO getTourById(Integer id);

    List<TourDTO> search(String searchString);

    void importTours(File tourFile);

    //export into application folder exports/
    void exportTours();









}
