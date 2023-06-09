package at.fhtw.bif.swen.mapper;

import at.fhtw.bif.swen.dto.TourDTO;
import at.fhtw.bif.swen.persistence.entities.TourEntity;
import at.fhtw.bif.swen.presentation.model.TourDetailsModel;
import at.fhtw.bif.swen.util.TransportType;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TourMapperTest {

    @Test
    public void testToEntity() {
        TourDTO dto = new TourDTO();
        dto.setId(1);
        dto.setName("Tour 1");
        dto.setDescription("Description 1");
        dto.setStart("Start Location");
        dto.setDestination("Destination Location");
        dto.setTransportType(TransportType.AFOOT);
        dto.setLogs(new ArrayList<>());

        TourEntity entity = TourMapper.toEntity(dto);

        assertEquals(dto.getId(), entity.getId());
        assertEquals(dto.getName(), entity.getName());
        assertEquals(dto.getDescription(), entity.getDescription());
        assertEquals(dto.getStart(), entity.getStart());
        assertEquals(dto.getDestination(), entity.getDestination());
        assertEquals(dto.getTransportType(), entity.getTransportType());
        assertEquals(dto.getLogs(), TourLogMapper.fromEntity(entity.getTourLogs()));
    }

    @Test
    public void testFromEntity() {
        TourEntity entity = new TourEntity();
        entity.setId(1);
        entity.setName("Tour 1");
        entity.setDescription("Description 1");
        entity.setStart("Start Location");
        entity.setDestination("Destination Location");
        entity.setTransportType(TransportType.AFOOT);
        entity.setTourLogs(new ArrayList<>());

        TourDTO dto = TourMapper.fromEntity(entity);

        assertEquals(entity.getId(), dto.getId());
        assertEquals(entity.getName(), dto.getName());
        assertEquals(entity.getDescription(), dto.getDescription());
        assertEquals(entity.getStart(), dto.getStart());
        assertEquals(entity.getDestination(), dto.getDestination());
        assertEquals(entity.getTransportType(), dto.getTransportType());
        assertEquals(entity.getTourLogs(), TourLogMapper.toEntity(dto.getLogs()));
    }

    @Test
    public void testFromDetailsModel() {
        TourDetailsModel tourDetailsModel = new TourDetailsModel();
        tourDetailsModel.setId("1");
        tourDetailsModel.setName("Tour 1");
        tourDetailsModel.setDescription("Description 1");
        tourDetailsModel.setStart("Start Location");
        tourDetailsModel.setDestination("Destination Location");
        tourDetailsModel.setTransportTypeString("BUS");
        tourDetailsModel.addAllLogs(new ArrayList<>());

        TourDTO dto = TourMapper.fromDetailsModel(tourDetailsModel);

        assertEquals(Integer.parseInt(tourDetailsModel.getId()), dto.getId());
        assertEquals(tourDetailsModel.getName(), dto.getName());
        assertEquals(tourDetailsModel.getDescription(), dto.getDescription());
        assertEquals(tourDetailsModel.getStart(), dto.getStart());
        assertEquals(tourDetailsModel.getDestination(), dto.getDestination());
        assertEquals(TransportType.parseName(tourDetailsModel.getTransportTypeString()), dto.getTransportType());
    }

    @Test
    public void testFromNewDetailsModel() {
        TourDetailsModel tourDetailsModel = new TourDetailsModel();
        tourDetailsModel.setName("Tour 1");
        tourDetailsModel.setDescription("Description 1");
        tourDetailsModel.setStart("Start Location");
        tourDetailsModel.setDestination("Destination Location");
        tourDetailsModel.setTransportTypeString("CAR");

        TourDTO dto = TourMapper.fromNewDetailsModel(tourDetailsModel);

        assertEquals(tourDetailsModel.getName(), dto.getName());
        assertEquals(tourDetailsModel.getDescription(), dto.getDescription());
        assertEquals(tourDetailsModel.getStart(), dto.getStart());
        assertEquals(tourDetailsModel.getDestination(), dto.getDestination());
        assertEquals(TransportType.parseName(tourDetailsModel.getTransportTypeString()), dto.getTransportType());
    }

    @Test
    public void testFromDTO() {
        TourDTO dto = new TourDTO();
        dto.setId(1);
        dto.setName("Tour 1");
        dto.setDescription("Description 1");
        dto.setStart("Start Location");
        dto.setDestination("Destination Location");
        dto.setTransportType(TransportType.CAR);
        dto.setEstimatedTime(60);
        dto.setChildFriendliness(5);
        dto.setPopularity(10);
        dto.setLogs(new ArrayList<>());

        TourDetailsModel tourDetailsModel = TourMapper.fromDTO(dto);

        assertEquals(dto.getId().toString(), tourDetailsModel.getId());
        assertEquals(dto.getName(), tourDetailsModel.getName());
        assertEquals(dto.getDescription(), tourDetailsModel.getDescription());
        assertEquals(dto.getStart(), tourDetailsModel.getStart());
        assertEquals(dto.getDestination(), tourDetailsModel.getDestination());
        assertEquals(dto.getTransportType().getName(), tourDetailsModel.getTransportTypeString());
        assertEquals(dto.getEstimatedTime().toString(), tourDetailsModel.getEstimatedTime());
        assertEquals(dto.getChildFriendliness().toString(), tourDetailsModel.getChildFriendliness());
        assertEquals(dto.getPopularity().toString(), tourDetailsModel.getPopularity());
    }

}