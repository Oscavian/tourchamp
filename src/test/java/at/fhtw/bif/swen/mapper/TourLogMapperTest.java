package at.fhtw.bif.swen.mapper;

import at.fhtw.bif.swen.dto.TourLogDTO;
import at.fhtw.bif.swen.persistence.entities.TourLogEntity;
import at.fhtw.bif.swen.presentation.model.TourLogModel;
import at.fhtw.bif.swen.util.TransportType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TourLogMapperTest {
    @Test
    public void testToEntity() {
        // Arrange
        List<TourLogDTO> dtos = new ArrayList<>();
        TourLogDTO dto1 = new TourLogDTO();
        dto1.setId(1);
        dto1.setTourId(100);
        dto1.setTimestamp(LocalDate.now());
        dto1.setComment("Test comment");
        dto1.setDifficulty(3);
        dto1.setTotalTime(120);
        dto1.setRating(4);
        dtos.add(dto1);

        // Act
        List<TourLogEntity> entities = TourLogMapper.toEntity(dtos);

        // Assert
        assertEquals(1, entities.size());
        TourLogEntity entity = entities.get(0);
        assertEquals(1, entity.getId());
        assertEquals(100, entity.getTourId());
        assertEquals(LocalDate.now(), entity.getTimestamp());
        assertEquals("Test comment", entity.getComment());
        assertEquals(3, entity.getDifficulty());
        assertEquals(120, entity.getTotalTime());
        assertEquals(4, entity.getRating());
    }

    @Test
    public void testFromEntity() {
        // Arrange
        List<TourLogEntity> entities = new ArrayList<>();
        TourLogEntity entity1 = new TourLogEntity();
        entity1.setId(1);
        entity1.setTourId(100);
        entity1.setTimestamp(LocalDate.now());
        entity1.setComment("Test comment");
        entity1.setDifficulty(3);
        entity1.setTotalTime(120);
        entity1.setRating(4);
        entities.add(entity1);

        // Act
        List<TourLogDTO> dtos = TourLogMapper.fromEntity(entities);

        // Assert
        assertEquals(1, dtos.size());
        TourLogDTO dto = dtos.get(0);
        assertEquals(1, dto.getId());
        assertEquals(100, dto.getTourId());
        assertEquals(LocalDate.now(), dto.getTimestamp());
        assertEquals("Test comment", dto.getComment());
        assertEquals(3, dto.getDifficulty());
        assertEquals(120, dto.getTotalTime());
        assertEquals(4, dto.getRating());
    }

    @Test
    public void testFromDTO() {
        // Arrange
        List<TourLogDTO> dtos = new ArrayList<>();
        TourLogDTO dto1 = new TourLogDTO();
        dto1.setTimestamp(LocalDate.now());
        dto1.setTotalTime(120);
        dto1.setComment("Test comment");
        dto1.setRating(4);
        dto1.setDifficulty(3);
        dtos.add(dto1);

        // Act
        List<TourLogModel> models = TourLogMapper.fromDTO(dtos);

        // Assert
        assertEquals(1, models.size());
        TourLogModel model = models.get(0);
        assertEquals(LocalDate.now(), model.getDate());
        assertEquals("120", model.getTime());
        assertEquals("Test comment", model.getComment());
        assertEquals(4, model.getRating());
        assertEquals(3, model.getDifficulty());
    }

    @Test
    public void testToDTO() {
        // Arrange
        List<TourLogModel> models = new ArrayList<>();
        TourLogModel model1 = new TourLogModel();
        model1.setDate(LocalDate.now());
        model1.setTime("120");
        model1.setComment("Test comment");
        model1.setRating(4);
        model1.setDifficulty(3);
        models.add(model1);

        // Act
        List<TourLogDTO> dtos = TourLogMapper.toDTO(models);

        // Assert
        assertEquals(1, dtos.size());
        TourLogDTO dto = dtos.get(0);
        assertEquals(LocalDate.now(), dto.getTimestamp());
        assertEquals(120, dto.getTotalTime());
        assertEquals("Test comment", dto.getComment());
        assertEquals(4, dto.getRating());
        assertEquals(3, dto.getDifficulty());
    }
}