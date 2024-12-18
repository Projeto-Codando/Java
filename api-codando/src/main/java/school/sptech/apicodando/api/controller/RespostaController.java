package school.sptech.apicodando.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.apicodando.api.domain.resposta.Resposta;
import school.sptech.apicodando.api.mapper.RespostaMapper;
import school.sptech.apicodando.service.respostaService.RespostaService;
import school.sptech.apicodando.service.respostaService.dto.RespostaCadastroDTO;
import school.sptech.apicodando.service.respostaService.dto.RespostaListagemDTO;

import java.util.List;

@RestController
@RequestMapping("/api/respostas")
@RequiredArgsConstructor
@SecurityRequirement(name = "Bearer")
public class RespostaController {

    private final RespostaService respostaService;

    @Operation(summary = "Cadastrar", description = "Método que cadastra uma resposta!", tags = "Resposta")
    @PostMapping
    public ResponseEntity<RespostaListagemDTO> criar (@RequestBody RespostaCadastroDTO respostaCadastroDTO) {
        Resposta resposta = respostaService.criar(respostaCadastroDTO);
        return ResponseEntity.ok(RespostaMapper.toDto(resposta));
    }

    @Operation(summary = "Buscar por ID", description = "Método que busca uma resposta por ID!", tags = "Resposta")
    @GetMapping("/{id}")
    public ResponseEntity<RespostaListagemDTO> buscarPorId (@PathVariable Integer id) {
        return ResponseEntity.ok(RespostaMapper.toDto(respostaService.buscarPorId(id)));
    }

    @Operation(summary = "Listar", description = "Método que lista respostas por ID da pergunta!", tags = "Resposta")
    @GetMapping("/perguntas/{idPergunta}")
    public ResponseEntity<List<RespostaListagemDTO>> buscarPorIdPergunta (@PathVariable Integer idPergunta) {
        return ResponseEntity.ok(RespostaMapper.toDto(respostaService.buscarPorIdPergunta(idPergunta)));
    }

//    @Operation(summary = "Listar", description = "Método que lista o aluno que mais errou as perguntas", tags = "Resposta")
//    @GetMapping("/erros")
//    public ResponseEntity<List<RespostaListagemDTO>> listarAlunoQueMaisErrou () {
//        return ResponseEntity.ok(respostaService.listarAlunoQueMaisErrou());
//    }

//    @Operation(summary = "Listar", description = "Método que lista uma lista de alunos de uma turma ordenados por quantidade de respostas corretas", tags = "Resposta")
//    @GetMapping("/corretas/{idTurma}")
//    public ResponseEntity<List<RespostaListagemDTO>> listarAlunosPorRespostasCorretas (int idTurma) {
//        return ResponseEntity.ok(respostaService.listarAlunosDeUmaTurmaPorRespostasCorretas(idTurma));
//    }

    @Operation(summary = "Incrementar contador", description = "Método que incrementa o contador de uma resposta!", tags = "Resposta")
    @PutMapping("/{idResposta}/{idAluno}")
    public ResponseEntity<RespostaListagemDTO> incrementarContador (@PathVariable Integer idResposta, @PathVariable Integer idAluno) {
        return ResponseEntity.ok(respostaService.incrementarContador(idResposta, idAluno));
    }





}
