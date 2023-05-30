package at.fhtw.bif.swen.presentation.controller;

import at.fhtw.bif.swen.presentation.TourItemListCell;
import at.fhtw.bif.swen.presentation.model.TourListModel;
import at.fhtw.bif.swen.presentation.model.TourListItemModel;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;

public class TourListController implements Initializable {
    @FXML
    public ListView<TourListItemModel> tourListView = new ListView<>();
    private final TourListModel tourListModel;
    //private Consumer<TourItemListCell> initTourFormListener;
    private Runnable initTourFormListener;
    private Consumer<TourListItemModel> selectedTourListItem;

    public TourListController(TourListModel tourListModel) {
        this.tourListModel = tourListModel;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.tourListView.setItems(this.tourListModel.getTours());
        this.tourListView.setCellFactory(
                tourModelListView -> new TourItemListCell(this::deleteTour)
        );


        //define behaviour when an item is clicked --> fire event
        tourListView.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
                selectedTourListItem.accept(observableValue.getValue());
        });

        if (!tourListView.getItems().isEmpty()) {
            tourListView.getSelectionModel().select(0);
        }
    }

    // todo: change method name
    // https://stackoverflow.com/questions/29945627/java-8-lambda-void-argument
    // set eventhandler for "runnable"
    public void initFormForNewTourListener(Runnable initTourFormListener) {
        this.initTourFormListener = initTourFormListener;
    }

    public void initFormForNewTour(MouseEvent actionEvent){
        tourListView.getSelectionModel().clearSelection();
        this.initTourFormListener.run();
    }

    public void setSelectedListener(Consumer<TourListItemModel> selectedTourListItem) {
        this.selectedTourListItem = selectedTourListItem;
    }
    public void deleteTour(TourListItemModel model) {
        this.tourListModel.removeTour(model);
    }
}
