package at.fhtw.bif.swen.mapper;

import at.fhtw.bif.swen.dto.TourDTO;
import at.fhtw.bif.swen.persistence.entities.TourEntity;
import at.fhtw.bif.swen.presentation.model.TourDetailsModel;
import at.fhtw.bif.swen.util.TransportType;
import javafx.beans.property.StringProperty;

import java.time.Duration;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class TourMapper {

    // Entity <-> DTO
    public static TourEntity toEntity(TourDTO dto) {
        TourEntity entity = new TourEntity();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setStart(dto.getStart());
        entity.setDestination(dto.getDestination());
        entity.setTransportType(dto.getTransportType());
        entity.setDistance(dto.getDistance());
        entity.setChildFriendliness(dto.getChildFriendliness());
        entity.setPopularity(dto.getPopularity());
        return entity;
    }

    public static TourDTO fromEntity(TourEntity entity) {
        TourDTO dto = new TourDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setStart(entity.getStart());
        dto.setDestination(entity.getDestination());
        dto.setTransportType(entity.getTransportType());
        dto.setDistance(entity.getDistance());
        dto.setChildFriendliness(entity.getChildFriendliness());
        dto.setPopularity(entity.getPopularity());
        return dto;
    }



    // Frontend Model <-> DTO

    public static TourDTO fromDetailsModel(TourDetailsModel tourDetailsModel) {
        TourDTO tourDTO = new TourDTO();
        tourDTO.setId(Integer.parseInt(tourDetailsModel.getId()));
        tourDTO.setName(tourDetailsModel.getName());
        tourDTO.setDescription(tourDetailsModel.getDescription());
        tourDTO.setStart(tourDetailsModel.getStart());
        tourDTO.setDestination(tourDetailsModel.getDestination());
        tourDTO.setTransportType(parseTransportType(tourDetailsModel.getTransportType()));
        tourDTO.setDistance(Integer.parseInt(tourDetailsModel.getTourDistance()));
        tourDTO.setEstimatedTime(parseDuration(tourDetailsModel.getDuration()));
        tourDTO.setChildFriendliness(Integer.parseInt(tourDetailsModel.getChildFriendliness()));
        tourDTO.setPopularity(Integer.parseInt(tourDetailsModel.getPopularity()));
        tourDTO.setMapURL(null);  // Assuming it is not available in the TourDetailsModel
        tourDTO.setLogs(new ArrayList<>());  // Assuming it is empty initially
        return tourDTO;
    }

    public static TourDetailsModel fromDTO(TourDTO tourDTO) {
        TourDetailsModel tourDetailsModel = new TourDetailsModel();
        tourDetailsModel.setId(tourDTO.getId().toString());
        tourDetailsModel.setName(tourDTO.getName());
        tourDetailsModel.setDescription(tourDTO.getDescription());
        tourDetailsModel.setStart(tourDTO.getStart());
        tourDetailsModel.setDestination(tourDTO.getDestination());
        tourDetailsModel.setTransportType(tourDTO.getTransportType().name());
        tourDetailsModel.setTourDistance(tourDTO.getDistance().toString());
        tourDetailsModel.setDuration(tourDTO.getEstimatedTime().toString());
        tourDetailsModel.setChildFriendliness(tourDTO.getChildFriendliness().toString());
        tourDetailsModel.setPopularity(tourDTO.getPopularity().toString());

        tourDetailsModel.addAllLogs(TourLogMapper.fromDTO(tourDTO.getLogs()));
        tourDetailsModel.setMapURL(tourDTO.getMapURL());
        return tourDetailsModel;
    }

    private static TransportType parseTransportType(String property) {
        try {
            return TransportType.valueOf(property);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    private static Duration parseDuration(String property) {
        try {
            return Duration.parse(property);
        } catch (DateTimeParseException e) {
            return null;
        }
    }
}
