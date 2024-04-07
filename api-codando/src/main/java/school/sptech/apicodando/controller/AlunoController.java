package school.sptech.apicodando.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.apicodando.record.DTOs.aluno.AlunoCadastroDTO;
import school.sptech.apicodando.record.DTOs.aluno.AlunoListagemDTO;
import school.sptech.apicodando.mapper.AlunoMapper;
import school.sptech.apicodando.entity.Aluno;
import school.sptech.apicodando.repository.AlunoRepository;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    private AlunoRepository alunoRepository;

    @PostMapping
    public ResponseEntity<AlunoListagemDTO> criar(@RequestBody @Valid AlunoCadastroDTO novoAluno) {
        Aluno aluno = AlunoMapper.toEntity(novoAluno);
        Aluno alunoSalvo = alunoRepository.save(aluno);
        AlunoListagemDTO listagemDto = AlunoMapper.toDto(alunoSalvo);
        return ResponseEntity.status(201).body(listagemDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlunoListagemDTO> buscaPorId(@PathVariable int id) {
        Optional<Aluno> alunoOpt = alunoRepository.findById(id);
        if (alunoOpt.isEmpty()) {
            return ResponseEntity.status(404).build();
        }
        AlunoListagemDTO dto = AlunoMapper.toDto(alunoOpt.get());
        return ResponseEntity.status(200).body(dto);
    }

    @GetMapping
    public ResponseEntity<List<AlunoListagemDTO>> listar() {
        List<Aluno> alunos = alunoRepository.findAll();
        if (alunos.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        List<AlunoListagemDTO> listaAuxiliar = AlunoMapper.toDto(alunos);
        return ResponseEntity.status(200).body(listaAuxiliar);
    }
}
