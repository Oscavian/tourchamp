package at.fhtw.bif.swen.businesslogic;

import at.fhtw.bif.swen.businesslogic.ITourLogic;
import at.fhtw.bif.swen.dto.TourDTO;
import at.fhtw.bif.swen.mapper.TourMapper;
import at.fhtw.bif.swen.persistence.ITourDataSource;
import at.fhtw.bif.swen.persistence.entities.TourEntity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class TourLogic implements ITourLogic {

    ITourDataSource dataSource;
    public TourLogic(ITourDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void saveTour(TourDTO tourDTO) {
        dataSource.save(TourMapper.toEntity(tourDTO));
    }

    @Override
    public void updateTour(TourDTO tourDTO) {
        dataSource.update(TourMapper.toEntity(tourDTO));
    }

    @Override
    public void deleteTour(TourDTO tourDTO) {
        dataSource.delete(TourMapper.toEntity(tourDTO));
    }

    @Override
    public List<TourDTO> getAll() {
        var tourDTOS = new ArrayList<TourDTO>();
        for (var entity : dataSource.getAll()) {
            tourDTOS.add(TourMapper.fromEntity(entity));
        }
        return tourDTOS;
    }

    @Override
    public TourDTO getTourById(Integer id) {
        return TourMapper.fromEntity(dataSource.getById(id));
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