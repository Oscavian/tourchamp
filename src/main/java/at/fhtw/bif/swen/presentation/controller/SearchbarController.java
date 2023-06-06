package at.fhtw.bif.swen.presentation.controller;

import at.fhtw.bif.swen.presentation.model.SearchbarModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class SearchbarController implements Initializable {
    public final SearchbarModel searchbarModel;
    @FXML
    public TextField searchValue;

    private Runnable searchAction;

    public SearchbarController(SearchbarModel searchbarModel) {
        this.searchbarModel = searchbarModel;
    }

    public void search(ActionEvent actionEvent) {
        System.out.println(this.searchbarModel.getSearchValue());
        this.searchAction.run();
    }

    public void setSearchAction(Runnable runnable) {
        this.searchAction = runnable;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.searchValue.textProperty().bindBidirectional(this.searchbarModel.searchValueProperty());
    }
}
