package school.sptech.apicodando.domain.csvFile.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.apicodando.domain.aula.Aula;
import school.sptech.apicodando.domain.csvFile.CsvFile;

import java.util.List;

public interface CsvFileRepository extends JpaRepository<CsvFile, Integer> {
    List<CsvFile> findByNM_DIRETORIA(String NM_DIRETORIA);


}
