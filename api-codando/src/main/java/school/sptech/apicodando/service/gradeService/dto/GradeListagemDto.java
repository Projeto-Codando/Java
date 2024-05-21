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
    private List<ModuloListagemDTO> modulo;
    private TurmaListagemDTO turma;

    public GradeListagemDto(){
        this.modulo = new ArrayList<>();
    }

    public static class TurmaListagemDTO {
        private Integer idTurma;
    }

}
