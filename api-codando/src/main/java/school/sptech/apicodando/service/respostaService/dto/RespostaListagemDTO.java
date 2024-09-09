package school.sptech.apicodando.service.respostaService.dto;

import lombok.Data;

@Data
public class RespostaListagemDTO {

    private Integer idResposta;
    private String texto;
    private Boolean correta;
    private Integer contador;
    private Integer idPergunta;

}
