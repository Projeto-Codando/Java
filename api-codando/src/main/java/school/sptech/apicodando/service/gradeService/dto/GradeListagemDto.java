package school.sptech.apicodando.service.gradeService.dto;

import lombok.Data;
import school.sptech.apicodando.api.domain.turma.Turma;
import school.sptech.apicodando.service.moduloService.dto.ModuloListagemDTO;
import school.sptech.apicodando.service.turmaService.dto.TurmaListagemDTO;

import java.util.ArrayList;
import java.util.List;
@Data
public class GradeListagemDto {
    private Integer idGrade;
    private Integer idTurma;
    private List<ModuloListagemDTO> modulo;

    public GradeListagemDto(){
        this.modulo = new ArrayList<>();
    }
//    @Data
//    public static class TurmaListagemDTO {
//        private Integer idTurma;
//    }

}
