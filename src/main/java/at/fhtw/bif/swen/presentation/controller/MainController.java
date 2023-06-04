package at.fhtw.bif.swen.presentation.controller;

import at.fhtw.bif.swen.presentation.model.TourDetailsModel;
import at.fhtw.bif.swen.presentation.model.TourListModel;
import at.fhtw.bif.swen.presentation.model.TourListItemModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    private final TourListModel tourListModel;

    @FXML
    public TourListController tourListController;

    @FXML
    public TourDetailsController tourDetailsController;
    @FXML MenubarController menubarController;

    private final Logger logger = LogManager.getLogger(getClass().getName());
    public MainController(TourListModel tourListModel) {
        this.tourListModel = tourListModel;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        this.tourListModel.reloadTourList();
        logger.debug("Tour List reloaded.");

        // set consumer for adding new tours
        this.tourDetailsController.tourDetailsGeneralController.setSaveListener(
                p -> {
                    logger.debug("Save tour event fired.");
                    this.tourListModel.addTour(TourDetailsModel.From(p));
                    this.tourListController.tourListView.getSelectionModel().clearSelection();
                    this.tourListModel.reloadTourList();
                }
        );

        this.tourDetailsController.tourDetailsGeneralController.setEditListener(
                p -> {
                    logger.debug("Edit tour event fired");
                    this.tourListModel.updateTour(TourDetailsModel.From(p));
                    this.tourListController.tourListView.getSelectionModel().clearSelection();
                }
        );

        this.tourDetailsController.tourDetailsGeneralController.setRemoveListener(
                p -> this.tourListModel.removeTour(TourListItemModel.From(p))
        );

        //what should happen when clicking on "add"
        this.tourListController.initFormForNewTourListener(
                // details
                () -> {
                    logger.debug("Init details view for new tour - event fired.");
                    this.tourDetailsController.tourDetailsGeneralController.initNewTour();
                    this.tourDetailsController.detailsTabPane.getSelectionModel().select(0);
                    this.tourDetailsController.tourDetailsLogsController.tourDetailsModel.getTourLogs().clear();
                }
        );

        this.tourListController.setSelectedListener(
                // selected list it
                p -> {
                    if (p != null) {
                        //set Tourdetails
                        var m = this.tourListModel.loadDetailModel(p);
                        m.requestAPIData();
                        this.tourDetailsController.tourDetailsRouteController.getAPIData(m.getApiData());
                        this.tourDetailsController.tourDetailsGeneralController.setTourDetailsModel(m);
                        this.tourDetailsController.tourDetailsLogsController.setTourDetailsModel(m);
                        logger.debug("Selected: " + p.getName() + p.getId());
                    }
                    //display edit/delete buttons
                    this.tourDetailsController.tourDetailsGeneralController.tourForm.setDisable(true);
                    this.tourDetailsController.tourDetailsGeneralController.editTourButtons.setVisible(true);
                    this.tourDetailsController.tourDetailsGeneralController.newTourButtons.setVisible(false);
                    this.tourDetailsController.tourDetailsGeneralController.saveEditButtons.setVisible(false);
                }
        );

        this.menubarController.setReloadListener(this.tourListModel::reloadTourList);
    }
}
