package school.sptech.apicodando.api.controller;

import com.fasterxml.jackson.databind.annotation.JsonAppend;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.apicodando.api.domain.aluno.Aluno;
import school.sptech.apicodando.api.mapper.AlunoMapper;
import school.sptech.apicodando.service.alunoService.AlunoService;
import school.sptech.apicodando.service.alunoService.dto.AlunoListagemDTO;
import school.sptech.apicodando.service.csvFileService.CsvFileService;
import school.sptech.apicodando.service.turmaService.TurmaService;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Formatter;
import java.util.List;

@RestController
@RequestMapping("/csvFiles")
public class CsvFileController {
    @Autowired
    private AlunoService alunoService;

    @Autowired
    private CsvFileService csvFileService;

    @Autowired
    private TurmaService turmaService;

    @Operation(summary = "Gerar Arquivo", description = "Método que gera o arquivo CSV!", tags = "Escola")
    @GetMapping("/gerarCSV")
    public ResponseEntity<Resource> gerarEbaixarCSV() {
        List<Aluno> alunosNoDTO = alunoService.listarTodos();

        List<AlunoListagemDTO> alunos = AlunoMapper.toDto(alunosNoDTO);

        if(alunos.isEmpty()){
            System.out.println("Lista vazia");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        FileWriter file = null;
        Formatter saida = null;
        Boolean badWay = false;

        String fileName = "alunos.csv";

        try {
            file = new FileWriter(fileName);
            saida = new Formatter(file);
        } catch (Exception e) {
            System.out.println("Erro ao criar arquivo: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }

        try {
            for (AlunoListagemDTO aluno : alunos) {
                saida.format("%d;%s;%s\n", aluno.getIdAluno(), aluno.getNome(), aluno.getApelido());
            }

        } catch (Exception e) {
            System.out.println("Erro ao gravar no arquivo: " + e.getMessage());
            e.printStackTrace();
            badWay = true;
        } finally {
            saida.close();
            try {
                file.close();
            } catch (IOException e) {
                System.out.println("Erro ao fechar arquivo: " + e.getMessage());
                e.printStackTrace();
                badWay = true;
            }
            if (badWay) {
                System.exit(1);
            }
        }

        Path path = Paths.get(fileName);
        Resource resource = new FileSystemResource(path);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName);
        headers.add(HttpHeaders.CONTENT_TYPE, "text/csv");

        return ResponseEntity.ok()
                .headers(headers)
                .body(resource);
    }
}
