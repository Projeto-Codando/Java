package school.sptech.apicodando.api.controller;

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

import java.util.List;

@RestController
@RequestMapping("/aulas")
@SecurityRequirement(name = "bearerAuth")
@RequiredArgsConstructor
public class AulaController {

    private final AulaService aulaService;

    @GetMapping
    public ResponseEntity<List<AulaListagemDTO>> listarAulas() {
        List<Aula> aulas = aulaService.listarAulas();
        if (aulas.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.ok(aulas.stream().map(AulaMapper::toDto).toList());
    }

    @GetMapping("/{idTema}")
    public ResponseEntity<List<AulaListagemDTO>> listarAulasPorTema(@PathVariable Integer idTema) {
        List<Aula> aulas = aulaService.listarAulasPorTema(idTema);
        if (aulas.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.ok(aulas.stream().map(AulaMapper::toDto).toList());
    }

    @PostMapping()
    public ResponseEntity<AulaListagemDTO> criar(@RequestBody @Valid AulaCriacaoDTO aulaNova){
        Aula aula = aulaService.criar(aulaNova);
        return ResponseEntity.created(null).body(AulaMapper.toDto(aula));
    }
}
