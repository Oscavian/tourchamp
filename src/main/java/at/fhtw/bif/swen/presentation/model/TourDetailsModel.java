package at.fhtw.bif.swen.presentation.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class TourDetailsModel {

    private final StringProperty id = new SimpleStringProperty();
    private final StringProperty name = new SimpleStringProperty();
    private final StringProperty description = new SimpleStringProperty();
    private final StringProperty start = new SimpleStringProperty();
    private final StringProperty destination = new SimpleStringProperty();
    private final StringProperty transportType = new SimpleStringProperty();
    private final StringProperty tourDistance = new SimpleStringProperty();
    private final StringProperty duration = new SimpleStringProperty();
    private final StringProperty childFriendliness = new SimpleStringProperty();
    private final StringProperty popularity = new SimpleStringProperty();
    private String mapURL;

    public TourDetailsModel(String id, String name) {
        setName(name);
        setId(id);
    }

    public static TourDetailsModel From(EnterTourDetailsModel source) {
        var newInstance = new TourDetailsModel();
        if (source.getId() != null) {
            newInstance.id.set(source.getId());
        }
        newInstance.name.set(source.getName());
        newInstance.description.set(source.getDescription());
        newInstance.start.set(source.getStart());
        newInstance.destination.set(source.getDestination());
        newInstance.transportType.set(source.getTransportType());
        return newInstance;
    }

    /**
     * empty all properties of this model
     */
    public void reset() {
        setId("");
        setName("");
        setDestination("");
        setStart("");
        setDuration("");
        setDescription("");
        setPopularity("");
        setTourDistance("");
        setChildFriendliness("");
        setTransportType("");
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

    public String getTransportType() {
        return transportType.get();
    }

    public StringProperty transportTypeProperty() {
        return transportType;
    }

    public String getTourDistance() {
        return tourDistance.get();
    }

    public StringProperty tourDistanceProperty() {
        return tourDistance;
    }

    public String getDuration() {
        return duration.get();
    }

    public StringProperty durationProperty() {
        return duration;
    }

    public String getChildFriendliness() {
        return childFriendliness.get();
    }

    public StringProperty childFriendlinessProperty() {
        return childFriendliness;
    }

    public String getPopularity() {
        return popularity.get();
    }

    public StringProperty popularityProperty() {
        return popularity;
    }

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

    public void setTourDistance(String tourDistance) {
        this.tourDistance.set(tourDistance);
    }

    public void setDuration(String duration) {
        this.duration.set(duration);
    }

    public void setChildFriendliness(String childFriendliness) {
        this.childFriendliness.set(childFriendliness);
    }

    public void setPopularity(String popularity) {
        this.popularity.set(popularity);
    }

    public void setTransportType(String transportType) {
        this.transportType.set(transportType);
    }
}
