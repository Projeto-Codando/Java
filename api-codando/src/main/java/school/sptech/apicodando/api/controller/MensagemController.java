package school.sptech.apicodando.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.apicodando.api.mapper.MensagemMapper;
import school.sptech.apicodando.api.mapper.ModuloMapper;
import school.sptech.apicodando.service.mensagemService.MensagemService;
import school.sptech.apicodando.service.mensagemService.dto.MensagemCadastroDTO;
import school.sptech.apicodando.service.mensagemService.dto.MensagemListagemDTO;
import school.sptech.apicodando.service.moduloService.dto.ModuloCadastroDTO;
import school.sptech.apicodando.service.moduloService.dto.ModuloListagemDTO;

import java.net.URI;
import java.util.List;

import static org.springframework.web.servlet.function.ServerResponse.status;

@RestController
@RequestMapping("/mensagens")
@SecurityRequirement(name = "bearerAuth")
@RequiredArgsConstructor
public class MensagemController {

    private final MensagemService mensagemService;

    @Operation(summary = "Cadastrar", description = "Método que cadastra uma mensagem!", tags = "Mensagem")
    @PostMapping
    public ResponseEntity<MensagemListagemDTO> criar(@RequestBody @Valid MensagemCadastroDTO moduloCadastro) {
        MensagemListagemDTO mensagemListagemDTO = MensagemMapper.toDto(mensagemService.criar(moduloCadastro));
        URI location = URI.create(String.format("/%d", mensagemListagemDTO.getIdMensagem()));
        return ResponseEntity.created(location).body(mensagemListagemDTO);
    }

    @Operation(summary = "Listar", description = "Método que lista mensagens pelo id da Turma!", tags = "Mensagem")
    @GetMapping("/turma/{idTurma}")
    public ResponseEntity<List<MensagemListagemDTO>> listarPorTurma(@PathVariable int idTurma) {
        return ResponseEntity.ok(mensagemService.exibirPorTurma(idTurma));
    }

}
