package at.fhtw.bif.swen.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.Duration;
import java.util.Date;

@Getter
@Setter
public class TourLogDTO {
    private Integer id;
    private Integer tourId;
    private Date timestamp;
    private String comment;
    private Integer difficulty;
    private Integer totalTime;
    private Integer rating;

    public TourLogDTO() {}
}
