package school.sptech.apicodando.service.csvFileService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import school.sptech.apicodando.domain.csvFile.CsvFile;
import school.sptech.apicodando.domain.csvFile.repository.CsvFileRepository;

import java.util.List;

@Service
public class CsvFileService {
    @Autowired
    private CsvFileRepository csvFileRepository;

    public void listarPorDiretoria(String diretoria) {
        csvFileRepository.findByNM_DIRETORIA(diretoria);
    }

    public List<CsvFile> quickSortByDiretoriaAndByREP_2() {
        List<CsvFile> csvFiles = csvFileRepository.findAll();
        quickSort(csvFiles, 0, csvFiles.size() - 1);
        return csvFiles;
    }

    private void quickSort(List<CsvFile> csvFiles, int low, int high) {
        if (low < high) {
            int pi = partition(csvFiles, low, high);

            quickSort(csvFiles, low, pi - 1);
            quickSort(csvFiles, pi + 1, high);
        }
    }

    private int partition(List<CsvFile> csvFiles, int low, int high) {
        CsvFile pivot = csvFiles.get(high);
        int i = (low - 1);
        for (int j = low; j <= high - 1; j++) {
            if (csvFiles.get(j).getNM_DIRETORIA().compareTo(pivot.getNM_DIRETORIA()) < 0 ||
                    (csvFiles.get(j).getNM_DIRETORIA().equals(pivot.getNM_DIRETORIA()) && csvFiles.get(j).getREP_2() < pivot.getREP_2())) {
                i++;

                CsvFile temp = csvFiles.get(i);
                csvFiles.set(i, csvFiles.get(j));
                csvFiles.set(j, temp);
            }
        }

        CsvFile temp = csvFiles.get(i + 1);
        csvFiles.set(i + 1, csvFiles.get(high));
        csvFiles.set(high, temp);

        return (i + 1);
    }

}
