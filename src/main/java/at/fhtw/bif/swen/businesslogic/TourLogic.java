package at.fhtw.bif.swen.businesslogic;

import at.fhtw.bif.swen.businesslogic.services.ImportExport.ExportService;
import at.fhtw.bif.swen.businesslogic.services.ImportExport.ImportService;
import at.fhtw.bif.swen.dto.TourDTO;
import at.fhtw.bif.swen.mapper.TourMapper;
import at.fhtw.bif.swen.persistence.ITourDataSource;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TourLogic implements ITourLogic {

    ITourDataSource dataSource;
    public TourLogic(ITourDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void saveTour(TourDTO tourDTO) {
        tourDTO.setPopularity(1);
        tourDTO.setChildFriendliness(1);
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
            var tourDTO = TourMapper.fromEntity(entity);
            tourDTO.setEstimatedTime(2);
            tourDTO.setChildFriendliness(1);
            tourDTOS.add(tourDTO);
        }
        return tourDTOS;
    }

    @Override
    public TourDTO getTourById(Integer id) {
        TourDTO tour = TourMapper.fromEntity(dataSource.getById(id));
        tour.setEstimatedTime(2);
        tour.setChildFriendliness(1);
        return tour;
    }

    @Override
    public List<TourDTO> search(String searchString) {
        var tourDTOs = new ArrayList<TourDTO>();
        for (var entity : dataSource.search(searchString)) {
            tourDTOs.add(TourMapper.fromEntity(entity));
        }
        return tourDTOs;
    }

    @Override
    public void importTours(File tourFile) {
        ImportService is = new ImportService(tourFile, dataSource);
        try {
            is._import();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String exportTours() {
        ExportService es = new ExportService(dataSource);
        try {
            return es._export();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
