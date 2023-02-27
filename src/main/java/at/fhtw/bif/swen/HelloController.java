package at.fhtw.bif.swen;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.time.LocalDateTime;
import java.util.Date;

public class HelloController {

    @FXML
    public TextField getDate;
    @FXML
    private Label welcomeText;

    private void initialize() {
        welcomeText.setText(getDate.getText());
    }
    @FXML
    protected void onHelloButtonClick() {
        System.out.println("hieagreag");
        System.out.println("eearear");
    }

    protected void onGetDate() {

    }
}