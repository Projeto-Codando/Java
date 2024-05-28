package school.sptech.apicodando.api.domain.csvFile.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.apicodando.api.domain.csvFile.csv;

import java.util.List;
import java.util.UUID;

public interface CsvFileRepository extends JpaRepository<csv, UUID> {
    List<csv> findBynmDiretoria(String nm_diretoria);

}
