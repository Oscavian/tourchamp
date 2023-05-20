package at.fhtw.bif.swen.businesslogic.services.MapQuestAPIService;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TourMapData {
    private String sessionId;
    private int distance;
    private String boundingBox;
}
