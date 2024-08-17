package school.sptech.apicodando.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import school.sptech.apicodando.service.educadorService.EducadorService;
import school.sptech.apicodando.service.educadorService.dto.EducadorCadastroDTO;
import school.sptech.apicodando.service.educadorService.dto.EducadorListagemDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.apicodando.api.mapper.EducadorMapper;
import school.sptech.apicodando.api.domain.educador.Educador;

import jakarta.validation.Valid;
import school.sptech.apicodando.service.educadorService.dto.EducadorLoginDTO;
import school.sptech.apicodando.service.educadorService.dto.dtoEducador.EducadorTokenDto;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.ResponseEntity.*;

@RestController
@RequestMapping("/educadores")
@SecurityRequirement(name = "bearerAuth")
@RequiredArgsConstructor
public class EducadorController {

    private final EducadorService educadorService;

    @Operation(summary = "Cadastrar", description = "Método que cadastra o educador!", tags = "Educador")
    @PostMapping
    public ResponseEntity<EducadorCadastroDTO> criar(@RequestBody @Valid EducadorCadastroDTO novoEducador) {
        this.educadorService.criar(novoEducador);
        return created(null).body(novoEducador);
    }

    @Operation(summary = "Login", description = "Método realiza o login do educador!", tags = "Educador")
    @PostMapping("/login")
    public ResponseEntity<EducadorTokenDto> login(@RequestBody EducadorLoginDTO usuarioLoginDto) {
        EducadorTokenDto usuarioTokenDto = this.educadorService.autenticar(usuarioLoginDto);
        return ok(usuarioTokenDto);
    }

    @Operation(summary = "Busca por ID", description = "Método que retorna o educador buscado por ID!", tags = "Educador")
    @GetMapping("/{id}")
    public ResponseEntity<EducadorListagemDTO> buscaPorId(@PathVariable int id) {
        Optional<Educador> educadorOpt = educadorService.listarUmPorId(id);
        EducadorListagemDTO dto = EducadorMapper.toDto(educadorOpt.get());
        return ok(dto);
    }

    @Operation(summary = "Listar", description = "Método que retorna todos os educadores!", tags = "Educador")
    @GetMapping
    public ResponseEntity<List<EducadorListagemDTO>> listar() {
        return ok(EducadorMapper.toDto(educadorService.listarTodos()));
    }

    @Operation(summary = "Excluir", description = "Método que apaga um educador!", tags = "Educador")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable int id) {
        educadorService.excluir(id);
        return ok().build();
    }

    @Operation(summary = "Atualizar", description = "Método que atualiza um educador!", tags = "Educador")
    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizar(@PathVariable int id,
                                          @RequestBody @Valid EducadorCadastroDTO educadorAlterado) {
        educadorService.atualizar(educadorAlterado, id);
        return ok().build();
    }


}
