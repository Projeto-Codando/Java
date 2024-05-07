package school.sptech.apicodando.domain.csvFile.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.apicodando.domain.csvFile.csv;

import java.util.List;

public interface CsvFileRepository extends JpaRepository<csv, Integer> {
    List<csv> findBynmDiretoria(String nm_diretoria);

}
