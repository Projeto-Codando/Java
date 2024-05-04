package school.sptech.apicodando.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.apicodando.service.alunoService.AlunoService;
import school.sptech.apicodando.service.alunoService.dto.AlunoCadastroDTO;
import school.sptech.apicodando.service.alunoService.dto.AlunoListagemDTO;
import school.sptech.apicodando.mapper.AlunoMapper;
import school.sptech.apicodando.domain.aluno.Aluno;
import jakarta.validation.Valid;
import school.sptech.apicodando.service.alunoService.dto.dtoAuthAluno.AlunoLoginDTO;
import school.sptech.apicodando.service.alunoService.dto.dtoAuthAluno.AlunoTokenDto;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.ResponseEntity.*;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

//    @Autowired
//    private AlunoRepository alunoRepository;
    @Autowired
    private AlunoService alunoService;

    @PostMapping
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<Void> criar(@RequestBody @Valid AlunoCadastroDTO novoAluno) {
        this.alunoService.criar(novoAluno);
        return ResponseEntity.status(201).build();
    }

    @PostMapping("/login")
    public ResponseEntity<AlunoTokenDto> login(@RequestBody AlunoLoginDTO usuarioLoginDto) {
        AlunoTokenDto usuarioTokenDto = this.alunoService.autenticar(usuarioLoginDto);

        return ok(usuarioTokenDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlunoListagemDTO> buscaPorId(@PathVariable int id) {
        AlunoListagemDTO dto = AlunoMapper.toDto(alunoService.listarUmPorId(id).get());
        return ok(dto);
    }

    @GetMapping
    public ResponseEntity<List<AlunoListagemDTO>> listar() {
        List<Aluno> alunos = alunoService.listarTodos();
        List<AlunoListagemDTO> listaAuxiliar = AlunoMapper.toDto(alunos);
        return ok(listaAuxiliar);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable @Valid int id) {
        alunoService.excluir(id);
        return ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizar(@PathVariable("id") @Valid int id,
                                          @RequestBody @Valid Aluno alunoAlterado) {
        alunoService.atualizar(alunoAlterado, id);
        return ResponseEntity.noContent().build();
    }

//    @GetMapping("/ordenarAZ")
//    public ResponseEntity


}
