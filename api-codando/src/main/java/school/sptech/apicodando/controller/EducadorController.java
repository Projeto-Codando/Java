package school.sptech.apicodando.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import school.sptech.apicodando.service.alunoService.dto.dtoAuthAluno.AlunoLoginDTO;
import school.sptech.apicodando.service.alunoService.dto.dtoAuthAluno.AlunoTokenDto;
import school.sptech.apicodando.service.educadorService.EducadorService;
import school.sptech.apicodando.service.educadorService.dto.EducadorCadastroDTO;
import school.sptech.apicodando.service.educadorService.dto.EducadorListagemDTO;
import school.sptech.apicodando.domain.educador.repository.EducadorRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.apicodando.mapper.EducadorMapper;
import school.sptech.apicodando.domain.educador.Educador;

import jakarta.validation.Valid;
import school.sptech.apicodando.service.educadorService.dto.EducadorLoginDTO;
import school.sptech.apicodando.service.educadorService.dto.dtoEducador.EducadorTokenDto;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.ResponseEntity.notFound;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/educadores")
public class EducadorController {
//    @Autowired
//    private EducadorRepository educadorRepository;
    @Autowired
    private EducadorService educadorService;



//    @PostMapping
//    public ResponseEntity<EducadorListagemDTO> criar(@RequestBody @Valid EducadorCadastroDTO novoEducador) {
//        Educador educador = EducadorMapper.toEntity(novoEducador);
//        Educador educadorSalvo = educadorRepository.save(educador);
//        EducadorListagemDTO listagemDto = EducadorMapper.toDto(educadorSalvo);
//        return ResponseEntity.status(201).body(listagemDto);
//    }


    @PostMapping
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<Void> criar(@RequestBody @Valid EducadorCadastroDTO novoEducador) {
        this.educadorService.criar(novoEducador);
        return ResponseEntity.status(201).build();
    }

    @PostMapping("/login")
    public ResponseEntity<EducadorTokenDto> login(@RequestBody EducadorLoginDTO usuarioLoginDto) {
        EducadorTokenDto usuarioTokenDto = this.educadorService.autenticar(usuarioLoginDto);

        return ResponseEntity.status(200).body(usuarioTokenDto);
    }



    @GetMapping("/{id}")
    public ResponseEntity<EducadorListagemDTO> buscaPorId(@PathVariable int id) {
        Optional<Educador> educadorOpt = educadorService.listarUmPorId(id);
        if (educadorOpt.isEmpty()) {
            return ResponseEntity.status(404).build();
        }
        EducadorListagemDTO dto = EducadorMapper.toDto(educadorOpt.get());
        return ResponseEntity.status(200).body(dto);
    }

    @GetMapping
    public ResponseEntity<List<EducadorListagemDTO>> listar() {
        List<Educador> educadores = educadorService.listarTodos();
        if (educadores.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        List<EducadorListagemDTO> listaAuxiliar = EducadorMapper.toDto(educadores);
        return ResponseEntity.status(200).body(listaAuxiliar);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir (@PathVariable @Valid int id){
        if (educadorService.existePorId(id)){
            educadorService.excluir(id);
            return ok().build();
        } else {
            return notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizar(@PathVariable("id") @Valid int id,
                                    @RequestBody @Valid Educador educadorAlterado) {
        if (educadorService.existePorId(id)) {
            educadorService.atualizar(educadorAlterado, id);
            return ok().build();
        }
            return notFound().build();
    }



}
