package at.fhtw.bif.swen.presentation.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class SearchbarModel {

    public SearchbarModel(){
        this.setSearchValue("");
    }
    private StringProperty searchValue = new SimpleStringProperty();

    public void setSearchValue(String searchValue) {
        this.searchValue.set(searchValue);
    }

    public String getSearchValue() { return searchValue.get(); }

    public StringProperty searchValueProperty() { return searchValue; }
}
