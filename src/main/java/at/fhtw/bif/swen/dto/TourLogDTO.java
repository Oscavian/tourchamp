package at.fhtw.bif.swen.dto;

import java.util.Date;

public record TourLogDTO(
        Integer id,
        Integer tour_id,
        Date timestamp,
        String comment,
        String difficulty,
        String totalTime,
        Integer rating
) {}
