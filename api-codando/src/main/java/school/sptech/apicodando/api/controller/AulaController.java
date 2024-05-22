package school.sptech.apicodando.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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

    @PostMapping("/id")
    public ResponseEntity<Aula> criar(@RequestBody AulaCriacaoDTO aulaNova){

        Aula aula = aulaService.criar(aulaNova, aulaNova.getTemaId());

        return ResponseEntity.created(null).body(aula);
    }


}
