package at.fhtw.bif.swen.presentation.model;

import at.fhtw.bif.swen.util.TransportType;
import javafx.beans.InvalidationListener;
import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.SelectionModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class EnterTourDetailsModel {

    @Setter
    @Getter
    private String id; //only used for edit
    private final StringProperty name = new SimpleStringProperty();
    private final StringProperty description = new SimpleStringProperty();
    private final StringProperty start = new SimpleStringProperty();
    private final StringProperty destination = new SimpleStringProperty();

    private final ObjectProperty<TransportType> transportType = new SimpleObjectProperty<>();


    public EnterTourDetailsModel() {
    }

    public void clear() {
        setName("");
        setDescription("");
        setStart("");
        setStart("");
        setDestination("");
        setTransportType(TransportType.EMPTY);
        setId("");
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public String getDescription() {
        return description.get();
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    public String getStart() {
        return start.get();
    }

    public StringProperty startProperty() {
        return start;
    }

    public String getDestination() {
        return destination.get();
    }

    public StringProperty destinationProperty() {
        return destination;
    }

    public ObjectProperty<TransportType> transportTypeProperty() { return transportType;}

    public TransportType getTransportType() {return transportType.get();}


    public void setName(String name) {
        this.name.set(name);
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public void setStart(String start) {
        this.start.set(start);
    }

    public void setDestination(String destination) {
        this.destination.set(destination);
    }

    public void setTransportType(TransportType transportType) {this.transportType.set(transportType);}
}
