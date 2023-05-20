package at.fhtw.bif.swen.persistence.entities;

import at.fhtw.bif.swen.util.Difficulty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.Duration;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "tourlogs")
public class TourLogEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(name = "tour_id")
    Integer tourId;

    @Temporal(TemporalType.TIMESTAMP)
    Date timestamp;

    String comment;

    Difficulty difficulty;

    Duration totalTime;

    Integer rating;

    public TourLogEntity(){
    }
}
