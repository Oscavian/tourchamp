package at.fhtw.bif.swen.persistence;

import at.fhtw.bif.swen.persistence.entities.TourEntity;

import java.util.List;

public interface ITourDataSource {
    List<TourEntity> getAll();
    void save(TourEntity tour);
    void update(TourEntity tour);
    void delete(TourEntity tour);
    TourEntity getById(Integer id);
    List<TourEntity> search(String searchString);
}
