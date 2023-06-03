package at.fhtw.bif.swen.persistence.entities;

import at.fhtw.bif.swen.util.TransportType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@Getter
@Setter
@Table(name = "tours")
public class TourEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    String name;

    String description;

    String start;

    String destination;

    @Column(name = "transport_type")
    TransportType transportType;

    @Column(name = "child_friendliness")
    Integer childFriendliness;  // 0-100

    Integer popularity; // 0-100

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER
    )
    @JoinColumn(name = "tour_id")
    List<TourLogEntity> tourLogs = new ArrayList<>();

    public TourEntity() {
    }

    public TourEntity(String name, String description, String start, String destination, Integer transportType, Integer childFriendliness, Integer popularity) {
        this.name = name;
        this.description = description;
        this.start = start;
        this.destination = destination;
        this.transportType = TransportType.parseValue(transportType);
        this.childFriendliness = childFriendliness;
        this.popularity = popularity;
    }

    public TourEntity(String name) {
        this.name = name;
    }
}