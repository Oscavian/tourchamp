package at.fhtw.bif.swen.businesslogic.services.ImportExport;

import at.fhtw.bif.swen.mapper.TourMapper;
import at.fhtw.bif.swen.dto.TourDTO;
import at.fhtw.bif.swen.persistence.ITourDataSource;
import at.fhtw.bif.swen.persistence.entities.TourEntity;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExportService {
    private final ObjectMapper objectMapper;

    private final ITourDataSource repository;
    private final Logger logger = LogManager.getLogger(getClass().getName());

    public ExportService(ITourDataSource repository) {
        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModule(new JavaTimeModule());
        this.repository = repository;
    }

    public String _export() throws IOException {
        List<TourEntity> tourEs = repository.getAll();
        List<TourDTO> tourDTOs = new ArrayList<>();

        logger.debug("Exporting tours");
        for (TourEntity tourEntity : tourEs) {
            TourDTO tourDTO = TourMapper.fromEntity(tourEntity);
            tourDTOs.add(tourDTO);
        }
        return this.objectMapper.writeValueAsString(tourDTOs);
    }
}
