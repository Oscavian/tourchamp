package at.fhtw.bif.swen.presentation.model;

import at.fhtw.bif.swen.presentation.service.MapQuestAPIService.MapQuestAPIService;
import at.fhtw.bif.swen.presentation.service.TourService;
import at.fhtw.bif.swen.util.TransportType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TourDetailsModelTest {

    TourService tourService;

    TourDetailsModel tourDetailsModel;

    @BeforeEach
    void setUp() {
        tourService = mock(TourService.class);

        tourDetailsModel = new TourDetailsModel(tourService);
        tourDetailsModel.setId("1");
    }

    @Test
    void from() {
        var entermodel = new EnterTourDetailsModel();
        entermodel.setName("test");
        entermodel.setDescription("huhu");
        entermodel.setStart("Wien");
        entermodel.setDestination("Graz");
        entermodel.setTransportType(TransportType.CAR);

        var model = TourDetailsModel.From(entermodel);

        assertEquals(model.getName(), entermodel.getName());
        assertEquals(model.getDescription(), entermodel.getDescription());
        assertEquals(model.getStart(), entermodel.getStart());
        assertEquals(model.getDestination(), entermodel.getDestination());
        assertEquals(model.getTransportTypeString(), entermodel.getTransportType().getName());
    }

    @Test
    void addNewLog() {
        var logmodel = new TourLogModel();
        tourDetailsModel.addNewLog(logmodel);

        verify(tourService).updateTour(tourDetailsModel);
    }

    @Test
    void addAllLogs() {
        var list = new ArrayList<TourLogModel>();
        list.add(new TourLogModel());
        list.add(new TourLogModel());
        list.add(new TourLogModel());

        tourDetailsModel.addAllLogs(list);

        assertEquals(3, tourDetailsModel.getTourLogs().size());
    }

    @Test
    void update() {
        tourDetailsModel.update();
        verify(tourService).updateTour(tourDetailsModel);
    }

    @Test
    void deleteLog() {
        var list = new ArrayList<TourLogModel>();
        list.add(new TourLogModel());
        list.add(new TourLogModel());
        tourDetailsModel.addAllLogs(list);

        tourDetailsModel.deleteLog(list.get(0));

        assertEquals(1, tourDetailsModel.getTourLogs().size());
        verify(tourService).updateTour(tourDetailsModel);

    }

    @Test
    void clearLogs() {
        var list = new ArrayList<TourLogModel>();
        list.add(new TourLogModel());
        list.add(new TourLogModel());
        tourDetailsModel.addAllLogs(list);

        tourDetailsModel.clearLogs();

        assertEquals(0, tourDetailsModel.getTourLogs().size());
        verify(tourService).updateTour(tourDetailsModel);
    }
}