package at.fhtw.bif.swen.dto;

import at.fhtw.bif.swen.util.TransportType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TourDTO {
    Integer id;
    String name;
    String description;
    String start;
    String destination;
    TransportType transportType;
    Integer distance;
    Date estimatedTime;
    Integer childFriendliness;
    Integer popularity;
    String mapURL;
    List<TourLogDTO> logs;



}
