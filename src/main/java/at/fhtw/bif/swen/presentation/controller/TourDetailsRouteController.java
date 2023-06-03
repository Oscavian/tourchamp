package at.fhtw.bif.swen.presentation.controller;

import at.fhtw.bif.swen.presentation.service.MapQuestAPIService.MapQuestAPIService;
import at.fhtw.bif.swen.presentation.service.MapQuestAPIService.TourMapData;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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
        this.routeMap.setImage(spinner);
        this.apiData = apiData;
        this.setImage();
    }
    private void setImage() {
        this.apiData.thenAccept( a -> {
            routeMap.setImage(new Image(MapQuestAPIService.buildStaticMapRequest(a)));
        });

    }
}
