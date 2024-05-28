package school.sptech.apicodando.service.educadorService.dto;

import lombok.Data;

import java.util.UUID;
@Data
public class EducadorListagemDTO {
    private UUID idEducador;
    private String nome;
    private String sobrenome;
    private String email;

}
