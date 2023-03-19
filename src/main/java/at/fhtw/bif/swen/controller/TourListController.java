package at.fhtw.bif.swen.controller;

import at.fhtw.bif.swen.TourItemListCell;
import at.fhtw.bif.swen.model.TourListModel;
import at.fhtw.bif.swen.model.TourModel;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;

public class TourListController implements Initializable {

    private TourListModel tourListModel;

    private Consumer<TourModel> newTourListener;
    private Consumer<TourItemListCell> selectedTourListItem;

    @FXML
    public ListView<TourModel> tourList = new ListView<>();

    @FXML
    private Label delete;
    @FXML
    private Label edit;

    public void addTour(MouseEvent mouseEvent) {
        this.newTourListener.accept(new TourModel());
    }

    public void selectedTourListItem(ActionEvent actionEvent) {

    }


    public void addListener(Consumer<TourModel> listenToNewTour) {
        this.newTourListener = listenToNewTour;
    }

    public TourListController(TourListModel tourListModel) {
        this.tourListModel = tourListModel;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.addListener(p -> this.tourListModel.addTour(new TourModel()));
        this.tourList.setItems(this.tourListModel.getTours());
        this.tourList.setCellFactory(
                tourModelListView -> new TourItemListCell(p -> this.deleteTour(p))
        );

        tourList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TourModel>() {
            @Override
            public void changed(ObservableValue<? extends TourModel> observableValue, TourModel tourModel, TourModel t1) {
                if (true) {
                    delete.disableProperty().set(false);
                    edit.disableProperty().set(false);
                } else {
                    delete.disableProperty().set(true);
                    edit.disableProperty().set(true);
                }

                System.out.println("Item selected");
            }
        });
    }

    public void deleteTour(TourModel model) {
        this.tourListModel.removeTour(model);
    }
}
