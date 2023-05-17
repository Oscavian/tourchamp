package at.fhtw.bif.swen.dto;

import at.fhtw.bif.swen.util.TransportType;
import java.util.Date;
import java.util.List;

public record TourDTO(
        Integer id,
        String name,
        String description,
        String start,
        String destination,
        TransportType transportType,
        Integer distance,
        Date estimatedTime,
        Integer childFriendliness,
        Integer popularity,

        String mapURL, //dunno how to tra
        List<TourLogDTO> logs

) {}
