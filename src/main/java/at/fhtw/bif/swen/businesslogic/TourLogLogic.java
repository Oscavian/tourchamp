package at.fhtw.bif.swen.businesslogic;

import at.fhtw.bif.swen.businesslogic.ITourLogLogic;
import at.fhtw.bif.swen.persistence.ITourLogDataSource;

public class TourLogLogic implements ITourLogLogic {

    ITourLogDataSource dataSource;

    public TourLogLogic(ITourLogDataSource dataSource) {
        this.dataSource = dataSource;
    }
}
