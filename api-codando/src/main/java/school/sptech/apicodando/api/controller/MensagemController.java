package school.sptech.apicodando.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import school.sptech.apicodando.api.mapper.MensagemMapper;
import school.sptech.apicodando.api.mapper.ModuloMapper;
import school.sptech.apicodando.service.mensagemService.MensagemService;
import school.sptech.apicodando.service.mensagemService.dto.MensagemCadastroDTO;
import school.sptech.apicodando.service.mensagemService.dto.MensagemListagemDTO;
import school.sptech.apicodando.service.moduloService.dto.ModuloCadastroDTO;
import school.sptech.apicodando.service.moduloService.dto.ModuloListagemDTO;

import java.net.URI;

@RestController
@RequestMapping("/mensagens")
@SecurityRequirement(name = "bearerAuth")
@RequiredArgsConstructor
public class MensagemController {

    private final MensagemService mensagemService;

    @Operation(summary = "Cadastrar", description = "Método que cadastra uma mensagem!", tags = "Mensagem")
    @PostMapping
    public ResponseEntity<MensagemListagemDTO> criar(@RequestBody MensagemCadastroDTO moduloCadastro) {
        MensagemListagemDTO mensagemListagemDTO = MensagemMapper.toDto(mensagemService.criar(moduloCadastro));
        URI location = URI.create(String.format("/%d", mensagemListagemDTO.getIdMensagem()));
        return ResponseEntity.created(location).body(mensagemListagemDTO);
    }

}
