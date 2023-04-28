package at.fhtw.bif.swen.presentation.controller;

import at.fhtw.bif.swen.presentation.model.TourDetailsGeneralModel;
import at.fhtw.bif.swen.presentation.model.TourListModel;
import at.fhtw.bif.swen.presentation.model.TourModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    private TourListModel tourListModel;

    @FXML
    public TourListController tourListController;

    @FXML
    public TourDetailsController tourDetailsController;

    public MainController(TourListModel tourListModel) {
        this.tourListModel = tourListModel;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // set consumer for adding new tours
        this.tourDetailsController.tourDetailsGeneralController.setSaveListener(
                p -> {
                    this.tourListModel.addTour(TourModel.From(p));
                }
        );

        this.tourDetailsController.tourDetailsGeneralController.setEditListener(
                p -> this.tourListModel.addTour(TourModel.From(p))
        );

        this.tourDetailsController.tourDetailsGeneralController.setRemoveListener(
                p -> this.tourListModel.removeTour(TourModel.From(p))
        );

        this.tourListController.initFormForNewTourListener( // list
                // details
                () -> {
                    this.tourDetailsController.tourDetailsGeneralController.initNewTour();
                });

        this.tourDetailsController.tourDetailsGeneralController.setCancelListener(
                () -> this.tourDetailsController.tourDetailsGeneralController.tourForm.setDisable(true)
        );

        this.tourListController.setSelectedListener(
                // selected list it
                p -> {
                    this.tourDetailsController.tourDetailsGeneralController.setTourDetailsGeneralModel(TourDetailsGeneralModel.From(p));

                    //display edit/delete buttons
                    this.tourDetailsController.tourDetailsGeneralController.editTourButtons.setVisible(true);

                    System.out.println("in consumer:" + p.getName());
                }

        );
    }
}
