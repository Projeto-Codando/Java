package school.sptech.apicodando.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.apicodando.service.gradeService.GradeService;
import school.sptech.apicodando.service.moduloService.ModuloService;
import school.sptech.apicodando.service.moduloService.dto.ModuloCadastroDTO;
import school.sptech.apicodando.service.moduloService.dto.ModuloListagemDTO;

import java.util.List;

@RestController
@RequestMapping("/modulos")
@SecurityRequirement(name = "bearerAuth")
@RequiredArgsConstructor
public class ModuloController {

    private final ModuloService moduloService;

    @PostMapping
    public ResponseEntity<ModuloCadastroDTO> criar(@RequestBody ModuloCadastroDTO moduloCadastro) {
        moduloService.criar(moduloCadastro, moduloCadastro.getGradeId());

        return ResponseEntity.created(null).body(moduloCadastro);
    }

    @GetMapping
    public ResponseEntity<List<ModuloListagemDTO>> listarModulos() {

        return ResponseEntity.ok(moduloService.listarModulos());
    }

    @GetMapping("/grade/{idGrade}")
    public ResponseEntity<List<ModuloListagemDTO>> listarPorGrade(@PathVariable Integer idGrade) {

        return ResponseEntity.ok(moduloService.listarPorGrade(idGrade));
    }


}
