package at.fhtw.bif.swen.businesslogic;

import at.fhtw.bif.swen.dto.TourDTO;
import at.fhtw.bif.swen.dto.TourLogDTO;
import at.fhtw.bif.swen.persistence.ITourDataSource;
import at.fhtw.bif.swen.persistence.TourRepository;
import at.fhtw.bif.swen.persistence.entities.TourEntity;
import at.fhtw.bif.swen.persistence.entities.TourLogEntity;
import at.fhtw.bif.swen.presentation.model.TourLogModel;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TourLogicTest {

    ITourDataSource dataSource;

    TourLogic tourLogic;

    @BeforeEach
    void setUp() {
        dataSource = mock(TourRepository.class);
        tourLogic = new TourLogic(dataSource);
    }

    @Test
    void saveTour() {
        var dto = new TourDTO();

        tourLogic.saveTour(dto);

        assertNotNull(dto.getPopularity());
        assertNotNull(dto.getChildFriendliness());
        verify(dataSource).save(any(TourEntity.class));
    }

    @Test
    void updateTour() {
        tourLogic.updateTour(new TourDTO());

        verify(dataSource).update(any(TourEntity.class));
    }

    @Test
    void deleteTour() {
        var entity = new TourEntity();
        when(dataSource.getById(1)).thenReturn(entity);

        tourLogic.deleteTour(1);

        verify(dataSource).delete(entity);
    }

    @Test
    void getAll() {
        var entities = new ArrayList<TourEntity>();
        entities.add(createTourEntityWithIdName(1, "bla"));
        entities.add(createTourEntityWithIdName(2, "bli"));
        entities.add(createTourEntityWithIdName(3, "blub"));
        when(dataSource.getAll()).thenReturn(entities);

        var list = tourLogic.getAll();

        assertEquals(3, list.size());
        assertEquals(1, list.get(0).getId());
        assertEquals(2, list.get(1).getId());
        assertEquals(3, list.get(2).getId());
    }

    @Test
    void getTourById() {
        var e = createTourEntityWithIdName(1, "foo");
        when(dataSource.getById(1)).thenReturn(e);

        var dto = tourLogic.getTourById(1);

        verify(dataSource).getById(1);
        assertEquals(1, dto.getId());
    }

    @Test
    public void testComputeEstimatedTime() {
        // Arrange
        TourDTO tourDTO = new TourDTO();
        List<TourLogDTO> logs = new ArrayList<>();

        logs.add(createTourLogWithTotalTime(5));
        logs.add(createTourLogWithTotalTime(10));
        logs.add(createTourLogWithTotalTime(15));
        tourDTO.setLogs(logs);

        // Act
        Integer estimatedTime = tourLogic.computeEstimatedTime(tourDTO);

        // Assert
        assertEquals(10, estimatedTime);
    }

    @Test
    public void testComputePopularity() {
        // Arrange
        TourDTO tourDTO = new TourDTO();
        List<TourLogDTO> logs = new ArrayList<>();
        logs.add(createTourLogWithRating(3));
        logs.add(createTourLogWithRating(4));
        logs.add(createTourLogWithRating(5));
        tourDTO.setLogs(logs);

        int totalLogRatingCount = 40;

        // Act
        Integer popularity = tourLogic.computePopularity(tourDTO, totalLogRatingCount);

        // Assert
        assertEquals(30, popularity);
    }

    @Test
    public void testGetLogRatingCount() {
        // Arrange
        TourEntity tourEntity1 = new TourEntity();
        TourLogEntity logEntity1 = new TourLogEntity();
        logEntity1.setRating(4);
        TourLogEntity logEntity2 = new TourLogEntity();
        logEntity2.setRating(5);
        tourEntity1.getTourLogs().add(logEntity1);
        tourEntity1.getTourLogs().add(logEntity2);

        TourEntity tourEntity2 = new TourEntity();
        TourLogEntity logEntity3 = new TourLogEntity();
        logEntity3.setRating(3);
        tourEntity2.getTourLogs().add(logEntity3);

        List<TourEntity> tourEntities = new ArrayList<>();
        tourEntities.add(tourEntity1);
        tourEntities.add(tourEntity2);

        when(dataSource.getAll()).thenReturn(tourEntities);

        // Act
        Integer logRatingCount = tourLogic.getLogRatingCount();

        // Assert
        assertEquals(12, logRatingCount);
    }

    @Test
    public void testComputeChildFriendliness() {
        // Arrange
        TourDTO tourDTO = new TourDTO();
        tourDTO.setPopularity(60);
        List<TourLogDTO> logs = new ArrayList<>();
        logs.add(createTourLogWithDifficulty(2));
        logs.add(createTourLogWithDifficulty(4));
        logs.add(createTourLogWithDifficulty(3));
        tourDTO.setLogs(logs);

        // Act
        Integer childFriendliness = tourLogic.computeChildFriendliness(tourDTO);

        // Assert
        assertEquals(6, childFriendliness);
    }

    private TourEntity createTourEntityWithIdName(Integer id, String name) {
        var e = new TourEntity();
        e.setId(id);
        e.setName(name);
        return e;
    }

    private TourLogDTO createTourLogWithDifficulty(Integer difficulty) {
        TourLogDTO tourLogDTO = new TourLogDTO();
        tourLogDTO.setDifficulty(difficulty);
        return tourLogDTO;
    }

    private TourLogDTO createTourLogWithRating(Integer rating) {
        TourLogDTO tourLogDTO = new TourLogDTO();
        tourLogDTO.setRating(rating);
        return tourLogDTO;
    }

    private TourLogDTO createTourLogWithTotalTime(Integer totalTime) {
        TourLogDTO tourLogDTO = new TourLogDTO();
        tourLogDTO.setTotalTime(totalTime);
        return tourLogDTO;
    }

}