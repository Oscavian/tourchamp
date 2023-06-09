package at.fhtw.bif.swen.businesslogic.services.ImportExport;

import at.fhtw.bif.swen.mapper.TourMapper;
import at.fhtw.bif.swen.dto.TourDTO;
import at.fhtw.bif.swen.persistence.ITourDataSource;
import at.fhtw.bif.swen.persistence.entities.TourEntity;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class ImportService {

    private final ObjectMapper objectMapper;
    private final File file;
    private final ITourDataSource repository;

    private final Logger logger = LogManager.getLogger(getClass().getName());

    public ImportService(File file, ITourDataSource repository) {
        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModule(new JavaTimeModule());
        this.file = file;
        this.repository = repository;
    }

    public void _import() throws IOException {
        List<TourDTO> tours = this.objectMapper.readValue(this.file,
                new TypeReference<List<TourDTO>>() {
        });
        logger.debug("Importing tours from '" + this.file.getName() + "'");
        // remove ids to avoid hibernate persistent object exception (detached entity)
        for (TourDTO tour : tours) {
            tour.setId(null);
           // TourDTO tmpTour = tour;
          //  tmpTour.setLogs(null);
           // repository.save(Tou);
            tour.getLogs().forEach(log -> {
                log.setId(null);
            });
            repository.update(TourMapper.toEntity(tour));
        }
    }
}
