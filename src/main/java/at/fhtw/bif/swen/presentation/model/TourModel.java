package at.fhtw.bif.swen.presentation.model;

import javafx.beans.property.*;

public class TourModel {
    private final StringProperty name = new SimpleStringProperty();
    private final StringProperty description = new SimpleStringProperty();
    private final StringProperty start = new SimpleStringProperty();
    private final StringProperty destination = new SimpleStringProperty();
    private final StringProperty transportType = new SimpleStringProperty();
    private final StringProperty distance = new SimpleStringProperty();
    private final StringProperty duration = new SimpleStringProperty();
    private final StringProperty childFriendliness = new SimpleStringProperty();
    private final StringProperty popularity = new SimpleStringProperty();

    public static TourModel From(TourDetailsGeneralModel source) {
        var newInstance = new TourModel();
        newInstance.name.set(source.getName());
        newInstance.description.set(source.getDescription());
        newInstance.start.set(source.getStart());
        newInstance.destination.set(source.getDestination());
        newInstance.distance.set(source.getTourDistance());
        newInstance.duration.set(source.getDuration());
        newInstance.childFriendliness.set(source.getChildFriendliness());
        newInstance.popularity.set(source.getPopularity());
        newInstance.transportType.set(source.getTransportType());
        return newInstance;
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


    public String getDescription() {
        return description.get();
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public String getStart() {
        return start.get();
    }

    public StringProperty startProperty() {
        return start;
    }

    public void setStart(String start) {
        this.start.set(start);
    }

    public String getDestination() {
        return destination.get();
    }

    public StringProperty destinationProperty() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination.set(destination);
    }

    public String getTransportType() {
        return transportType.get();
    }

    public StringProperty transportTypeProperty() {
        return transportType;
    }

    public void setTransportType(String transportType) {
        this.transportType.set(transportType);
    }

    public String getDistance() {
        return distance.get();
    }

    public StringProperty distanceProperty() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance.set(distance);
    }

    public String getChildFriendliness() {
        return childFriendliness.get();
    }

    public StringProperty childFriendlinessProperty() {
        return childFriendliness;
    }

    public void setChildFriendliness(String childFriendliness) {
        this.childFriendliness.set(childFriendliness);
    }

    public String getPopularity() {
        return popularity.get();
    }

    public StringProperty popularityProperty() {
        return popularity;
    }

    public void setPopularity(String popularity) {
        this.popularity.set(popularity);
    }

    public String getDuration() {
        return duration.get();
    }

    public StringProperty durationProperty() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration.set(duration);
    }
}
