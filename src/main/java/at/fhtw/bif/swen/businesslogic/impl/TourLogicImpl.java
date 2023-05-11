package at.fhtw.bif.swen.businesslogic.impl;

import at.fhtw.bif.swen.businesslogic.ITourLogic;
import at.fhtw.bif.swen.persistence.ITourDataSource;
import lombok.extern.slf4j.Slf4j;

public class TourLogicImpl implements ITourLogic {

    ITourDataSource dataSource;
    public TourLogicImpl(ITourDataSource dataSource) {
        this.dataSource = dataSource;
    }
}
