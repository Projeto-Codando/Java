package school.sptech.apicodando.service.moduloService.dto;

import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;
import school.sptech.apicodando.api.domain.grade.Grade;
import school.sptech.apicodando.api.domain.tema.Tema;
import school.sptech.apicodando.service.gradeService.dto.GradeListagemDto;
import school.sptech.apicodando.service.temaService.dto.TemaListagemDTO;
import school.sptech.apicodando.service.turmaService.dto.TurmaListagemDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
public class ModuloListagemDTO {

    protected UUID idModulo;
    protected String nome;
//    protected Integer idGrade;

    private List<TemaListagemDTO> temas;

    public ModuloListagemDTO() {
        this.temas = new ArrayList<>();
    }

//    @Data
//    public static class GradeListagemDTO {
//        private Integer idGrade;
//        protected TurmaListagemDTO turma;
//
//    }
}
//public class ModuloListagemDTO {
//
//    protected Integer idModulo;
//    protected String nome;
//
//    private List<TemaListagemDTO> temas;
//    protected GradeListagemDTO grade;
//
//    public ModuloListagemDTO() {
//        this.temas = new ArrayList<>();
//    }
//
//    @Data
//    public static class GradeListagemDTO {
//        private Integer idGrade;
//        protected TurmaListagemDTO turma;
//
//        @Data
//        public static class TurmaListagemDTO {
//            private Integer idTurma;
//            private String nome;
//            private List<AlunoListagemDTO> alunos;
//
//            @Data
//            public static class AlunoListagemDTO {
//                private Integer idAluno;
//                private String nome;
//                private String sobrenome;
//                private String apelido;
//                private Integer moedas;
//                private Boolean status;
//            }
//        }
//
//    }
//
//}
