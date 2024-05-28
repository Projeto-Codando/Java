package school.sptech.apicodando.service.avatarService.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class AvatarListagemDTO {
    private UUID id;
    private String descricao;
    private Integer preço;
}
