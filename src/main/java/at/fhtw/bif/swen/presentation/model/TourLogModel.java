package at.fhtw.bif.swen.presentation.model;

import javafx.beans.property.*;
import lombok.AllArgsConstructor;

import javax.persistence.criteria.CriteriaBuilder;
import java.time.LocalDate;

@AllArgsConstructor
public class TourLogModel {
    private final ObjectProperty<LocalDate> date = new SimpleObjectProperty<>();
    private final StringProperty time = new SimpleStringProperty();
    private final StringProperty comment = new SimpleStringProperty();
    private final IntegerProperty difficulty = new SimpleIntegerProperty();
    private final IntegerProperty rating = new SimpleIntegerProperty();

    public TourLogModel(String date, String time, String comment, Integer difficulty, Integer rating) {
        setDate(LocalDate.parse(date));
        setTime(time);
        setComment(comment);
        setDifficulty(difficulty);
        setRating(rating);
    }

    public TourLogModel(String comment) {
        setComment(comment);
    }

    public static TourLogModel newInstance(TourLogModel tourLogModel) {
        var model = new TourLogModel();
        model.setDate(tourLogModel.getDate());
        model.setTime(tourLogModel.getTime());
        model.setComment(tourLogModel.getComment());
        model.setDifficulty(tourLogModel.getDifficulty());
        model.setRating(tourLogModel.getRating());
        return model;
    }

    public void clear() {
        setDate(LocalDate.now());
        setComment("");
        setDifficulty(0);
        setRating(0);
        setTime("");
    }

    public LocalDate getDate() {
        return date.get();
    }
    public ObjectProperty<LocalDate> dateProperty() {
        return date;
    }
    public void setDate(LocalDate date) {
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
    public Integer getDifficulty() {
        return difficulty.get();
    }
    public IntegerProperty difficultyProperty() {
        return difficulty;
    }
    public void setDifficulty(Integer difficulty) {
        this.difficulty.set(difficulty);
    }
    public Integer getRating() {
        return rating.get();
    }
    public IntegerProperty ratingProperty() {
        return rating;
    }
    public void setRating(Integer rating) {
        this.rating.set(rating);
    }
}
