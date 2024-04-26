package school.sptech.apicodando.controller;

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
import school.sptech.apicodando.domain.aluno.repository.AlunoRepository;
import jakarta.validation.Valid;
import school.sptech.apicodando.service.autenticacao.dto.AlunoLoginDTO;
import school.sptech.apicodando.service.autenticacao.dto.AlunoTokenDto;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.ResponseEntity.notFound;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    private AlunoRepository alunoRepository;
    @Autowired
    private AlunoService alunoService;

    @PostMapping
//    @SecurityRequirement(name = "Bearer")

    public ResponseEntity<Void> criar(@RequestBody @Valid AlunoCadastroDTO novoAluno) {
        this.alunoService.criar(novoAluno);
        return ResponseEntity.status(201).build();
    }

    @PostMapping("/login")
    public ResponseEntity<AlunoTokenDto> login(@RequestBody AlunoLoginDTO usuarioLoginDto) {
        AlunoTokenDto usuarioTokenDto = this.alunoService.autenticar(usuarioLoginDto);

        return ResponseEntity.status(200).body(usuarioTokenDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlunoListagemDTO> buscaPorId(@PathVariable int id) {
        Optional<Aluno> alunoOpt = alunoService.listarUmPorId(id);
        if (alunoOpt.isEmpty()) {
            return ResponseEntity.status(404).build();
        }
        AlunoListagemDTO dto = AlunoMapper.toDto(alunoOpt.get());
        return ResponseEntity.status(200).body(dto);
    }

    @GetMapping
    public ResponseEntity<List<AlunoListagemDTO>> listar() {
        List<Aluno> alunos = alunoService.listarTodos() ;
        if (alunos.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        List<AlunoListagemDTO> listaAuxiliar = AlunoMapper.toDto(alunos);
        return ResponseEntity.status(200).body(listaAuxiliar);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir (@PathVariable @Valid int id){
        if (alunoRepository.existsById(id)){
            alunoService.excluir(id);
            return ok().build();
        } else {
            return notFound().build();
        }
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
