package at.fhtw.bif.swen.presentation.service;

import at.fhtw.bif.swen.businesslogic.ITourLogLogic;
import at.fhtw.bif.swen.presentation.model.TourLogModel;

import java.util.List;

public class TourLogService implements IService<TourLogModel, Integer> {

    private ITourLogLogic tourLogLogic;

    public TourLogService(ITourLogLogic tourLogLogic) {
        this.tourLogLogic = tourLogLogic;
    }
    @Override
    public List<TourLogModel> getAll() {
        throw new UnsupportedOperationException("TODO");
    }

    @Override
    public TourLogModel getById(Integer id) {
        throw new UnsupportedOperationException("TODO");
    }

    @Override
    public void save(TourLogModel tourLogModel) {
        throw new UnsupportedOperationException("TODO");
    }

    @Override
    public void update(TourLogModel tourLogModel) {
        throw new UnsupportedOperationException("TODO");
    }

    @Override
    public void delete(TourLogModel tourLogModel) {

    }
}
