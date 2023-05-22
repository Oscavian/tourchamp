package at.fhtw.bif.swen.persistence;

import at.fhtw.bif.swen.persistence.entities.TourEntity;
import at.fhtw.bif.swen.persistence.repositories.IRepository;

public interface ITourDataSource extends IRepository<TourEntity, Integer> {
}
