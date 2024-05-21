package school.sptech.apicodando.service.moduloService.dto;

import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;
import school.sptech.apicodando.api.domain.grade.Grade;
import school.sptech.apicodando.api.domain.tema.Tema;
import school.sptech.apicodando.service.temaService.dto.TemaListagemDTO;

import java.util.List;
@Data
public class ModuloListagemDTO {

    protected Integer idModulo;
    protected String nome;

    private List<TemaListagemDTO> temas;
    protected GradeListagemDTO grade;

    @Data
    public static class GradeListagemDTO {
        private Integer idGrade;
    }

}
