package at.fhtw.bif.swen.presentation.controller;

import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class TourDetailsRouteController implements Initializable {
    public ImageView routeMap;

    private Image placeholderImage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        placeholderImage = new Image("https://images.unsplash.com/photo-1475359524104-d101d02a042b?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1294&q=80");
        routeMap.setImage(placeholderImage);
        routeMap.setSmooth(true);
        routeMap.setCache(true);
    }

}
