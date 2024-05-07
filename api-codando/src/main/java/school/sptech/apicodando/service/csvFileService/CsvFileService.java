package school.sptech.apicodando.service.csvFileService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import school.sptech.apicodando.domain.csvFile.csv;
import school.sptech.apicodando.domain.csvFile.repository.CsvFileRepository;

import java.util.List;

@Service
public class CsvFileService {
    @Autowired
    private CsvFileRepository csvFileRepository;

    public void listarPorDiretoria(String diretoria) {
        csvFileRepository.findBynmDiretoria(diretoria);
    }

    public List<csv> quickSortByDiretoriaAndByREP_2() {
        List<csv> csvFiles = csvFileRepository.findAll();
        quickSort(csvFiles, 0, csvFiles.size() - 1);
        return csvFiles;
    }

    private void quickSort(List<csv> csvFiles, int low, int high) {
        if (low < high) {
            int pi = partition(csvFiles, low, high);

            quickSort(csvFiles, low, pi - 1);
            quickSort(csvFiles, pi + 1, high);
        }
    }

    private int partition(List<csv> csvFiles, int low, int high) {
        csv pivot = csvFiles.get(high);
        int i = (low - 1);
        for (int j = low; j <= high - 1; j++) {
            if (csvFiles.get(j).getRep2() > pivot.getRep2() ||
                    (csvFiles.get(j).getRep2() == pivot.getRep2() && csvFiles.get(j).getNmDiretoria().compareTo(pivot.getNmDiretoria()) < 0)) {
                i++;

                csv temp = csvFiles.get(i);
                csvFiles.set(i, csvFiles.get(j));
                csvFiles.set(j, temp);
            }
        }

        csv temp = csvFiles.get(i + 1);
        csvFiles.set(i + 1, csvFiles.get(high));
        csvFiles.set(high, temp);

        return (i + 1);
    }
}
