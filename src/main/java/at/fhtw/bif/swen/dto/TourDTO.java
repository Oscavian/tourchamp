package at.fhtw.bif.swen.dto;

public record TourDTO(
        Integer id,
        String name,
        String description,
        String start,
        String destination,
        String transportType,
        String childFriendliness,
        String popularity

) {}
