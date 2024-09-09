package school.sptech.apicodando.api.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.apicodando.api.domain.aluno.Aluno;
import school.sptech.apicodando.api.domain.csvFile.csv;
import school.sptech.apicodando.api.mapper.AlunoMapper;
import school.sptech.apicodando.service.alunoService.AlunoService;
import school.sptech.apicodando.service.alunoService.dto.AlunoListagemDTO;
import school.sptech.apicodando.service.csvFileService.CsvFileService;
import school.sptech.apicodando.service.turmaService.TurmaService;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.Formatter;
import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/csvFile")
@RequiredArgsConstructor
public class CsvFileController {

    private final CsvFileService csvFileService;

    @Operation(summary = "Listar por Diretoria", description = "Método que lista alunos por diretoria!", tags = "Aluno")
    @GetMapping("/listarPorREP2")
    public ResponseEntity<List<csv>> listarPorREP2() {
        List<csv> csvs = csvFileService.quickSortByDiretoriaAndByREP_2();
        return ok(csvs);
    }

    @Operation(summary = "Listar Escolas", description = "Método que lista escolas do JSON!", tags = "Escola")
    @GetMapping("/pesquisaEscolas")
    public ResponseEntity<Object> getJsonFile() {
        try {
            Resource resource = new ClassPathResource("resultado.json");
            ObjectMapper mapper = new ObjectMapper();
            return ResponseEntity.ok(mapper.readValue(resource.getInputStream(), Object.class));
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


}
