package at.fhtw.bif.swen.persistence.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

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

    //@Temporal(TemporalType.DATE)
    LocalDate timestamp;

    String comment;

    Integer difficulty;

    Integer totalTime;

    Integer rating;

    public TourLogEntity(){
    }
}
