package school.sptech.apicodando.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.apicodando.api.domain.modulo.Modulo;
import school.sptech.apicodando.api.mapper.ModuloMapper;
import school.sptech.apicodando.service.gradeService.GradeService;
import school.sptech.apicodando.service.moduloService.ModuloService;
import school.sptech.apicodando.service.moduloService.dto.ModuloCadastroDTO;
import school.sptech.apicodando.service.moduloService.dto.ModuloListagemDTO;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/modulos")
@SecurityRequirement(name = "bearerAuth")
@RequiredArgsConstructor
public class ModuloController {

    private final ModuloService moduloService;

    @PostMapping
    public ResponseEntity<ModuloListagemDTO> criar(@RequestBody ModuloCadastroDTO moduloCadastro) {
        ModuloListagemDTO moduloNovo = ModuloMapper.toDto(moduloService.criar(moduloCadastro, moduloCadastro.getGradeId()));
        URI location = URI.create(String.format("/%d", moduloNovo.getIdModulo()));
        return ResponseEntity.created(location).body(moduloNovo);
    }

    @GetMapping
    public ResponseEntity<List<ModuloListagemDTO>> listarModulos() {

        return ResponseEntity.ok(moduloService.listarModulos());
    }

    @GetMapping("/grade/{idGrade}")
    public ResponseEntity<List<ModuloListagemDTO>> listarPorGrade(@PathVariable Integer idGrade) {

        return ResponseEntity.ok(moduloService.listarPorGrade(idGrade));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ModuloListagemDTO> listarPorId(@PathVariable Integer id) {

        return ResponseEntity.ok(moduloService.listarPorId(id));
    }

}
