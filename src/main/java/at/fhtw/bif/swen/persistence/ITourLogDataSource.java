package at.fhtw.bif.swen.persistence;

import at.fhtw.bif.swen.persistence.entities.TourLogEntity;
import at.fhtw.bif.swen.persistence.repositories.IRepository;

import javax.persistence.criteria.CriteriaBuilder;

public interface ITourLogDataSource extends IRepository<TourLogEntity, Integer> {
}
