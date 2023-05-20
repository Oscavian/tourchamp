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
    private final String filePath;
    private ITourDataSource repository;


    public ImportService(String filePath, ITourDataSource repository) {
        this.objectMapper = new ObjectMapper();
        this.filePath = filePath;
        this.repository = repository;
    }

    public void _import() throws IOException {
        List<TourDTO> tours = this.objectMapper.readValue(new File(this.filePath),
                new TypeReference<List<TourDTO>>() {
        });

        for (TourDTO tour : tours) {
            repository.save(TourMapper.toEntity(tour));
        }
    }
}
