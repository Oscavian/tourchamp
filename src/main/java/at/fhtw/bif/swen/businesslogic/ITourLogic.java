package at.fhtw.bif.swen.businesslogic;

import at.fhtw.bif.swen.dto.TourDTO;

public interface ITourLogic {

    default TourDTO saveTour() {
        return null;
    }

}
