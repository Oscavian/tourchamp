package at.fhtw.bif.swen.presentation;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class AlertUtil {
    public static void alert(Alert.AlertType type, String header, String message){
        Platform.runLater(() -> {
            showAlert(type, header, message);
        });
    }

    public static void alert(Alert.AlertType type, String header, String message, Runnable runnable){
        Platform.runLater(() -> {
            showAlert(type, header, message);
            runnable.run();
        });
    }

    private static void showAlert(Alert.AlertType type, String header, String message) {
        Alert a = new Alert(type, message, ButtonType.OK);
        switch (type) {
            case ERROR -> {
                a.setTitle("Error!");
            }
            case INFORMATION -> {
                a.setTitle("Info!");
            }
        }
        a.setHeaderText(header);
        a.show();
    }
}