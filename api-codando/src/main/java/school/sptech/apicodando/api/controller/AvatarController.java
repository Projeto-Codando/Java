package school.sptech.apicodando.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.apicodando.api.domain.avatar.Avatar;
import school.sptech.apicodando.api.domain.avatar.repository.AvatarRepository;
import school.sptech.apicodando.api.mapper.AlunoMapper;
import school.sptech.apicodando.api.mapper.AvatarMapper;
import school.sptech.apicodando.service.avatarService.AvatarService;
import school.sptech.apicodando.service.avatarService.dto.AvatarListagemDTO;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RequestMapping("/avatares")
@RestController
@SecurityRequirement(name = "bearerAuth")
public class AvatarController {

    private final AvatarService service;
    @Operation(summary = "Listar", description = "Método que lista todos os avatares!", tags = "Avatar")
    @GetMapping
    public List<AvatarListagemDTO> listar() {
        return AvatarMapper.toListagem(service.listar());
    }

    @Operation(summary = "Listar", description = "Método que compra um avatar", tags = "Avatar")
    @PutMapping("/aluno/{idAluno}/avatar/{idAvatar}")
    public ResponseEntity<AvatarListagemDTO> atualizarAvatar(@PathVariable UUID idAluno, @PathVariable UUID idAvatar) {
        service.atualizarAvatar(idAluno, idAvatar);
        return ResponseEntity.ok(AvatarMapper.toListagemDTO(service.buscarPorId(idAvatar)));
    }

    @Operation(summary = "Listar", description = "Método que seta um avatar na lista do aluno", tags = "Avatar")
    @PutMapping("/aluno/{idAluno}/avatar-escolhido/{idAvatar}")
    public ResponseEntity<AvatarListagemDTO> atualizarAvatarEscolhido(@PathVariable UUID idAluno,@PathVariable UUID idAvatar) {
        service.atualizarAvatarEscolhido(idAluno, idAvatar);
        return ResponseEntity.ok(AlunoMapper.toDtoAvatarEntity(service.buscarPorId(idAvatar)));
    }
}
