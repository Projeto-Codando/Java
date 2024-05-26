package school.sptech.apicodando.service.alunoService.dto;

import lombok.Data;
import school.sptech.apicodando.api.domain.avatar.Avatar;
import school.sptech.apicodando.service.avatarService.dto.AvatarListagemDTO;

import java.util.List;

@Data
public class AlunoListagemDTO {

    private Integer idAluno;
    private String nome;
    private String sobrenome;
    private String apelido;
    private Boolean status;
    private Integer moedas;
    private List<AvatarListagemDTO> avatares;

    @Data
    public static class AvatarListagemDTO {
        private Integer idAvatar;
        private String descricao;
        private Integer preco;
    }

}
