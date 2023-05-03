package at.fhtw.bif.swen.persistence.entities;

import at.fhtw.bif.swen.util.TransportType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
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

    Integer distance; //km

    @Column(name = "estimated_time")
    @Temporal(TemporalType.TIME)
    Date estimatedTime;

    @Column(name = "child_friendliness")
    Integer childFriendliness;  // 0-100

    Integer popularity; // 0-100

    @Column(name = "route_info")
    String routeInfo; //Path to map image

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER
    )
    @JoinColumn(referencedColumnName = "tour_id")
    List<TourLogEntity> tourLogs = new ArrayList<>();

}
