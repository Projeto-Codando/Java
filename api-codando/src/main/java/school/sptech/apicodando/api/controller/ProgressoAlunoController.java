package school.sptech.apicodando.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.apicodando.api.domain.pontosAluno.PontosDTO;
import school.sptech.apicodando.api.domain.progressoAluno.ProgressoAluno;
import school.sptech.apicodando.api.mapper.ProgressoAlunoMapper;
import school.sptech.apicodando.service.progressoAlunoService.ProgressoAlunoService;
import school.sptech.apicodando.service.progressoAlunoService.dto.ProgressoAlunoCadastroDTO;
import school.sptech.apicodando.service.progressoAlunoService.dto.ProgressoAlunoListagemDto;

import java.util.List;

@RequestMapping("/progresso-aluno")
@RequiredArgsConstructor
@RestController
@SecurityRequirement(name = "bearerAuth")
public class ProgressoAlunoController {

    private final ProgressoAlunoService progressoAlunoService;
    private final ProgressoAlunoMapper mapper;

    @Operation(summary = "Listar", description = "Método que lista o progresso do aluno por ID!", tags = "Progresso Aluno")
    @GetMapping("/aluno/{idAluno}")
    public ResponseEntity<ProgressoAlunoListagemDto> listarPorIdAluno(@PathVariable Integer idAluno) {
        return ResponseEntity.ok(mapper.toListagemDto(progressoAlunoService.buscarPorIdAluno(idAluno)));
    }

    @Operation(summary = "Criar", description = "Método que cria o progresso do aluno!", tags = "Progresso Aluno")
    @PostMapping
    public ResponseEntity<ProgressoAlunoListagemDto> criar(@RequestBody @Valid ProgressoAlunoCadastroDTO entidade){
        ProgressoAluno progressoAluno = progressoAlunoService.criar(entidade);
        return ResponseEntity.ok(mapper.toListagemDto(progressoAluno));
    }

    @Operation(summary = "Listar", description = "Método que lista todos os progressos do aluno!", tags = "Progresso Aluno")
    @GetMapping
    public ResponseEntity<List<ProgressoAlunoListagemDto>> listarTodos() {
        return ResponseEntity.ok(mapper.toListagemDto(progressoAlunoService.listarTodos()));
    }
    @Operation(summary = "Atualizar", description = "Método que atualiza os pontos do aluno!", tags = "Progresso Aluno")
    @PutMapping("/aluno/{idAluno}/aula/{idAula}/pontos")
    public ResponseEntity<ProgressoAlunoListagemDto> atualizarPontos(@PathVariable Integer idAluno, @RequestBody PontosDTO pontosDTO, @PathVariable Integer idAula){
        ProgressoAluno progressoAluno = progressoAlunoService.atualizarPontos(idAluno, pontosDTO, idAula);
        return ResponseEntity.ok(mapper.toListagemDto(progressoAluno));
    }
}
