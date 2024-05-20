package school.sptech.apicodando.service.gradeService.dto;

import lombok.Data;
import school.sptech.apicodando.api.domain.turma.Turma;
import school.sptech.apicodando.service.turmaService.dto.TurmaListagemDTO;

import java.util.List;
@Data
public class GradeListagemDto {
    private Integer idGrade;
    private List<ModuloListagemGradeDto> modulo;
    private TurmaListagemDTO turma;
@Data
    public static class TurmaListagemDTO {
        private Integer idTurma;
        private String nome;
        private String senha;
        private EscolaridadeListagemDTO fkEscolaridade;
        private EducadorListagemDTO fkEducador;
        private List<AlunoListagemDTO> alunos;
        private boolean statusTurma;

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

            public String getStatus() {
                return status ? "Ativo" : "Inativo";
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
    @Data
    public static class ModuloListagemGradeDto {
        private Integer idModulo;
        private String nome;
        private List<TemaListagemGradeDto> temas;
        @Data
        public static class TemaListagemGradeDto {
            private Integer idTema;
            private String nome;
            private List<AulaListagemGradeDto> aulas;
            @Data
            public static class AulaListagemGradeDto {
                private Integer id;
                private String titulo;
                private String descricao;
                private Integer nivelDificuldade;
                private Integer pontuacaoMaxima;
            }
        }
    }
}
