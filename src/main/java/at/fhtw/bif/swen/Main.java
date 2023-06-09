package at.fhtw.bif.swen;

import at.fhtw.bif.swen.businesslogic.ITourLogic;
import at.fhtw.bif.swen.businesslogic.TourLogic;
import at.fhtw.bif.swen.persistence.TourRepository;
import at.fhtw.bif.swen.persistence.ITourDataSource;
import at.fhtw.bif.swen.presentation.controller.ControllerFactory;
import at.fhtw.bif.swen.presentation.service.TourService;
import javafx.application.Application;
import javafx.application.HostServices;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;
import java.util.concurrent.Callable;
import java.util.function.Consumer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main extends Application {

    private final Logger logger = LogManager.getLogger(getClass().getName());
    @Override
    public void start(Stage stage) throws IOException {

        //load configuration
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("tourchamp.properties");

        final Properties properties = new Properties();
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            logger.fatal("Failed to read tourchamp.properties file.");
            throw new RuntimeException(e);
        }

        System.setProperty("MAP_API_KEY", properties.getProperty("MAP_API_KEY"));
        properties.remove("MAP_API_KEY");
        System.setProperty("log4j.configurationFile", "./log4j2.xml");

        //create hiberate entity manager
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("tourPU", properties);
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        //create concrete data source layer
        ITourDataSource tourDataSource = new TourRepository(entityManager);

        //create concrete business layer
        ITourLogic tourLogic = new TourLogic(tourDataSource);

        //inject services into controller factory
        ControllerFactory factory = new ControllerFactory(
                new TourService(tourLogic)
        );

        //load gui
        FXMLLoader fxmlLoader = getFxmlLoader(factory);
        Scene scene = new Scene(fxmlLoader.load(), 1600, 900);

        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/css/Styles.css")).toExternalForm());
        //scene.getStylesheets().add("./css/darkmode.css");
        stage.setTitle("TourChamp");
        stage.setMinHeight(800);
        stage.setMinWidth(1000);
        stage.setScene(scene);
        stage.show();

        logger.info("Application successfully started!");

    }

    private FXMLLoader getFxmlLoader(ControllerFactory factory) {
        FXMLLoader fxmlLoader = new FXMLLoader(
                Main.class.getResource("main-view.fxml"),
                null,
                new JavaFXBuilderFactory(),
                controller -> {
                    try {
                        return factory.create(controller, this.callBrowser());
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

    private Consumer<String> callBrowser() {
        return s -> this.getHs().showDocument(s);
    }
    private HostServices getHs(){
        return this.getHostServices();
    }
}