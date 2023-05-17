package at.fhtw.bif.swen.businesslogic.impl;

import at.fhtw.bif.swen.businesslogic.ITourLogic;
import at.fhtw.bif.swen.dto.TourDTO;
import at.fhtw.bif.swen.persistence.ITourDataSource;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.util.List;

public class TourLogicImpl implements ITourLogic {

    ITourDataSource dataSource;
    public TourLogicImpl(ITourDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void saveTour(TourDTO tourDTO) {

        //dto is incomplete
        //complete length, duration, etc. with map api
        //save to db
    }

    @Override
    public void updateTour(TourDTO tourDTO) {

    }

    @Override
    public void deleteTour(TourDTO tourDTO) {

    }

    @Override
    public List<TourDTO> getAll() {
        return null;
    }

    @Override
    public TourDTO getTourById(Integer id) {
        return null;
    }

    @Override
    public List<TourDTO> search(String searchString) {
        return null;
    }

    @Override
    public void importTours(File tourFile) {

    }

    @Override
    public void exportTours() {

    }
}
