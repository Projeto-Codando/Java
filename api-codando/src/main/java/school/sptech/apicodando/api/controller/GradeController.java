package school.sptech.apicodando.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.apicodando.api.domain.grade.Grade;
import school.sptech.apicodando.service.gradeService.GradeService;
import school.sptech.apicodando.service.gradeService.dto.GradeCadastroDto;
import school.sptech.apicodando.service.gradeService.dto.GradeListagemDto;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/grades")
//@NoArgsConstructor
public class GradeController {

    @Autowired
    private GradeService gradeService;

    @Operation(summary = "Cadastar", description = "Método que cadastra uma grade!", tags = "Grade")
    @PostMapping
//    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<Void> criar(@RequestBody GradeCadastroDto gradeNova) {
        gradeService.criar(gradeNova);
        return status(201).build();
    }

    @Operation(summary = "Listar", description = "Método que lista uma grade por Id!", tags = "Grade")
    @GetMapping("/{id}")
//    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<GradeListagemDto> listar(@PathVariable int id) {
        return status(200).body(gradeService.listarPorId(id));
    }
    @Operation(summary = "Listar", description = "Método que lista grades por idTurma!", tags = "Grade")
    @GetMapping("/turma /{id}")
    public ResponseEntity<List<GradeListagemDto>> listarPorTurma(@PathVariable int idTurma) {

        List<GradeListagemDto> grades = gradeService.listarPorTurma(idTurma);

        if (grades.isEmpty()) {
            return status(204).build();
        }
        return ok().body(grades);
    }


    @Operation(summary = "Listar", description = "Método que lista todas as grades!", tags = "Grade")
    @GetMapping
//    @SecurityRequirement(name = "Bearer")
    public ResponseEntity <List<GradeListagemDto>> listarTodos() {
        return status(200).body(gradeService.listarTodos());
    }

}
