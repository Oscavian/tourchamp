package at.fhtw.bif.swen.presentation.controller;

import at.fhtw.bif.swen.presentation.TourItemListCell;
import at.fhtw.bif.swen.presentation.model.TourListModel;
import at.fhtw.bif.swen.presentation.model.TourModel;
import at.fhtw.bif.swen.presentation.service.TourService;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;

public class TourListController implements Initializable {
    @FXML
    private Label delete;
    @FXML
    private Label edit;
    @FXML
    public ListView<TourModel> tourList = new ListView<>();
    private final TourListModel tourListModel;
    //private Consumer<TourItemListCell> initTourFormListener;
    private Runnable initTourFormListener;
    private Consumer<TourModel> selectedTourListItem;

    public TourListController(TourListModel tourListModel) {
        this.tourListModel = tourListModel;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.tourList.setItems(this.tourListModel.getTours());
        this.tourList.setCellFactory(
                tourModelListView -> new TourItemListCell(p -> this.deleteTour(p))
        );

        tourList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TourModel>() {
            @Override
            public void changed(ObservableValue<? extends TourModel> observableValue, TourModel tourModel, TourModel t1) {
                if(observableValue != null) {
                    System.out.println(observableValue.getValue());

                    selectedTourListItem.accept(observableValue.getValue());

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

    // todo: change method name
    // https://stackoverflow.com/questions/29945627/java-8-lambda-void-argument
    // set eventhandler for "runnable"
    public void initFormForNewTourListener(Runnable initTourFormListener) {
        this.initTourFormListener = initTourFormListener;
    }

    public void initFormForNewTour(MouseEvent actionEvent){
        this.initTourFormListener.run();
    }

    public void setSelectedListener(Consumer<TourModel> selectedTourListItem) {
        this.selectedTourListItem = selectedTourListItem;
    }
    public void deleteTour(TourModel model) {
        this.tourListModel.removeTour(model);
    }
}
