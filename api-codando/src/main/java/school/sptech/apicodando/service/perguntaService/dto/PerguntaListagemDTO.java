package school.sptech.apicodando.service.perguntaService.dto;

import lombok.Data;
import school.sptech.apicodando.api.domain.resposta.Resposta;
import school.sptech.apicodando.service.respostaService.dto.RespostaListagemDTO;

import java.util.List;

@Data
public class PerguntaListagemDTO {

    private Integer idPergunta;
    private String texto;
    private Integer idAula;
    private List<RespostaListagemDTO> respostas;

}
