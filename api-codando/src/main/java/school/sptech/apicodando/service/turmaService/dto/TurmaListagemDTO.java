package school.sptech.apicodando.service.turmaService.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import school.sptech.apicodando.api.domain.aluno.Aluno;
import school.sptech.apicodando.api.domain.avatar.Avatar;
import school.sptech.apicodando.api.domain.educador.Educador;
import school.sptech.apicodando.api.domain.escolaridade.Escolaridade;
import school.sptech.apicodando.api.mapper.AlunoMapper;
import school.sptech.apicodando.service.alunoService.dto.AlunoListagemDTO;
import school.sptech.apicodando.service.mensagemService.dto.MensagemListagemDTO;
import school.sptech.apicodando.service.muralService.dto.MuralListagemDTO;

import java.util.List;
@Data
@Getter
@Setter
public class TurmaListagemDTO {
    private int idTurma;
    private String nome;
    private String senha;
    private EscolaridadeListagemDTO fkEscolaridade;
    private EducadorListagemDTO fkEducador;
    private List<AlunoListagemDTO> alunos;
    private int fkModulo;
    private boolean statusTurma;
    private List<MensagemListagemDTO> mensagens;

//    public String getFkEscolaridade() {
//
//    }

    public String getStatusTurma(){
        return statusTurma ? "Ativa" : "Inativa";
    }

    @Data
    public static class AlunoListagemDTO {
        private Integer idAluno;
        private String nome;
        private String sobrenome;
        private String apelido;
        private boolean status;
        private Integer moedas;
        private Integer idAvatar;
        private List<AvatarListagemDTO> avatar;

        public String getStatus() {
            return status ? "Ativo" : "Inativo";
        }

        @Data
        public static class AvatarListagemDTO {
            private Integer idAvatar;
            private Integer preco;
            private String descricao;
            private String imagemURL;
        }
    }

    @Data
    public static class EscolaridadeListagemDTO {
        private Integer idEscolaridade;
        private String descricao;
    }

    @Data
    public static class EducadorListagemDTO {
        private Integer idEducador;
        private String nome;
        private String sobrenome;
    }
}
