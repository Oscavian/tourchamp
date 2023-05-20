module at.fhtw.bif.swen {
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;
    requires java.persistence;
    requires org.hibernate.orm.core;
    requires java.sql;
    requires java.net.http;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires kernel;
    requires layout;
    requires io;


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

    exports at.fhtw.bif.swen.persistence.repositories;
    opens at.fhtw.bif.swen.persistence.repositories to javafx.fxml;

    exports at.fhtw.bif.swen.persistence.entities;
    opens at.fhtw.bif.swen.persistence.entities to org.hibernate.orm.core;
}