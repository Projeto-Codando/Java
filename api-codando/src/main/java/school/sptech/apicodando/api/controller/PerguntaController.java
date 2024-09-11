package school.sptech.apicodando.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import school.sptech.apicodando.api.mapper.ModuloMapper;
import school.sptech.apicodando.api.mapper.PerguntaMapper;
import school.sptech.apicodando.service.moduloService.dto.ModuloCadastroDTO;
import school.sptech.apicodando.service.moduloService.dto.ModuloListagemDTO;
import school.sptech.apicodando.service.perguntaService.PerguntaService;
import school.sptech.apicodando.service.perguntaService.dto.PerguntaCadastroDTO;
import school.sptech.apicodando.service.perguntaService.dto.PerguntaListagemDTO;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/perguntas")
@SecurityRequirement(name = "bearerAuth")
public class PerguntaController {

    private final PerguntaService perguntaService;

    @Operation(summary = "Cadastrar", description = "Método que cadastra uma pergunta", tags = "Pergunta")
    @PostMapping
    public ResponseEntity<PerguntaListagemDTO> criar(@RequestBody PerguntaCadastroDTO perguntaCadastroDTO) {
        PerguntaListagemDTO perguntaDto = PerguntaMapper.toDto(perguntaService.criar(perguntaCadastroDTO));
        URI location = URI.create(String.format("/%d", perguntaDto.getIdPergunta()));
        return ResponseEntity.created(location).body(perguntaDto);
    }

    @Operation(summary = "Listar", description = "Método que lista uma pergunta por ID", tags = "Pergunta")
    @GetMapping("/{id}")
    public ResponseEntity<PerguntaListagemDTO> listarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(PerguntaMapper.toDto(perguntaService.buscarPorId(id)));
    }

    @Operation(summary = "Listar", description = "Método que lista perguntas por ID da aula", tags = "Pergunta")
    @GetMapping("/aulas/{idAula}")
    public ResponseEntity<List<PerguntaListagemDTO>> listarPorIdAula(@PathVariable Integer idAula) {
        if (perguntaService.buscarPorIdAula(idAula).isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(PerguntaMapper.toDto(perguntaService.buscarPorIdAula(idAula)));
    }

    @Operation(summary = "Deletar", description = "Método que deleta uma pergunta por ID", tags = "Pergunta")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        perguntaService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Atualizar", description = "Método que atualiza uma pergunta por ID", tags = "Pergunta")
    @PutMapping("/{id}")
    public ResponseEntity<PerguntaListagemDTO> atualizar(@PathVariable Integer id, @RequestBody PerguntaCadastroDTO perguntaCadastroDTO) {
        PerguntaListagemDTO perguntaDto = PerguntaMapper.toDto(perguntaService.atualizar(id, perguntaCadastroDTO));
        return ResponseEntity.ok(perguntaDto);
    }

}
