package at.fhtw.bif.swen.presentation.model;

import at.fhtw.bif.swen.presentation.service.TourLogService;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.NamedEntityGraph;

@AllArgsConstructor
public class TourLogModel {
    private final StringProperty name = new SimpleStringProperty();
    private final StringProperty date = new SimpleStringProperty();
    private final StringProperty time = new SimpleStringProperty();
    private final StringProperty comment = new SimpleStringProperty();
    private final StringProperty difficulty = new SimpleStringProperty();
    private final StringProperty rating = new SimpleStringProperty();

    public TourLogModel(String name, String date, String time, String comment, String difficulty, String rating) {
        setName(name);
        setDate(date);
        setTime(time);
        setComment(comment);
        setDifficulty(difficulty);
        setRating(rating);
    }



    public static TourLogModel newInstance(TourLogModel tourLogModel) {
        var model = new TourLogModel();
        model.setName(tourLogModel.getName());
        model.setDate(tourLogModel.getDate());
        model.setTime(tourLogModel.getTime());
        model.setComment(tourLogModel.getComment());
        model.setDifficulty(tourLogModel.getDifficulty());
        model.setRating(tourLogModel.getRating());
        return model;
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getDate() {
        return date.get();
    }

    public StringProperty dateProperty() {
        return date;
    }

    public void setDate(String date) {
        this.date.set(date);
    }

    public String getTime() {
        return time.get();
    }

    public StringProperty timeProperty() {
        return time;
    }

    public void setTime(String time) {
        this.time.set(time);
    }

    public String getComment() {
        return comment.get();
    }

    public StringProperty commentProperty() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment.set(comment);
    }

    public String getDifficulty() {
        return difficulty.get();
    }

    public StringProperty difficultyProperty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty.set(difficulty);
    }

    public String getRating() {
        return rating.get();
    }

    public StringProperty ratingProperty() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating.set(rating);
    }
}
