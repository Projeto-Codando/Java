package school.sptech.apicodando.service.respostaService.dto;

import jakarta.persistence.ManyToOne;
import lombok.Data;
import school.sptech.apicodando.api.domain.pergunta.Pergunta;

@Data
public class RespostaCadastroDTO {

    private String texto;
    private Boolean correta;
    private Integer idPergunta;

}
