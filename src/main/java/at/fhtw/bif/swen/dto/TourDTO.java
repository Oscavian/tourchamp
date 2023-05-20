package at.fhtw.bif.swen.dto;

import at.fhtw.bif.swen.util.TransportType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Date;
import java.util.List;

@Getter
public class TourDTO {
        private Integer id;
        private String name;
        private String description;
        private String start;
        private String destination;
        private TransportType transportType;
        private Integer distance;
        private Date estimatedTime;
        private Integer childFriendliness;
        private Integer popularity;
        private String mapURL;
        private byte[] image;
        private List<TourLogDTO> logs;
}
