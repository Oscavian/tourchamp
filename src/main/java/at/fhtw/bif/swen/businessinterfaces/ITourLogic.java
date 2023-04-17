package at.fhtw.bif.swen.businessinterfaces;

import at.fhtw.bif.swen.dto.TourDTO;

public interface ITourLogic {

    default TourDTO saveTour() {
        return null;
    }

}
