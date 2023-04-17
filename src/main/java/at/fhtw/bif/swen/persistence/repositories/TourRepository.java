package at.fhtw.bif.swen.persistence.repositories;

import at.fhtw.bif.swen.persistence.entities.TourEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TourRepository extends JpaRepository<TourEntity, Integer> {
}
