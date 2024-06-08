package school.sptech.apicodando.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.apicodando.api.domain.grade.Grade;
import school.sptech.apicodando.api.mapper.GradeMapper;
import school.sptech.apicodando.service.gradeService.GradeService;
import school.sptech.apicodando.service.gradeService.dto.GradeCadastroDto;
import school.sptech.apicodando.service.gradeService.dto.GradeListagemDto;

import java.net.URI;
import java.util.List;

import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/grades")
@SecurityRequirement(name = "bearerAuth")
@RequiredArgsConstructor
public class GradeController {

    private final GradeService gradeService;

    @Operation(summary = "Cadastar", description = "Método que cadastra uma grade!", tags = "Grade")
    @PostMapping
//    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<GradeListagemDto> criar(@RequestBody GradeCadastroDto gradeNova) {
        GradeListagemDto novaGrade = GradeMapper.toDto(gradeService.criar(gradeNova));
        URI location = URI.create(String.format("/%d", novaGrade.getIdGrade()));
        return ResponseEntity.created(location).body(novaGrade);
    }

    @Operation(summary = "Listar", description = "Método que lista uma grade por Id!", tags = "Grade")
    @GetMapping("/{id}")
    public ResponseEntity<GradeListagemDto> listar(@PathVariable int id) {
        return status(200).body(gradeService.listarPorId(id));
    }

//    @Operation(summary = "Listar", description = "Método que lista grades por idTurma!", tags = "Grade")
//    @GetMapping("/turma /{id}")
//    public ResponseEntity<List<GradeListagemDto>> listarPorTurma(@PathVariable int idTurma) {
//
//        List<GradeListagemDto> grades = gradeService.listarPorTurma(idTurma);
//
//        if (grades.isEmpty()) {
//            return status(204).build();
//        }
//        return ok().body(grades);
//    }


    @Operation(summary = "Listar", description = "Método que lista todas as grades!", tags = "Grade")
    @GetMapping
    public ResponseEntity <List<GradeListagemDto>> listarTodos() {
        return status(200).body(gradeService.listarTodos());
    }

}
