package at.fhtw.bif.swen.presentation.service;

import at.fhtw.bif.swen.businesslogic.ITourLogic;
import at.fhtw.bif.swen.businesslogic.TourLogic;
import at.fhtw.bif.swen.mapper.TourMapper;
import at.fhtw.bif.swen.presentation.model.TourDetailsModel;
import at.fhtw.bif.swen.presentation.model.TourListItemModel;
import at.fhtw.bif.swen.presentation.model.TourLogModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class TourService implements IService {

    private ITourLogic tourLogic;

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

    }

    public List<TourListItemModel> getTourList() {
        var list = tourLogic.getAll();
        var newList = new ArrayList<TourListItemModel>();

        for (var t : list) {
            newList.add(TourListItemModel.From(TourMapper.fromDTO(t)));
        }

        return newList;
    }
}
