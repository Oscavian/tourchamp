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
    //services
    private final TourService tourService;

    public ControllerFactory(TourService tourService) {
        //instantiate business layer
        this.tourService = tourService;

        // instantiate models
        this.searchbarModel = new SearchbarModel();
        this.tourListModel = new TourListModel(tourService);
        this.tourDetailsModel = new TourDetailsModel(tourService);
        this.tourListItemModel = new TourListItemModel();
        this.enterTourDetailsModel = new EnterTourDetailsModel();
        this.tourLogModel = new TourLogModel();
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
            return new TourDetailsController();
        } else if (controllerClass == MainController.class) {
            return new MainController(this.tourListModel);
        } else if (controllerClass == TourDetailsRouteController.class) {
            return new TourDetailsRouteController();
        } else if (controllerClass == TourDetailsLogsController.class) {
            return new TourDetailsLogsController(this.tourDetailsModel, tourLogModel);
        } else {
            throw new IllegalArgumentException("Controller not supported " + controllerClass.getName());
        }
    }

}
