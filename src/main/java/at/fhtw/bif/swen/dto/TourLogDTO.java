package at.fhtw.bif.swen.dto;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
public class TourLogDTO {
    private Integer id;
    private Integer tourId;
    private LocalDate timestamp;
    private String comment;
    private Integer difficulty;
    private Integer totalTime;
    private Integer rating;

    public TourLogDTO() {}
}
