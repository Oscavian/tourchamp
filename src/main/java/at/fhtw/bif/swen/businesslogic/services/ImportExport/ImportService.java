package at.fhtw.bif.swen.businesslogic.services.ImportExport;

import at.fhtw.bif.swen.mapper.TourMapper;
import at.fhtw.bif.swen.dto.TourDTO;
import at.fhtw.bif.swen.persistence.ITourDataSource;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class ImportService {

    private final ObjectMapper objectMapper;
    private final File file;
    private ITourDataSource repository;


    public ImportService(File file, ITourDataSource repository) {
        this.objectMapper = new ObjectMapper();
        this.file = file;
        this.repository = repository;
    }

    public void _import() throws IOException {
        List<TourDTO> tours = this.objectMapper.readValue(this.file,
                new TypeReference<List<TourDTO>>() {
        });

        for (TourDTO tour : tours) {
            tour.setId(null);
            repository.save(TourMapper.toEntity(tour));
        }
    }
}
