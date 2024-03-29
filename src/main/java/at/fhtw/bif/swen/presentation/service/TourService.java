package at.fhtw.bif.swen.presentation.service;

import at.fhtw.bif.swen.businesslogic.ITourLogic;
import at.fhtw.bif.swen.businesslogic.TourLogic;
import at.fhtw.bif.swen.dto.TourDTO;
import at.fhtw.bif.swen.mapper.TourMapper;
import at.fhtw.bif.swen.presentation.model.TourDetailsModel;
import at.fhtw.bif.swen.presentation.model.TourListItemModel;
import at.fhtw.bif.swen.presentation.model.TourLogModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class TourService implements IService {

    private final ITourLogic tourLogic;
    private final Logger logger = LogManager.getLogger(getClass().getName());


    public TourService(ITourLogic tourLogic) {
        this.tourLogic = tourLogic;
    }


    public TourDetailsModel getById(Integer integer) {
        return TourMapper.fromDTO(tourLogic.getTourById(integer));
    }

    @Override
    public void saveTour(TourDetailsModel tourDetailsModel) {
        tourLogic.saveTour(TourMapper.fromNewDetailsModel(tourDetailsModel));
    }

    @Override
    public void updateTour(TourDetailsModel tourDetailsModel) {
        tourLogic.updateTour(TourMapper.fromDetailsModel(tourDetailsModel));
    }

    @Override
    public void deleteTour(TourDetailsModel tourDetailsModel) {
        tourLogic.deleteTour(Integer.parseInt(tourDetailsModel.getId()));
    }

    @Override
    public void importTours(File tourFile) {
        this.tourLogic.importTours(tourFile);
    }

    @Override
    public String exportTours() {
        return this.tourLogic.exportTours();
    }

    public List<TourListItemModel> getTourList() {
        var list = tourLogic.getAll();
        var newList = new ArrayList<TourListItemModel>();

        for (var t : list) {
            newList.add(TourListItemModel.From(TourMapper.fromDTO(t)));
        }

        return newList;
    }

    public List<TourListItemModel> searchTours(String searchString) {
        var list = tourLogic.search(searchString);
        var newList = new ArrayList<TourListItemModel>();

        for (var t : list) {
            newList.add(TourListItemModel.From(TourMapper.fromDTO(t)));
        }
        return newList;
    }

    public ArrayList<TourDTO> getTourDTOs(){
        return (ArrayList<TourDTO>) this.tourLogic.getAll();
    }
    public TourDTO getDTOById(Integer id) {
        return this.tourLogic.getTourById(id);
    }

}
