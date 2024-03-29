package at.fhtw.bif.swen.mapper;

import at.fhtw.bif.swen.dto.TourDTO;
import at.fhtw.bif.swen.persistence.entities.TourEntity;
import at.fhtw.bif.swen.presentation.model.TourDetailsModel;
import at.fhtw.bif.swen.util.TransportType;

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
        entity.setTourLogs(TourLogMapper.toEntity(dto.getLogs()));
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
        dto.setLogs(TourLogMapper.fromEntity(entity.getTourLogs()));
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
        tourDTO.setTransportType(TransportType.parseName(tourDetailsModel.getTransportTypeString()));
        tourDTO.setLogs(TourLogMapper.toDTO(tourDetailsModel.getTourLogs()));
        return tourDTO;
    }

    public static TourDTO fromNewDetailsModel(TourDetailsModel tourDetailsModel) {
        TourDTO tourDTO = new TourDTO();
        tourDTO.setName(tourDetailsModel.getName());
        tourDTO.setDescription(tourDetailsModel.getDescription());
        tourDTO.setStart(tourDetailsModel.getStart());
        tourDTO.setDestination(tourDetailsModel.getDestination());
        tourDTO.setTransportType(TransportType.parseName(tourDetailsModel.getTransportTypeString()));
        return tourDTO;
    }

    public static TourDetailsModel fromDTO(TourDTO tourDTO) {
        TourDetailsModel tourDetailsModel = new TourDetailsModel();
        tourDetailsModel.setId(tourDTO.getId().toString());
        tourDetailsModel.setName(tourDTO.getName());
        tourDetailsModel.setDescription(tourDTO.getDescription());
        tourDetailsModel.setStart(tourDTO.getStart());
        tourDetailsModel.setDestination(tourDTO.getDestination());
        tourDetailsModel.setTransportTypeString(tourDTO.getTransportType().getName());
        tourDetailsModel.setEstimatedTime(tourDTO.getEstimatedTime().toString());
        tourDetailsModel.setChildFriendliness(tourDTO.getChildFriendliness().toString());
        tourDetailsModel.setPopularity(tourDTO.getPopularity().toString());
        tourDetailsModel.addAllLogs(TourLogMapper.fromDTO(tourDTO.getLogs()));
        return tourDetailsModel;
    }

}
