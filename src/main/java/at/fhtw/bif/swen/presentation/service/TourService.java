package at.fhtw.bif.swen.presentation.service;

import at.fhtw.bif.swen.businesslogic.ITourLogic;
import at.fhtw.bif.swen.presentation.model.TourDetailsModel;
import at.fhtw.bif.swen.presentation.model.TourListItemModel;
import at.fhtw.bif.swen.presentation.model.TourLogModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class TourService implements IService {

    private ITourLogic tourLogic;

    private final HashMap<Integer, TourDetailsModel> tours = new HashMap<>();

    public TourService(ITourLogic tourLogic) {
        this.tourLogic = tourLogic;
    }

    @Override
    public List<TourDetailsModel> getAll() {
        return (List<TourDetailsModel>) tours.values();
    }


    public TourDetailsModel getById(Integer integer) {
        tours.forEach((k,v) -> {
            System.out.println(k + " " + v.getName());
        });
        return tours.get(integer);
    }

    @Override
    public void saveTour(TourDetailsModel tourDetailsModel) {
        //only for testing
        Integer id = new Random().nextInt(1000);
        tourDetailsModel.setId(String.valueOf(id));
        tours.put(id, tourDetailsModel);

        System.out.println("Tour saved! " + tourDetailsModel.getId());
    }

    @Override
    public void updateTour(TourDetailsModel tourDetailsModel) {
        tours.put(Integer.valueOf(tourDetailsModel.getId()), tourDetailsModel);
    }

    @Override
    public void deleteTour(TourDetailsModel tourDetailsModel) {

    }

    public List<TourListItemModel> getTourList() {
        var list = new ArrayList<TourListItemModel>();

        for (var t : tours.values()) {
            list.add(TourListItemModel.From(t));
        }

        return list;
    }
}
