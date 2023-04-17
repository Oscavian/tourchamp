package at.fhtw.bif.swen.persistence.repositories;

import at.fhtw.bif.swen.persistence.entities.TourLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TourLogRepository extends JpaRepository<TourLogEntity, Integer> {
}
