package school.sptech.apicodando.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import school.sptech.apicodando.api.domain.aula.Aula;
import school.sptech.apicodando.service.aulaService.AulaService;
import school.sptech.apicodando.service.aulaService.dto.AulaCriacaoDTO;
import school.sptech.apicodando.service.aulaService.dto.AulaListagemDTO;
import school.sptech.apicodando.api.mapper.AulaMapper;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/aulas")
@SecurityRequirement(name = "bearerAuth")
@RequiredArgsConstructor
public class AulaController {

    private final AulaService aulaService;

    @Operation(summary = "Listar aulas", description = "Método que lista todas as aulas!", tags = "Aula")
    @GetMapping
    public ResponseEntity<List<AulaListagemDTO>> listarAulas() {
        List<Aula> aulas = aulaService.listarAulas();
        if (aulas.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.ok(aulas.stream().map(AulaMapper::toDto).toList());
    }

    @Operation(summary = "Listar",description = "Listar todas as aulas de um tema específico",tags = "Aula")
    @GetMapping("/tema/{idTema}")
    public ResponseEntity<List<AulaListagemDTO>> listarAulasPorTema(@PathVariable Integer idTema) {
        List<AulaListagemDTO> aulas = aulaService.listarAulasPorTema(idTema);
        if (aulas.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.ok(aulas);
    }

    @Operation(summary = "Listar",description = "Listar todas as aulas de uma grade específica",tags = "Aula")
    @GetMapping("/grade/{idGrade}")
    public ResponseEntity<List<AulaListagemDTO>> listarAulasPorGrade(@PathVariable Integer idGrade) {
        List<AulaListagemDTO> aulas = aulaService.listarAulasPorGrade(idGrade);

        if (aulas.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.ok(aulas);
    }

    @Operation(summary = "Cadastrar",description = "Método que cria uma Aula!",tags = "Aula")
    @PostMapping()
    public ResponseEntity<AulaListagemDTO> criar(@RequestBody @Valid AulaCriacaoDTO aulaNova){
        AulaListagemDTO aula = AulaMapper.toDto(aulaService.criar(aulaNova));
        URI location = URI.create(String.format("/%d", aula.getId()));
        return ResponseEntity.created(location).body(aula);
    }
}
