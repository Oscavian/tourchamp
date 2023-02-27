package at.fhtw.bif.swen;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    public PasswordField passwordField ;

    @FXML
    public Button submitButton;

    @FXML
    public TextField usernameField;

    @FXML
    public Label formError;

    @FXML
    protected void onSubmit() {
        formError.setVisible(false);

        String username = usernameField.getText();
        String password = passwordField.getText();

        if (username.length() < 4) {
            formError.setVisible(true);
            formError.setText("Username invalid.");
        }
        System.out.println(username + ":" + password);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
