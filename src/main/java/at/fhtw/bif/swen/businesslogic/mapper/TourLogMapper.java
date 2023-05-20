package at.fhtw.bif.swen.businesslogic.mapper;

import at.fhtw.bif.swen.dto.TourLogDTO;
import at.fhtw.bif.swen.persistence.entities.TourLogEntity;

public class TourLogMapper implements IMapper<TourLogDTO, TourLogEntity> {

    public TourLogEntity toEntity(TourLogDTO dto) {
        throw new UnsupportedOperationException("TODO");
    }

    @Override
    public TourLogDTO fromEntity(TourLogEntity entity) {
        throw new UnsupportedOperationException("TODO");
    }
}
