package at.fhtw.bif.swen.dto;

import at.fhtw.bif.swen.util.Difficulty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Duration;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TourLogDTO {
    Integer id;
    Integer tourId;
    Date timestamp;
    String comment;
    Difficulty difficulty;
    Duration totalTime;
    Integer rating;
}
