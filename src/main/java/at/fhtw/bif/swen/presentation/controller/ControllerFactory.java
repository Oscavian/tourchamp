package at.fhtw.bif.swen.presentation.controller;

import at.fhtw.bif.swen.presentation.model.*;
import at.fhtw.bif.swen.presentation.service.TourService;

public class ControllerFactory {

    //define models
    private final SearchbarModel searchbarModel;
    private final TourListModel tourListModel;
    private final TourDetailsModel tourDetailsModel;
    private final TourListItemModel tourListItemModel;
    private EnterTourDetailsModel enterTourDetailsModel;
    private final TourLogModel tourLogModel;
    private final TourLogListModel tourLogListModel;

    //services
    private final TourService tourService;

    public ControllerFactory(TourService tourService) {
        //instantiate business layer
        this.tourService = tourService;

        // instantiate models
        this.searchbarModel = new SearchbarModel();
        this.tourListModel = new TourListModel(tourService);
        this.tourDetailsModel = new TourDetailsModel();
        this.tourListItemModel = new TourListItemModel();
        this.enterTourDetailsModel = new EnterTourDetailsModel();
        this.tourLogModel = new TourLogModel();
        this.tourLogListModel = new TourLogListModel(tourService);
    }

    public Object create(Class controllerClass) throws Exception {
        if (controllerClass == TourDetailsGeneralController.class) {
            return new TourDetailsGeneralController(this.tourDetailsModel, this.enterTourDetailsModel);
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
            return new TourDetailsLogsController(tourLogListModel, tourLogModel);
        } else {
            throw new Exception("Controller not supported " + controllerClass.getName());
        }
    }

}
