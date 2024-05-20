package school.sptech.apicodando.service.gradeService.dto;

import lombok.Data;
import school.sptech.apicodando.api.domain.turma.Turma;

import java.util.List;
@Data
public class GradeCriacaoDto {
    private Integer idGrade;
    private List<ModuloListagemGradeDto> modulo;
    private Turma turma;
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
