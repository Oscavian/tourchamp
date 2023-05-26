package at.fhtw.bif.swen;

import at.fhtw.bif.swen.businesslogic.ITourLogLogic;
import at.fhtw.bif.swen.businesslogic.ITourLogic;
import at.fhtw.bif.swen.businesslogic.impl.TourLogLogicImpl;
import at.fhtw.bif.swen.businesslogic.impl.TourLogicImpl;
import at.fhtw.bif.swen.persistence.ITourLogDataSource;
import at.fhtw.bif.swen.persistence.entities.TourEntity;
import at.fhtw.bif.swen.persistence.repositories.TourLogRepository;
import at.fhtw.bif.swen.persistence.repositories.TourRepository;
import at.fhtw.bif.swen.persistence.ITourDataSource;
import at.fhtw.bif.swen.presentation.controller.ControllerFactory;
import at.fhtw.bif.swen.presentation.service.TourLogService;
import at.fhtw.bif.swen.presentation.service.TourService;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;
import java.util.Objects;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        //create hiberate entity manager
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("tourPU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        //create concrete data source layer
        ITourDataSource tourDataSource = new TourRepository(entityManager);
        ITourLogDataSource tourLogDataSource = new TourLogRepository(entityManager);

        //create concrete business layer
        ITourLogic tourLogic = new TourLogicImpl(tourDataSource);
        ITourLogLogic tourLogLogic = new TourLogLogicImpl(tourLogDataSource);

        //inject services into controller factory
        ControllerFactory factory = new ControllerFactory(
                new TourService(tourLogic),
                new TourLogService(tourLogLogic)
        );

        //load gui
        FXMLLoader fxmlLoader = getFxmlLoader(factory);
        Scene scene = new Scene(fxmlLoader.load(), 1600, 900);

        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/css/Styles.css")).toExternalForm());

        stage.setTitle("TourChamp");
        stage.setScene(scene);
        stage.show();
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