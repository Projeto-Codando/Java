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
@RequestMapping("/api/mensagens")
@SecurityRequirement(name = "bearerAuth")
@RequiredArgsConstructor
public class MensagemController {

    private final MensagemService mensagemService;

    @Operation(summary = "Cadastrar", description = "Método que cadastra uma mensagem!", tags = "Mensagem")
    @PostMapping
    public ResponseEntity<MensagemListagemDTO> criar(@RequestBody @Valid MensagemCadastroDTO moduloCadastro) {
        if (moduloCadastro == null) {
            return ResponseEntity.badRequest().build();
        }

        MensagemListagemDTO mensagemListagemDTO = MensagemMapper.toDto(mensagemService.criar(moduloCadastro));
        if (mensagemListagemDTO == null) {
            return ResponseEntity.badRequest().build();
        }
        URI location = URI.create(String.format("/%d", mensagemListagemDTO.getIdMensagem()));
        return ResponseEntity.created(location).body(mensagemListagemDTO);
    }

    @Operation(summary = "Listar", description = "Método que lista mensagens pelo id da Turma!", tags = "Mensagem")
    @GetMapping("/turma/{idTurma}")
    public ResponseEntity<List<MensagemListagemDTO>> listarPorTurma(@PathVariable int idTurma) {
        if (mensagemService.exibirPorTurma(idTurma).isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(mensagemService.exibirPorTurma(idTurma));
    }

    @Operation(summary = "Deletar", description = "Método que deleta uma mensagem pelo id!", tags = "Mensagem")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable int id) {
        mensagemService.deletar(id);
        return ResponseEntity.noContent().build();
    }

}
