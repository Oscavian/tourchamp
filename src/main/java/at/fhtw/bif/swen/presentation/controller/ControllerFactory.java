package at.fhtw.bif.swen.presentation.controller;

import at.fhtw.bif.swen.persistence.repositories.TourRepository;
import at.fhtw.bif.swen.presentation.model.SearchbarModel;
import at.fhtw.bif.swen.presentation.model.TourDetailsGeneralModel;
import at.fhtw.bif.swen.presentation.model.TourListModel;
import at.fhtw.bif.swen.presentation.model.TourModel;

public class ControllerFactory {

    //define models
    private final SearchbarModel searchbarModel;
    private final TourListModel tourListModel;
    private final TourDetailsGeneralModel tourDetailsGeneralModel;
    private final TourModel tourModel;


    public ControllerFactory() {
        // instantiate models
        this.searchbarModel = new SearchbarModel();
        this.tourListModel = new TourListModel();
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
        } else {
            throw new Exception("Controller not supported " + controllerClass.getName());
        }
    }

}
