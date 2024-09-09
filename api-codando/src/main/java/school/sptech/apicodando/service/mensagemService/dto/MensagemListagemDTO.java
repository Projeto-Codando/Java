package school.sptech.apicodando.service.mensagemService.dto;

import lombok.Data;

@Data
public class MensagemListagemDTO {

    private Integer idMensagem;
    private String mensagem;
    private String dataEnvio;
//    private String educador;
    private Integer idTurma;
}
