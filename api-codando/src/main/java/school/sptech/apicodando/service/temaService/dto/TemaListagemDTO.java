package school.sptech.apicodando.service.temaService.dto;

import lombok.Data;
import school.sptech.apicodando.api.domain.modulo.Modulo;
import school.sptech.apicodando.service.aulaService.dto.AulaListagemDTO;

import java.util.List;
@Data
public class TemaListagemDTO {

    protected Integer idTema;
    protected String nome;

    private List<AulaListagemDTO> aulas;
    private ModuloDto modulo;

    @Data
    public static class ModuloDto {
        private Integer id;
        private String nome;
    }
}
