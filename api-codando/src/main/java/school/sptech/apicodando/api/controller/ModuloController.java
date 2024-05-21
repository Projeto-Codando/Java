package school.sptech.apicodando.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.apicodando.service.gradeService.GradeService;
import school.sptech.apicodando.service.moduloService.ModuloService;
import school.sptech.apicodando.service.moduloService.dto.ModuloCadastroDTO;

@RestController
@RequestMapping("/modulos")
@SecurityRequirement(name = "bearerAuth")
@RequiredArgsConstructor
public class ModuloController {

    private final ModuloService moduloService;

    @PostMapping
    public ResponseEntity<ModuloCadastroDTO> criar(ModuloCadastroDTO moduloCadastro) {
        moduloService.criar(moduloCadastro, moduloCadastro.getGradeId());
        return ResponseEntity.ok(moduloCadastro);
    }

    @GetMapping
    public ResponseEntity<String> listarModulos() {

        return ResponseEntity.ok(moduloService.listarModulos());
    }


}
