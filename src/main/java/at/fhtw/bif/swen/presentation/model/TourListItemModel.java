package at.fhtw.bif.swen.presentation.model;

import javafx.beans.property.*;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TourListItemModel {

    private final StringProperty id = new SimpleStringProperty();
    private final StringProperty name = new SimpleStringProperty();

    public static TourListItemModel From(TourDetailsModel source) {
        var newInstance = new TourListItemModel();
        newInstance.id.set(source.getId());
        newInstance.name.set(source.getName());
        return newInstance;
    }


    public String getId() {
        return id.get();
    }
    public StringProperty idProperty() {
        return id;
    }
    public void setId(String id) {
        this.id.set(id);
    }
    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }
    public StringProperty nameProperty() {
        return name;
    }
}
