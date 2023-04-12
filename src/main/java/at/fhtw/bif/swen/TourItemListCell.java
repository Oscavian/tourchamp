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
        if (empty || tour == null) {
            setText(null);
            setGraphic(null);
            return;
        }

        super.updateItem(tour, empty);



        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("tourlistitem-view.fxml"));
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        var controller = (TourListItemController)fxmlLoader.getController();
        controller.setTour(tour); //todo change to tourdetaildata
        System.out.println(tour.getName());
        controller.addListenerForDeleteTour(this.onDeleteTourCallBack);
        setGraphic(controller.getTourItemBox());
    }

}
