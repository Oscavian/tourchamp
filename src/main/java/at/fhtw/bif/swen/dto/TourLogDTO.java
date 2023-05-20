package at.fhtw.bif.swen.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Duration;
import java.util.Date;

@Getter
@AllArgsConstructor
public class TourLogDTO {
    private Integer id;
    private Integer tour_id;
    private Date timestamp;
    private String comment;
    private Integer difficulty;
    private Duration totalTime;
    private Integer rating;
}
