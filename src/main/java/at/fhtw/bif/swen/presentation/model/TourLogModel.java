package at.fhtw.bif.swen.presentation.model;

import at.fhtw.bif.swen.presentation.service.TourLogService;

public class TourLogModel {
    private final TourLogService tourLogService;

    public TourLogModel(TourLogService tourLogService) {
        this.tourLogService = tourLogService;
    }
}
