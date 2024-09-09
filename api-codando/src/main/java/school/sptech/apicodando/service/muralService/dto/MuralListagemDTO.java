package school.sptech.apicodando.service.muralService.dto;

import lombok.Data;
import school.sptech.apicodando.service.mensagemService.dto.MensagemListagemDTO;

import java.util.List;

@Data
public class MuralListagemDTO {


    private Integer idMural;

    private List<MensagemListagemDTO> mensagens;



}
