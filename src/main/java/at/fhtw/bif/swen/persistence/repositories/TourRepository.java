package at.fhtw.bif.swen.persistence.repositories;

import at.fhtw.bif.swen.persistence.entities.TourEntity;
import at.fhtw.bif.swen.persistence.ITourDataSource;

import java.util.List;


public class TourRepository implements ITourDataSource  {
    @Override
    public List<TourEntity> getAll() {
        return null;
    }

    @Override
    public void save(TourEntity tourEntity) {

    }

    @Override
    public void merge(TourEntity tourEntity) {

    }

    @Override
    public void delete(TourEntity tourEntity) {

    }
}
