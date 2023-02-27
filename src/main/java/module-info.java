module at.fhtw.bif.swen {
    requires javafx.controls;
    requires javafx.fxml;


    opens at.fhtw.bif.swen to javafx.fxml;
    exports at.fhtw.bif.swen;
}