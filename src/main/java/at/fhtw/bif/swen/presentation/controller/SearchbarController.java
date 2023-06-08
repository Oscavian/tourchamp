package at.fhtw.bif.swen.presentation.controller;

import at.fhtw.bif.swen.presentation.model.SearchbarModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.URL;
import java.util.ResourceBundle;

public class SearchbarController implements Initializable {
    public final SearchbarModel searchbarModel;
    @FXML
    public TextField searchValue;

    private Runnable searchAction;
    private final Logger logger = LogManager.getLogger(getClass().getName());

    public SearchbarController(SearchbarModel searchbarModel) {
        this.searchbarModel = searchbarModel;
    }

    public void search(ActionEvent actionEvent) {
        logger.debug("Searchbar: Search for '" + this.searchbarModel.getSearchValue() +"'");
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
