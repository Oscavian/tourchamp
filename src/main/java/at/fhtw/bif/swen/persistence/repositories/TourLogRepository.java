package at.fhtw.bif.swen.persistence.repositories;

import at.fhtw.bif.swen.persistence.entities.TourLogEntity;
import at.fhtw.bif.swen.persistence.ITourLogDataSource;

import java.util.List;

public class TourLogRepository implements ITourLogDataSource {
    @Override
    public List<TourLogEntity> getAll() {
        return null;
    }

    @Override
    public void save(TourLogEntity tourLogEntity) {

    }

    @Override
    public void merge(TourLogEntity tourLogEntity) {

    }

    @Override
    public void delete(TourLogEntity tourLogEntity) {

    }
}
