module at.fhtw.bif.swen {
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;


    opens at.fhtw.bif.swen to javafx.fxml;
    exports at.fhtw.bif.swen;
    exports at.fhtw.bif.swen.controller;
    opens at.fhtw.bif.swen.controller to javafx.fxml;
    exports at.fhtw.bif.swen.model;
    opens at.fhtw.bif.swen.model to javafx.fxml;
}