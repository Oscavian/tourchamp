package at.fhtw.bif.swen.dto;

public record TourLogDTO(
        Integer id,
        Integer tour_id,
        String datetime,
        String comment,
        String difficulty,
        String totalTime,
        Integer rating
) {}
