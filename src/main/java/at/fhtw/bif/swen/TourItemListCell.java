package at.fhtw.bif.swen;

import at.fhtw.bif.swen.controller.TourListItemController;
import at.fhtw.bif.swen.model.TourModel;
import javafx.fxml.FXMLLoader;

import java.io.IOException;
import java.util.function.Consumer;

public class TourItemListCell extends javafx.scene.control.ListCell<TourModel> {

    private Consumer<TourModel> onDeleteTourCallBack;

    public TourItemListCell(Consumer<TourModel> callback) {
        this.onDeleteTourCallBack = callback;
    }

    @Override
    public void updateItem(TourModel tour, boolean empty) {
        System.out.println("Call to updateItem");
        super.updateItem(tour, empty);

        if (empty || tour == null) {
            setText(null);
            setGraphic(null);
            return;
        }

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("tourlistitem-view.fxml"));
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        var controller = (TourListItemController)fxmlLoader.getController();
        controller.setTour(new TourModel()); //todo change to tourdetaildata
        controller.addListenerForDeleteTour(this.onDeleteTourCallBack);
        setGraphic(controller.getTourItemBox());
    }

}
