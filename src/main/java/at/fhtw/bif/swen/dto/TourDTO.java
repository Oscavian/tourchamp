package at.fhtw.bif.swen.dto;

import at.fhtw.bif.swen.util.TransportType;

public record TourDTO(
        Integer id,
        String name,
        String description,
        String start,
        String destination,
        TransportType transportType,
        Integer childFriendliness,
        Integer popularity

) {}
