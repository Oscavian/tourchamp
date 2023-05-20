package at.fhtw.bif.swen.dto;

import at.fhtw.bif.swen.util.TransportType;
import lombok.Getter;
import lombok.Setter;

import java.time.Duration;
import java.util.List;

@Getter
@Setter
public class TourDTO {
        private Integer id;
        private String name;
        private String description;
        private String start;
        private String destination;
        private TransportType transportType;
        private Integer distance;
        private Duration estimatedTime;
        private Integer childFriendliness;
        private Integer popularity;
        private String mapURL;
        private byte[] image;
        private List<TourLogDTO> logs;

        public TourDTO(){}
}
