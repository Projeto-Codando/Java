package school.sptech.apicodando.api.controller;

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

    @GetMapping("/aluno/{idAluno}")
    public ResponseEntity<ProgressoAlunoListagemDto> listarPorIdAluno(@PathVariable Integer idAluno) {
        return ResponseEntity.ok(mapper.toListagemDto(progressoAlunoService.buscarPorIdAluno(idAluno)));
    }

    @PostMapping
    public ResponseEntity<ProgressoAlunoListagemDto> criar(@RequestBody @Valid ProgressoAlunoCadastroDTO entidade){
        ProgressoAluno progressoAluno = progressoAlunoService.criar(entidade);
        return ResponseEntity.ok(mapper.toListagemDto(progressoAluno));
    }

    @GetMapping
    public ResponseEntity<List<ProgressoAlunoListagemDto>> listarTodos() {
        return ResponseEntity.ok(mapper.toListagemDto(progressoAlunoService.listarTodos()));
    }

    @PutMapping("/aluno/{idAluno}/pontos")
    public ResponseEntity<ProgressoAlunoListagemDto> atualizarPontos(@PathVariable Integer idAluno, @RequestBody PontosDTO pontosDTO) {
        ProgressoAluno progressoAluno = progressoAlunoService.atualizarPontos(idAluno, pontosDTO);
        return ResponseEntity.ok(mapper.toListagemDto(progressoAluno));
    }
}
