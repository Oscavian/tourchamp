package at.fhtw.bif.swen.presentation;

import at.fhtw.bif.swen.presentation.controller.TourListItemController;
import at.fhtw.bif.swen.presentation.model.TourListItemModel;
import javafx.fxml.FXMLLoader;

import java.io.IOException;
import java.util.function.Consumer;

public class TourItemListCell extends javafx.scene.control.ListCell<TourListItemModel> {

    private Consumer<TourListItemModel> onDeleteTourCallBack;

    public TourItemListCell(Consumer<TourListItemModel> callback) {
        this.onDeleteTourCallBack = callback;
    }

    @Override
    public void updateItem(TourListItemModel tour, boolean empty) {
        if (empty || tour == null) {
            setText(null);
            setGraphic(null);
            return;
        }

        super.updateItem(tour, empty);

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/at/fhtw/bif/swen/tourlistitem-view.fxml"));
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        var controller = (TourListItemController)fxmlLoader.getController();
        controller.setTour(tour);
        controller.addListenerForDeleteTour(this.onDeleteTourCallBack);
        setGraphic(controller.getTourItemBox());
    }

}
