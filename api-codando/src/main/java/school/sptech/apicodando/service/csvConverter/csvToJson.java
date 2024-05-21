package school.sptech.apicodando.service.csvConverter;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class csvToJson {
    public static void main(String[] args) {
        String caminhoArquivoCSV = "\\Java\\api-codando\\src\\main\\resources\\fluxo_escolar.csv";
        String caminhoArquivoJSON = "resultado.json";

        try {
            FileReader leitorArquivo = new FileReader(caminhoArquivoCSV);
            CSVParser parserCSV = new CSVParserBuilder().withSeparator(';').build();
            CSVReader leitorCSV = new CSVReaderBuilder(leitorArquivo).withCSVParser(parserCSV).build();

            String[] cabecalhos = leitorCSV.readNext();
            int colunasEsperadas = cabecalhos.length;

            List<JSONObject> objetosJSON = new ArrayList<>();

            String[] linha;
            while ((linha = leitorCSV.readNext()) != null) {
                if (linha.length < colunasEsperadas) {
                    System.err.println("Linha incompleta. Pulando.");
                    continue;
                }

                JSONObject objetoJSON = new JSONObject();

                for (int i = 0; i < colunasEsperadas; i++) {
                    objetoJSON.put(cabecalhos[i], linha[i]);
                }

                objetosJSON.add(objetoJSON);
            }

            JSONArray arrayJSON = new JSONArray(objetosJSON);

            try (FileWriter escritorJSON = new FileWriter(caminhoArquivoJSON)) {
                escritorJSON.write(arrayJSON.toString(4));
            }

            System.out.println("Conversão concluída. Arquivo JSON salvo em: " + caminhoArquivoJSON);

        } catch (IOException e) {
            System.err.println("Erro ao converter CSV para JSON: " + e.getMessage());
        } catch (CsvValidationException e) {
            System.err.println("Erro de validação do CSV: " + e.getMessage());
        }
    }
}