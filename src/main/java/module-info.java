module at.fhtw.bif.swen {
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;
    requires spring.data.jpa;
    requires java.persistence;


    opens at.fhtw.bif.swen to javafx.fxml;
    exports at.fhtw.bif.swen;

    exports at.fhtw.bif.swen.presentation.controller;
    opens at.fhtw.bif.swen.presentation.controller to javafx.fxml;

    exports at.fhtw.bif.swen.presentation.model;
    opens at.fhtw.bif.swen.presentation.model to javafx.fxml;

    //exports at.fhtw.bif.swen.presentation;
    //opens at.fhtw.bif.swen.presentation to javafx.fxml;

    exports at.fhtw.bif.swen.presentation.service;
    opens at.fhtw.bif.swen.presentation.service to javafx.fxml;

    exports at.fhtw.bif.swen.businesslogic;
    opens at.fhtw.bif.swen.businesslogic to javafx.fxml;

    exports at.fhtw.bif.swen.businesslogic.impl;
    opens at.fhtw.bif.swen.businesslogic.impl to javafx.fxml;

    exports at.fhtw.bif.swen.dto;
    opens at.fhtw.bif.swen.dto to javafx.fxml;

    exports at.fhtw.bif.swen.persistence;
    opens at.fhtw.bif.swen.persistence to javafx.fxml;
    exports at.fhtw.bif.swen.presentation;
    opens at.fhtw.bif.swen.presentation to javafx.fxml;

}