package at.fhtw.bif.swen.presentation.model;

import at.fhtw.bif.swen.presentation.service.TourService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TourListModelTest {


    TourService tourService;
    TourDetailsModel tourDetailsModel;
    TourListModel tourListModel;

    @BeforeEach
    void setUp() {
        tourService = mock(TourService.class);
        tourListModel = new TourListModel(tourService);
        tourDetailsModel = mock(TourDetailsModel.class);
    }

    @Test
    void addTour() {
        //act
        tourListModel.addTour(tourDetailsModel);

        //assert
        verify(tourService).saveTour(tourDetailsModel);
    }

    @Test
    void removeTour() {
        //act
        tourListModel.removeTour(tourDetailsModel);

        //assert
        verify(tourService).deleteTour(tourDetailsModel);
    }

    @Test
    void loadDetailModel() {

        var itemModel = mock(TourListItemModel.class);
        var returnedModel = new TourDetailsModel();
        returnedModel.setDescription("huhu");
        when(itemModel.getId()).thenReturn("1");
        when(itemModel.getName()).thenReturn("test");

        when(tourService.getById(any(Integer.class))).thenReturn(returnedModel);

        var model = tourListModel.loadDetailModel(itemModel);

        assertEquals(model, returnedModel);
    }

    @Test
    void updateTour() {
        tourListModel.updateTour(tourDetailsModel);

        verify(tourService).updateTour(tourDetailsModel);
    }
}