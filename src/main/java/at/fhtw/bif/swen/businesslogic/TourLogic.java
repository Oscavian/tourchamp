package at.fhtw.bif.swen.businesslogic;

import at.fhtw.bif.swen.businesslogic.services.ImportExport.ExportService;
import at.fhtw.bif.swen.businesslogic.services.ImportExport.ImportService;
import at.fhtw.bif.swen.dto.TourDTO;
import at.fhtw.bif.swen.dto.TourLogDTO;
import at.fhtw.bif.swen.mapper.TourMapper;
import at.fhtw.bif.swen.persistence.ITourDataSource;
import at.fhtw.bif.swen.persistence.entities.TourEntity;
import at.fhtw.bif.swen.persistence.entities.TourLogEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TourLogic implements ITourLogic {

    ITourDataSource dataSource;
    private final Logger logger = LogManager.getLogger(getClass().getName());
    public TourLogic(ITourDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void saveTour(TourDTO tourDTO) {
        tourDTO.setPopularity(1);
        tourDTO.setChildFriendliness(1);
        dataSource.save(TourMapper.toEntity(tourDTO));
        logger.debug(String.format("Tour named:%s saved!", tourDTO.getName()));
    }

    @Override
    public void updateTour(TourDTO tourDTO) {
        dataSource.update(TourMapper.toEntity(tourDTO));
        logger.debug(String.format("Tour ID: %d updated", tourDTO.getId()));
    }

    @Override
    public void deleteTour(Integer id) {
        dataSource.delete(dataSource.getById(id));
        logger.debug(String.format("Tour ID: %d deleted!", id));
    }

    @Override
    public List<TourDTO> getAll() {
        var tourDTOS = new ArrayList<TourDTO>();
        for (var entity : dataSource.getAll()) {
            var tourDTO = TourMapper.fromEntity(entity);
            setComputedAttributes(tourDTO);
            tourDTOS.add(tourDTO);
        }
        return tourDTOS;
    }

    @Override
    public TourDTO getTourById(Integer id) {
        TourDTO tour = TourMapper.fromEntity(dataSource.getById(id));
        setComputedAttributes(tour);
        return tour;
    }

    @Override
    public List<TourDTO> search(String searchString) {
        var tourDTOs = new ArrayList<TourDTO>();
        int logCount = this.getLogRatingCount();
        for (var entity : dataSource.search(searchString)) {
            TourDTO tourDTO = TourMapper.fromEntity(entity);
            setComputedAttributes(tourDTO);
            tourDTOs.add(tourDTO);
        }
        return tourDTOs;
    }

    @Override
    public void importTours(File tourFile) {
        ImportService is = new ImportService(tourFile, dataSource);
        try {
            is._import();
        } catch (IOException e) {
            logger.error("Failed to import tour file!");
            throw new RuntimeException(e);
        }
    }

    @Override
    public String exportTours() {
        ExportService es = new ExportService(dataSource);
        try {
            return es._export();
        } catch (IOException e) {
            logger.error("Failed to export tours!");
            throw new RuntimeException(e);
        }
    }

    private void setComputedAttributes(TourDTO tourDTO) {
        int logCount = this.getLogRatingCount();
        tourDTO.setEstimatedTime(this.computeEstimatedTime(tourDTO));
        tourDTO.setPopularity(this.computePopularity(tourDTO, logCount));
        tourDTO.setChildFriendliness(this.computeChildFriendliness(tourDTO));
    }
    // calculate average total time of tour logs
    private Integer computeEstimatedTime(TourDTO tourDTO) {
        int estimatedTime = 0;
        int i = 0;

        for (i = 0; i < tourDTO.getLogs().size(); i++) {
            estimatedTime += tourDTO.getLogs().get(i).getTotalTime();
        }
        if (i > 0) {
            estimatedTime /= i;
        }
        return estimatedTime;
    }

    // calculate percentage of number of tour logs
    private Integer computePopularity(TourDTO tour, int totalLogRatingCount) {
        int tourLogRatingCount = 0;
        for (TourLogDTO l : tour.getLogs()) {
            tourLogRatingCount += l.getRating();
        }
        return (int)((double) tourLogRatingCount / (double) totalLogRatingCount * 100);
    }

    private Integer getLogRatingCount() {
        int logs = 0;

        for (TourEntity e : this.dataSource.getAll()) {
            for (TourLogEntity l : e.getTourLogs()) {
                logs += l.getRating();
            }
        }
        return logs;
    }
    private Integer computeChildFriendliness(TourDTO tourDTO) {
        double childFriendliness = 0;
        childFriendliness += tourDTO.getPopularity() / 10.0;
        childFriendliness += tourDTO.getEstimatedTime();
        return (int) childFriendliness / 2;
    }
}
