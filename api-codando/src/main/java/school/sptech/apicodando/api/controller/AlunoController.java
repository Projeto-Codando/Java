package school.sptech.apicodando.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
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
import school.sptech.apicodando.service.alunoService.dto.AlunoAtualizadoDTO;
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
import java.util.UUID;

import static org.springframework.http.ResponseEntity.*;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/alunos")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class AlunoController {

    private final AlunoService alunoService;

    @Operation(summary = "Cadastrar", description = "Método que cadastra o aluno!", tags = "Aluno")
    @PostMapping
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<AlunoListagemDTO> criar(@RequestBody @Valid AlunoCadastroDTO novoAluno) {
        return status(201).body(AlunoMapper.toDto(alunoService.criar(novoAluno)));
    }

    @Operation(summary = "Login", description = "Método realiza o login do aluno!", tags = "Aluno")
    @PostMapping("/login")
    public ResponseEntity<AlunoTokenDto> login(@RequestBody AlunoLoginDTO usuarioLoginDto) {
        AlunoTokenDto usuarioTokenDto = this.alunoService.autenticar(usuarioLoginDto);
        return ok(usuarioTokenDto);
    }

    @Operation(summary = "Busca por ID", description = "Método que retorna o aluno buscado por ID!", tags = "Aluno")
    @GetMapping("/{id}")
    public ResponseEntity<AlunoListagemDTO> buscaPorId(@PathVariable UUID id) {
        AlunoListagemDTO dto = AlunoMapper.toDto(alunoService.listarUmPorId(id).get());
        return ok(dto);
    }

    @Operation(summary = "Listar", description = "Método que retorna todos os alunos!", tags = "Aluno")
    @GetMapping
    public ResponseEntity<List<AlunoListagemDTO>> listar() {
        List<Aluno> alunos = alunoService.listarTodos();
        return ok(AlunoMapper.toDto(alunos));
    }

    @Operation(summary = "Excluir", description = "Método que apaga um aluno!", tags = "Aluno")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable @Valid UUID id) {
        alunoService.excluir(id);
        return ok().build();
    }

    @Operation(summary = "Atualizar", description = "Método que atualiza o aluno!", tags = "Aluno")
    @PutMapping("/{id}")
    public ResponseEntity<AlunoListagemDTO> atualizar(@PathVariable UUID id, @RequestBody @Valid AlunoAtualizadoDTO alunoAlterado) {
        alunoService.atualizar(alunoAlterado, id);
        return ResponseEntity.ok().body(AlunoMapper.toDto(alunoService.listarUmPorId(id).get()));
    }

}
