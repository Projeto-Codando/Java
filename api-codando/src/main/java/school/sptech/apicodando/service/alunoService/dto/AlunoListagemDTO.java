package school.sptech.apicodando.service.alunoService.dto;

import lombok.Data;
import school.sptech.apicodando.service.respostaService.dto.RespostaListagemDTO;

import java.util.List;

@Data
public class AlunoListagemDTO {

    private Integer idAluno;
    private String nome;
    private String sobrenome;
    private String apelido;
    private Boolean status;
    private Integer moedas;
    private String idTurma;
    private int idAvatar;
    private List<AvatarListagemDTO> avatares;
    private List<RespostaListagemDto> idRespostas;

    public String getNomeCompleto(){
        return getNome() + " " + getSobrenome();
    }

    @Data
    public static class AvatarListagemDTO {
        private Integer id;
        private String descricao;
        private Integer preco;
        private String imagemURL;
    }

    @Data
    public static class RespostaListagemDto {
        private Integer idResposta;
        private String texto;
        private Boolean correta;
        private Integer contador;
        private Integer idPergunta;
//        private List<Integer> idAlunos;
    }

}
