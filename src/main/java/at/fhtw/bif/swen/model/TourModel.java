package at.fhtw.bif.swen.model;

import javafx.beans.property.*;

public class TourModel {
    private StringProperty name = new SimpleStringProperty();
   // private ObjectProperty details = new SimpleObjectProperty();
    // private ListProperty logs = new SimpleListProperty();

    //test constructor for testing
    public TourModel() {
        this.setName("Test-Tour");
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
