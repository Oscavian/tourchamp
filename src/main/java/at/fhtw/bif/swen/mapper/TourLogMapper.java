package at.fhtw.bif.swen.mapper;

import at.fhtw.bif.swen.dto.TourLogDTO;
import at.fhtw.bif.swen.persistence.entities.TourLogEntity;
import at.fhtw.bif.swen.presentation.model.TourLogModel;
import org.hibernate.cfg.NotYetImplementedException;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TourLogMapper {

    // Entity <-> DTO
    public static List<TourLogEntity> toEntity(List<TourLogDTO> dtos) {
        var list = new ArrayList<TourLogEntity>();
        if (dtos == null) {
            return list;
        }
        for (var dto : dtos) {
            TourLogEntity entity = new TourLogEntity();
            entity.setId(dto.getId());
            entity.setTourId(dto.getTourId());
            entity.setTimestamp(dto.getTimestamp());
            entity.setComment(dto.getComment());
            entity.setDifficulty(dto.getDifficulty());
            entity.setTotalTime(dto.getTotalTime());
            entity.setRating(dto.getRating());
            list.add(entity);
        }
        return list;
    }

    public static List<TourLogDTO> fromEntity(List<TourLogEntity> entities) {
        var list = new ArrayList<TourLogDTO>();
        if (entities == null) {
            return list;
        }

        for(var e : entities) {

            TourLogDTO dto = new TourLogDTO();
            dto.setId(e.getId());
            dto.setTourId(e.getTourId());
            dto.setTimestamp(e.getTimestamp());
            dto.setComment(e.getComment());
            dto.setDifficulty(e.getDifficulty());
            dto.setTotalTime(e.getTotalTime());
            dto.setRating(e.getRating());
            list.add(dto);
        }
        return list;
    }

    //Frontend Model <-> DTO

    public static List<TourLogModel> fromDTO(List<TourLogDTO> dtos) {
        var list = new ArrayList<TourLogModel>();
        if (dtos == null) {
            return list;
        }
        for (var dto : dtos){
            var model = new TourLogModel();
            model.setDate(dto.getTimestamp().toString());
            model.setTime(dto.getTotalTime().toString());
            model.setComment(dto.getComment());
            model.setRating(dto.getRating().toString());
            model.setDifficulty(dto.getDifficulty().toString());
            list.add(model);
        }
        return list;
    }

    public static List<TourLogDTO> toDTO(List<TourLogModel> tourLogModels) {

        List<TourLogDTO> list = new ArrayList<>();
        if (tourLogModels == null) {
            return list;
        }

        for (var tourLogModel : tourLogModels) {
            var dto = new TourLogDTO();
            dto.setTotalTime(Integer.parseInt(tourLogModel.getTime()));
            dto.setRating(Integer.parseInt(tourLogModel.getRating()));
            try {
                dto.setTimestamp(new Timestamp(new SimpleDateFormat("dd.MM.yyyy").parse(tourLogModel.getDate()).getTime()));
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            dto.setComment(tourLogModel.getComment());
            dto.setDifficulty(Integer.parseInt(tourLogModel.getDifficulty()));

            list.add(dto);
        }

        return list;
    }
}
