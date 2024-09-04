package school.sptech.apicodando.service.muralService.dto;

import lombok.Data;
import school.sptech.apicodando.service.turmaService.dto.TurmaListagemDTO;

@Data
public class MuralListagemDTO {


    private Integer idMural;

    private String mensagem;

    private TurmaListagemDTO turma;


}
