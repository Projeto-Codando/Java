package school.sptech.apicodando.service.mensagemService.dto;

import lombok.Data;

@Data
public class MensagemCadastroDTO {

    private String texto;
    private Integer idMural;
    private Integer idEducador;
}
