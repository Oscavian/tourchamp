package at.fhtw.bif.swen.presentation.service;

import at.fhtw.bif.swen.businesslogic.ITourLogic;
import at.fhtw.bif.swen.presentation.model.TourModel;

import java.util.List;

public class TourService implements IService<TourModel, Integer> {

    private ITourLogic tourLogic;

    public TourService(ITourLogic tourLogic) {
        this.tourLogic = tourLogic;
    }

    @Override
    public List<TourModel> getAll() {
        throw new UnsupportedOperationException("TODO");
    }

    @Override
    public TourModel getById(Integer integer) {
        throw new UnsupportedOperationException("TODO");
    }

    @Override
    public Integer save(TourModel tourModel) {
        throw new UnsupportedOperationException("TODO");
    }

    @Override
    public void update(TourModel tourModel) {
        throw new UnsupportedOperationException("TODO");
    }

    @Override
    public void delete(TourModel tourModel) {
        throw new UnsupportedOperationException("TODO");
    }
}
