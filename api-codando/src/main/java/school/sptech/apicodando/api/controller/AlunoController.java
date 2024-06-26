package school.sptech.apicodando.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.apicodando.api.domain.csvFile.csv;
import school.sptech.apicodando.service.alunoService.AlunoService;
import school.sptech.apicodando.service.alunoService.dto.AlunoCadastroDTO;
import school.sptech.apicodando.service.alunoService.dto.AlunoListagemDTO;
import school.sptech.apicodando.api.mapper.AlunoMapper;
import school.sptech.apicodando.api.domain.aluno.Aluno;
import jakarta.validation.Valid;
import school.sptech.apicodando.service.alunoService.dto.dtoAuthAluno.AlunoLoginDTO;
import school.sptech.apicodando.service.alunoService.dto.dtoAuthAluno.AlunoTokenDto;
import school.sptech.apicodando.service.csvFileService.CsvFileService;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Formatter;
import java.util.List;

import static org.springframework.http.ResponseEntity.*;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/alunos")
@SecurityRequirement(name = "bearerAuth")
public class AlunoController {

//    @Autowired
//    private AlunoRepository alunoRepository;
    @Autowired
    private AlunoService alunoService;

    @Autowired
    private CsvFileService csvFileService;

    @Operation(summary = "Cadastrar", description = "Método que cadastra o aluno!", tags = "Aluno")
    @PostMapping
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<Void> criar(@RequestBody @Valid AlunoCadastroDTO novoAluno) {
        this.alunoService.criar(novoAluno);
        return status(201).build();
    }

    @Operation(summary = "Login", description = "Método realiza o login do aluno!", tags = "Aluno")
    @PostMapping("/login")
    public ResponseEntity<AlunoTokenDto> login(@RequestBody AlunoLoginDTO usuarioLoginDto) {
        AlunoTokenDto usuarioTokenDto = this.alunoService.autenticar(usuarioLoginDto);

        return ok(usuarioTokenDto);
    }

    @Operation(summary = "Busca por ID", description = "Método que retorna o aluno buscado por ID!", tags = "Aluno")
    @GetMapping("/{id}")
    public ResponseEntity<AlunoListagemDTO> buscaPorId(@PathVariable int id) {
        AlunoListagemDTO dto = AlunoMapper.toDto(alunoService.listarUmPorId(id).get());
        return ok(dto);
    }

    @Operation(summary = "Listar", description = "Método que retorna todos os alunos!", tags = "Aluno")
    @GetMapping
    public ResponseEntity<List<AlunoListagemDTO>> listar() {
        List<Aluno> alunos = alunoService.listarTodos();
        List<AlunoListagemDTO> listaAuxiliar = AlunoMapper.toDto(alunos);
        return ok(listaAuxiliar);
    }

    @Operation(summary = "Excluir", description = "Método que apaga um aluno!", tags = "Aluno")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable @Valid int id) {
        alunoService.excluir(id);
        return ok().build();
    }

    @Operation(summary = "Atualizar", description = "Método que atualiza o aluno!", tags = "Aluno")
    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizar(@PathVariable("id") @Valid int id,
                                          @RequestBody @Valid Aluno alunoAlterado) {
        alunoService.atualizar(alunoAlterado, id);
        return ResponseEntity.noContent().build();
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

    @Operation(summary = "Baixar Arquivo", description = "Método que realiza o download do JSON!", tags = "Escola")
    @GetMapping("/download/{filename}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String filename) {
        Path path = Paths.get("caminho/para/o/arquivo", filename + ".csv");
        Resource resource = new FileSystemResource(path);

        // Criar cabeçalho para o download
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename + ".csv");
        headers.add(HttpHeaders.CONTENT_TYPE, "text/csv");

        // Retornar o arquivo para download
        return ResponseEntity.ok()
                .headers(headers)
                .body(resource);
    }

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

    @Operation(summary = "Listar por Diretoria", description = "Método que lista alunos por diretoria!", tags = "Aluno")
    @GetMapping("/listarPorREP2")
    public ResponseEntity<List<csv>> listarPorREP2() {
        List<csv> csvs = csvFileService.quickSortByDiretoriaAndByREP_2();
        return ok(csvs);
    }



}
