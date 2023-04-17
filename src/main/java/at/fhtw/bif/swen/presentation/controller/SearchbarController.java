package at.fhtw.bif.swen.presentation.controller;

import at.fhtw.bif.swen.presentation.model.SearchbarModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class SearchbarController implements Initializable {
    private final SearchbarModel searchbarModel;
    public TextField searchValue;

    public SearchbarController(SearchbarModel searchbarModel) {
        this.searchbarModel = searchbarModel;
    }

    @FXML
    public void search(ActionEvent actionEvent) {
        System.out.println(this.searchbarModel.getSearchValue());
        //todo: search for searchValue in tours and tourLogs
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.searchValue.textProperty().bindBidirectional(this.searchbarModel.searchValueProperty());
    }
}
