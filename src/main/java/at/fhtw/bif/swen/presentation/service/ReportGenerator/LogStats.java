package at.fhtw.bif.swen.presentation.service.ReportGenerator;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LogStats {
    private Integer difficulty;
    private Integer totalTime;
    private Integer rating;

    public LogStats() {
        this.difficulty = 0;
        this.totalTime = 0;
        this.rating = 0;
    }
}
