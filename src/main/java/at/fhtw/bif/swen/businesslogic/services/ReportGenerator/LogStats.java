package at.fhtw.bif.swen.businesslogic.services.ReportGenerator;

import lombok.Getter;
import lombok.Setter;

import java.time.Duration;

@Getter
@Setter
public class LogStats {
    private Integer difficulty;
    private Duration totalTime;
    private Integer rating;
}
