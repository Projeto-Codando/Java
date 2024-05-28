package school.sptech.apicodando.service.alunoService.dto;

import lombok.Data;
import school.sptech.apicodando.api.domain.avatar.Avatar;
import school.sptech.apicodando.service.avatarService.dto.AvatarListagemDTO;

import java.util.List;
import java.util.UUID;

@Data
public class AlunoListagemDTO {

    private UUID idAluno;
    private String nome;
    private String sobrenome;
    private String apelido;
    private Boolean status;
    private Integer moedas;
    private String idTurma;
    private int idAvatar;
    private List<AvatarListagemDTO> avatares;
    public String getNomeCompleto(){
        return getNome() + " " + getSobrenome();
    }

    @Data
    public static class AvatarListagemDTO {
        private UUID idAvatar;
        private String descricao;
        private Integer preco;
    }

}
