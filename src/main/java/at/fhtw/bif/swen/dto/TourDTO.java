package at.fhtw.bif.swen.dto;

import at.fhtw.bif.swen.util.TransportType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class TourDTO {
        private Integer id;
        private String name;
        private String description;
        private String start;
        private String destination;
        private TransportType transportType;
        private Integer estimatedTime;
        private Integer childFriendliness;
        private Integer popularity;
        private List<TourLogDTO> logs;

        public TourDTO(){}
}
