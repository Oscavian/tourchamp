package at.fhtw.bif.swen.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class TourDetailsGeneralModel {
    private final StringProperty name = new SimpleStringProperty();
    private final StringProperty description = new SimpleStringProperty();
    private final StringProperty from = new SimpleStringProperty();
    private final StringProperty to = new SimpleStringProperty();
    private final StringProperty transportType = new SimpleStringProperty();
    private final StringProperty tourDistance = new SimpleStringProperty();
    private final StringProperty duration = new SimpleStringProperty();
    private final StringProperty routeInfo = new SimpleStringProperty();
    private final StringProperty childFriendliness = new SimpleStringProperty();
    private final StringProperty popularity = new SimpleStringProperty();

    public static TourDetailsGeneralModel From(TourModel source) {
        var newInstance = new TourDetailsGeneralModel();
        newInstance.name.set(source.getName());
        newInstance.description.set(source.getDescription());
        newInstance.from.set(source.getFrom());
        newInstance.to.set(source.getTo());
        newInstance.tourDistance.set(source.getTourDistance());
        newInstance.duration.set(source.getDuration());
        newInstance.routeInfo.set(source.getRouteInfo());
        newInstance.childFriendliness.set(source.getChildFriendliness());
        newInstance.popularity.set(source.getPopularity());
        return newInstance;
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

    public String getFrom() {
        return from.get();
    }

    public StringProperty fromProperty() {
        return from;
    }

    public String getTo() {
        return to.get();
    }

    public StringProperty toProperty() {
        return to;
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

    public String getRouteInfo() {
        return routeInfo.get();
    }

    public StringProperty routeInfoProperty() {
        return routeInfo;
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
}
