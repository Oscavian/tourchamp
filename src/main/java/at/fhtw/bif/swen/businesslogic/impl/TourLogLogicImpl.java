package at.fhtw.bif.swen.businesslogic.impl;

import at.fhtw.bif.swen.businesslogic.ITourLogLogic;
import at.fhtw.bif.swen.persistence.ITourLogDataSource;

public class TourLogLogicImpl implements ITourLogLogic {

    ITourLogDataSource dataSource;

    public TourLogLogicImpl(ITourLogDataSource dataSource) {
        this.dataSource = dataSource;
    }
}
