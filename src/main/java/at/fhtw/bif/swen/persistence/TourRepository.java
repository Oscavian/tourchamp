package at.fhtw.bif.swen.persistence;

import at.fhtw.bif.swen.persistence.entities.TourEntity;
import at.fhtw.bif.swen.persistence.ITourDataSource;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;


public class TourRepository implements ITourDataSource  {

    EntityManager entityManager;

    public TourRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void save(TourEntity tour) {
        entityManager.getTransaction().begin();
        entityManager.persist(tour);
        entityManager.getTransaction().commit();
    }

    @Override
    public void update(TourEntity tour) {
        entityManager.getTransaction().begin();
        entityManager.merge(tour);
        entityManager.getTransaction().commit();
    }

    @Override
    public void delete(TourEntity tour) {
        entityManager.getTransaction().begin();
        entityManager.remove(tour);
        entityManager.getTransaction().commit();
    }

    @Override
    public TourEntity getById(Integer id) {
        return entityManager.find(TourEntity.class, id);
    }

    @Override
    public List<TourEntity> search(String searchString) {
        Query query = entityManager.createQuery("SELECT t FROM TourEntity t WHERE name LIKE '%" + searchString + "%' " +
                "OR description LIKE '%" + searchString + "%' OR start LIKE '%" + searchString + "%' " +
                "OR start LIKE '%" + searchString + "%' OR t.id IN" +
                "(SELECT tl.tourId FROM TourLogEntity tl WHERE tl.comment LIKE '%" + searchString +"%')");
        return new ArrayList<>(query.getResultList());
    }

    @Override
    public List<TourEntity> getAll() {
        return new ArrayList<>(entityManager.createQuery("SELECT t FROM TourEntity t", TourEntity.class).getResultList());
    }

}
