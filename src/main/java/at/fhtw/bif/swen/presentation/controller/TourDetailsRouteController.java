package at.fhtw.bif.swen.presentation.controller;

import at.fhtw.bif.swen.presentation.service.MapQuestAPIService.MapQuestAPIService;
import at.fhtw.bif.swen.presentation.service.MapQuestAPIService.TourMapData;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class TourDetailsRouteController implements Initializable {
    public ImageView routeMap;

    private CompletableFuture<TourMapData> apiData;
    private Image spinner;
    private final Logger logger = LogManager.getLogger(getClass().getName());

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            spinner = new Image(new FileInputStream("./src/main/resources/img/spinner.gif"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        routeMap.setImage(null);
        routeMap.setSmooth(true);
        routeMap.setCache(true);
    }


    public void getAPIData(CompletableFuture<TourMapData> apiData) {
        logger.info("Request data from API.");
        this.routeMap.setImage(spinner);
        this.apiData = apiData;
        this.setImage();
    }
    private void setImage() {
        this.apiData.thenAccept( a -> {
            if (a == null) {
                logger.error("Error loading static map image");
                try {
                    routeMap.setImage(new Image(
                            new FileInputStream("./src/main/resources/img/error.png")));
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
                return;
            }
            logger.debug("Finished loading data from API");
            routeMap.setImage(new Image(MapQuestAPIService.buildStaticMapRequest(a)));
        });

    }
}
