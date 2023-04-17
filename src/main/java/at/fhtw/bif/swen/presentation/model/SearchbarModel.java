package at.fhtw.bif.swen.presentation.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class SearchbarModel {
    private StringProperty searchValue = new SimpleStringProperty();

    public String getSearchValue() { return searchValue.get(); }

    public StringProperty searchValueProperty() { return searchValue; }
}
