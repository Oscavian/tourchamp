package at.fhtw.bif.swen;

import at.fhtw.bif.swen.controller.ControllerFactory;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        ControllerFactory factory = new ControllerFactory();
        FXMLLoader fxmlLoader = getFxmlLoader(factory);
        Scene scene = new Scene(fxmlLoader.load(), 1600, 900);
        stage.setTitle("TourChamp");
        stage.setScene(scene);
        stage.show();

        //FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("main-view.fxml"));
        //Scene scene = new Scene(fxmlLoader.load(), 1600, 900);
        //scene.getStylesheets().add(String.valueOf(Main.class.getResource("Styles.css")));
        //stage.setTitle("Hello!");
        //stage.setScene(scene);
        //stage.show();
    }

    private FXMLLoader getFxmlLoader(ControllerFactory factory) {
        FXMLLoader fxmlLoader = new FXMLLoader(
                Main.class.getResource("main-view.fxml"),
                null,
                new JavaFXBuilderFactory(),
                controller -> {
                    try {
                        return factory.create(controller);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return null;
                });
        return fxmlLoader;
    }

    public static void main(String[] args) {
        launch();
    }
}