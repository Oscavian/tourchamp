package at.fhtw.bif.swen.presentation.model;

import at.fhtw.bif.swen.presentation.service.MapQuestAPIService.MapQuestAPIService;
import at.fhtw.bif.swen.presentation.service.MapQuestAPIService.TourMapData;
import at.fhtw.bif.swen.presentation.service.TourService;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.URISyntaxException;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

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
    @Getter
    @Setter
    private String mapURL;
    @Getter
    @Setter
    private CompletableFuture<TourMapData> apiData;
    private final ObservableList<TourLogModel> tourLogs = FXCollections.observableArrayList();

    private TourService tourService;

    private final Logger logger = LogManager.getLogger(getClass().getName());
    public TourDetailsModel(TourService tourService){
        this.tourService = tourService;
    }

    public TourDetailsModel(){}

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

    //log methods
    public void addNewLog(TourLogModel tourLogModel){
        if (getId().isEmpty()) {
            logger.warn("Id of TourDetailsModel " + getName() + " is empty!");
            return;
        }
        var model = TourLogModel.newInstance(tourLogModel);
        this.tourLogs.add(model);
        tourService.updateTour(this);
    }

    public void addAllLogs(List<TourLogModel> logs) {
        if (getId().isEmpty()) {
            logger.warn("Id of TourDetailsModel " + getName() + " is empty!");
            return;
        }
        tourLogs.clear();
        tourLogs.addAll(logs);
    }

    public void requestAPIData() {
        try {
            CompletableFuture<TourMapData> apiData = MapQuestAPIService.getTourData(
                    this.getStart(), this.getDestination());
            this.setApiData(apiData);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteLog(TourLogModel tourLogModel) {
        if (tourLogModel == null) {
            logger.warn("Selected log entry is null!");
            return;
        }
        this.tourLogs.remove(tourLogModel);
        this.tourService.updateTour(this);
    }

    public void clearLogs() {
        logger.debug("Cleared all logs for Tour {}", getId());
        this.tourLogs.clear();
        this.tourService.updateTour(this);
    }


    /**
     * empty all properties of this model
     */
    public void resetGeneralValues() {
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

    public ObservableList<TourLogModel> getTourLogs() {
        return tourLogs;
    }
}
