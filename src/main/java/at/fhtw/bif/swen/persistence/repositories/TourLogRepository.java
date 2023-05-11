package at.fhtw.bif.swen.persistence.repositories;

import at.fhtw.bif.swen.persistence.entities.TourEntity;
import at.fhtw.bif.swen.persistence.entities.TourLogEntity;
import at.fhtw.bif.swen.persistence.ITourLogDataSource;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public class TourLogRepository implements ITourLogDataSource {

    EntityManager entityManager;

    public TourLogRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void save(TourLogEntity tourLog) {
        entityManager.getTransaction().begin();
        entityManager.persist(tourLog);
        entityManager.getTransaction().commit();
    }

    @Override
    public void update(TourLogEntity tourLog) {
        entityManager.getTransaction().begin();
        entityManager.merge(tourLog);
        entityManager.getTransaction().commit();
    }

    @Override
    public void delete(TourLogEntity tourLog) {
        entityManager.getTransaction().begin();
        entityManager.remove(tourLog);
        entityManager.getTransaction().commit();
    }

    @Override
    public List<TourLogEntity> getAll() {
        return new ArrayList<>(entityManager.createQuery("SELECT t FROM TourLogEntity t", TourLogEntity.class).getResultList());
    }
}
