package at.fhtw.bif.swen.businesslogic.mapper;

import at.fhtw.bif.swen.dto.TourDTO;
import at.fhtw.bif.swen.persistence.entities.TourEntity;

public class TourMapperImpl implements IMapper<TourDTO, TourEntity> {

    @Override
    public TourEntity toEntity(TourDTO dto) {
        throw new UnsupportedOperationException("TODO");
    }

    @Override
    public TourDTO fromEntity(TourEntity entity) {
        throw new UnsupportedOperationException("TODO");
    }
}
