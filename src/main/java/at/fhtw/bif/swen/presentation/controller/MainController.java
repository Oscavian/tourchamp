package at.fhtw.bif.swen.presentation.controller;

import at.fhtw.bif.swen.presentation.model.TourDetailsModel;
import at.fhtw.bif.swen.presentation.model.TourListModel;
import at.fhtw.bif.swen.presentation.model.TourListItemModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    private final TourListModel tourListModel;

    @FXML
    public TourListController tourListController;

    @FXML
    public TourDetailsController tourDetailsController;

    public MainController(TourListModel tourListModel) {
        this.tourListModel = tourListModel;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        this.tourListModel.reloadTourList();

        // set consumer for adding new tours
        this.tourDetailsController.tourDetailsGeneralController.setSaveListener(
                p -> {
                    this.tourListModel.addTour(TourDetailsModel.From(p));
                    //this.tourListController.tourListView.getSelectionModel().clearSelection(); // buggy, produces sussy nullpointer exceptions
                }
        );

        this.tourDetailsController.tourDetailsGeneralController.setEditListener(
                p -> this.tourListModel.addTour(TourDetailsModel.From(p))
        );

        this.tourDetailsController.tourDetailsGeneralController.setRemoveListener(
                p -> this.tourListModel.removeTour(TourListItemModel.From(p))
        );

        //what should happen when clicking on "add"
        this.tourListController.initFormForNewTourListener(
                // details
                () -> {
                    this.tourDetailsController.tourDetailsGeneralController.initNewTour();
                    //this.tourListController.tourListView.getSelectionModel().clearSelection(); // also produces an exception when an item is currently selected, prob due to buggy change listener on listcell
                });

        //what should happen when clicking on "cancel"
        this.tourDetailsController.tourDetailsGeneralController.setCancelListener(
                () -> {
                    this.tourDetailsController.tourDetailsGeneralController.tourForm.setDisable(true);
                    this.tourListController.tourListView.getSelectionModel().clearSelection();
                }
        );

        this.tourListController.setSelectedListener(
                // selected list it
                p -> {
                    this.tourDetailsController.tourDetailsGeneralController.setTourDetailsModel(this.tourListModel.loadDetailModel(p));
                    //display edit/delete buttons
                    this.tourDetailsController.tourDetailsGeneralController.editTourButtons.setVisible(true);
                    this.tourDetailsController.tourDetailsGeneralController.newTourButtons.setVisible(false);

                    System.out.println("in consumer:" + p.getName());
                }

        );
    }
}
