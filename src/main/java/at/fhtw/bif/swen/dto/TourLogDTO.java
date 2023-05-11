package at.fhtw.bif.swen.dto;

import at.fhtw.bif.swen.util.Difficulty;

import java.time.Duration;
import java.util.Date;

public record TourLogDTO(
        Integer id,
        Integer tour_id,
        Date timestamp,
        String comment,
        Difficulty difficulty,
        Duration totalTime,
        Integer rating
) {}
