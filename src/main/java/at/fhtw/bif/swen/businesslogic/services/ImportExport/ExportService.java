package at.fhtw.bif.swen.businesslogic.services.ImportExport;

import at.fhtw.bif.swen.businesslogic.mapper.TourMapper;
import at.fhtw.bif.swen.dto.TourDTO;
import at.fhtw.bif.swen.persistence.ITourDataSource;
import at.fhtw.bif.swen.persistence.entities.TourEntity;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExportService {
    private ObjectMapper objectMapper;
    private final String filePath;

    private final ITourDataSource repository;

    public ExportService(ITourDataSource repository) {
        this.repository = repository;
        this.filePath = "./exports/export.json";
    }

    public ExportService(String filePath, ITourDataSource repository) {
        this.repository = repository;
        this.filePath = filePath;
    }

    public void _export() throws IOException {
        List<TourEntity> tourEs = repository.getAll();
        List<TourDTO> tourDTOs = new ArrayList<>();
        TourMapper mapper = new TourMapper();

        for (TourEntity tourEntity : tourEs) {
            TourDTO tourDTO = mapper.fromEntity(tourEntity);
            tourDTOs.add(tourDTO);
        }

        this.objectMapper.writeValue(new File(this.filePath), tourDTOs);
    }
}
