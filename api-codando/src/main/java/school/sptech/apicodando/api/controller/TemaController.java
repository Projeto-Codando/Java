package school.sptech.apicodando.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.apicodando.api.domain.tema.Tema;
import school.sptech.apicodando.api.mapper.TemaMapper;
import school.sptech.apicodando.service.moduloService.ModuloService;
import school.sptech.apicodando.service.temaService.TemaService;
import school.sptech.apicodando.service.temaService.dto.TemaCadastroDTO;
import school.sptech.apicodando.service.temaService.dto.TemaListagemDTO;

import java.net.URI;
import java.util.List;

import static org.springframework.web.servlet.function.ServerResponse.created;

@RestController
@RequestMapping("/temas")
@SecurityRequirement(name = "bearerAuth")
@RequiredArgsConstructor
public class TemaController {

    public final TemaService temaService;
    public final ModuloService moduloService;

    @Operation(summary = "Listar",description = "Listar todas temas!",tags = "Tema")
    @GetMapping
    public ResponseEntity<List<TemaListagemDTO>> listar(){
        return ResponseEntity.ok(temaService.listar());
    }

    @Operation(summary = "Cadastrar",description = "Método para cadastrar um tema!",tags = "Tema")
    @PostMapping()
    public ResponseEntity<TemaListagemDTO> criar(@RequestBody TemaCadastroDTO dto){
        TemaListagemDTO novoTema = TemaMapper.toDto(temaService.criar(dto, dto.getModuloId()));
        URI location = URI.create(String.format("/%S", dto.getModuloId()));
        return ResponseEntity.created(location).body(novoTema);
    }


}
