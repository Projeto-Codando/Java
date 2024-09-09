package school.sptech.apicodando.service.alunoService.dto.dtoAuthAluno;

import lombok.Data;
import school.sptech.apicodando.service.alunoService.dto.AlunoListagemDTO;

import java.util.List;

@Data
public class AlunoTokenDto {

    private Integer userId;
    private String nome;
    private String apelido;
    private String token;
    private AlunoListagemDTO alunoListagemDTO;

    @Data
    public static class AlunoListagemDTO {

        private Integer idAluno;
        private String nome;
        private String sobrenome;
        private String apelido;
        private Boolean status;
        private Integer moedas;
        private String idTurma;
        private int idAvatar;
        private List<AvatarListagemDTO> avatares;

        @Data
        public static class AvatarListagemDTO {
            private Integer idAvatar;
            private String descricao;
            private Integer preco;
            private String imagemURL;

            public void setPreço(int preco) {
                this.preco = preco;
            }

            public void setId(int id) {
                this.idAvatar = id;
            }
        }
    }
}
