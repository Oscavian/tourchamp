package at.fhtw.bif.swen.mapper;

import at.fhtw.bif.swen.dto.TourLogDTO;
import at.fhtw.bif.swen.persistence.entities.TourLogEntity;
import at.fhtw.bif.swen.presentation.model.TourLogModel;
import org.hibernate.cfg.NotYetImplementedException;

import java.util.List;

public class TourLogMapper {

    // Entity <-> DTO
    public static TourLogEntity toEntity(TourLogDTO dto) {
        TourLogEntity entity = new TourLogEntity();
        entity.setId(dto.getId());
        entity.setTourId(dto.getTourId());
        entity.setTimestamp(dto.getTimestamp());
        entity.setComment(dto.getComment());
        entity.setDifficulty(dto.getDifficulty());
        entity.setTotalTime(dto.getTotalTime());
        entity.setRating(dto.getRating());
        return entity;
    }

    public static TourLogDTO fromEntity(TourLogEntity entity) {
        TourLogDTO dto = new TourLogDTO();
        dto.setId(entity.getId());
        dto.setTourId(entity.getTourId());
        dto.setTimestamp(entity.getTimestamp());
        dto.setComment(entity.getComment());
        dto.setDifficulty(entity.getDifficulty());
        dto.setTotalTime(entity.getTotalTime());
        dto.setRating(entity.getRating());
        return dto;
    }

    //Frontend Model <-> DTO
    // todo: log mapping model <-> dto

    public static List<TourLogModel> fromDTO(List<TourLogDTO> dtos) {
        throw new UnsupportedOperationException();
    }
}
