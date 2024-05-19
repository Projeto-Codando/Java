package school.sptech.apicodando.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.apicodando.api.domain.turma.Turma;
import school.sptech.apicodando.service.turmaService.TurmaService;
import school.sptech.apicodando.service.turmaService.dto.TurmaCadastroDTO;
import school.sptech.apicodando.service.turmaService.dto.TurmaListagemDTO;

import java.util.List;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/turmas")
@NoArgsConstructor
public class TurmaController {
    @Autowired
    private TurmaService turmaService;

    @Operation(summary = "Cadastar", description = "Método que cadastra uma turma!", tags = "Turma")
    @PostMapping
//    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<Void> criar(@RequestBody @Valid TurmaCadastroDTO novaTurma) {
        turmaService.criar(novaTurma);
        return status(201).build();
    }

    @Operation(summary = "Listar", description = "Método que lista todas as turmas de um determinado Educador!", tags = "Turma")
    @GetMapping("/{idProfessor}")
    public ResponseEntity<List<TurmaListagemDTO>> listarPorProfessor(@PathVariable int idProfessor) {
        return status(200).body(this.turmaService.listarTodasTurmasPorProfessor(idProfessor));
    }

    @Operation(summary = "Editar", description = "Método que edita uma turma!", tags = "Turma")
    @PutMapping("/{idProfessor}/{id}")
    public ResponseEntity<Void> editar(@RequestBody @Valid Turma turmaAtualizada, @PathVariable int idProfessor, @PathVariable int id) {
        this.turmaService.atualizar(turmaAtualizada, id, idProfessor);
        return status(200).build();
    }

    @Operation(summary = "Desativar", description = "Método que seta o status da turma como desativado!", tags = "Turma")
    @PostMapping("/desativar/{idProfessor}/{id}")
    public ResponseEntity<Turma> desativar(@PathVariable int id, @PathVariable int idProfessor){
        return status(200).body(this.turmaService.desativar(id, idProfessor));
    }


}
