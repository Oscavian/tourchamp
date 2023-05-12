package at.fhtw.bif.swen.presentation.controller;

import at.fhtw.bif.swen.presentation.model.*;
import at.fhtw.bif.swen.presentation.service.IService;
import at.fhtw.bif.swen.presentation.service.TourLogService;
import at.fhtw.bif.swen.presentation.service.TourService;

public class ControllerFactory {

    //define models
    private final SearchbarModel searchbarModel;
    private final TourListModel tourListModel;
    private final TourDetailsGeneralModel tourDetailsGeneralModel;
    private final TourModel tourModel;

    //services
    private final TourService tourService;
    private final TourLogService tourLogService;


    public ControllerFactory(TourService tourService, TourLogService tourLogService) {
        //instantiate business layer
        this.tourService = tourService;
        this.tourLogService = tourLogService;

        // instantiate models
        this.searchbarModel = new SearchbarModel();
        this.tourListModel = new TourListModel(tourService);
        this.tourDetailsGeneralModel = new TourDetailsGeneralModel();
        this.tourModel = new TourModel();
    }

    public Object create(Class controllerClass) throws Exception {
        if (controllerClass == TourDetailsGeneralController.class) {
            return new TourDetailsGeneralController(this.tourDetailsGeneralModel);
        } else if (controllerClass == MenubarController.class) {
            return new MenubarController();
        } else if (controllerClass == SearchbarController.class) {
            return new SearchbarController(this.searchbarModel);
        } else if (controllerClass == TourListController.class) {
            return new TourListController(this.tourListModel);
        } else if (controllerClass == TourDetailsController.class) {
            return new TourDetailsController(tourListModel);
        } else if (controllerClass == MainController.class) {
            return new MainController(this.tourListModel);
        } else if (controllerClass == TourDetailsRouteController.class) {
            return new TourDetailsRouteController();
        } else if (controllerClass == TourDetailsLogsController.class) {
            return new TourDetailsLogsController();
        } else {
            throw new Exception("Controller not supported " + controllerClass.getName());
        }
    }

}
