package school.sptech.apicodando.service.presencaService;

import lombok.Data;

import java.util.UUID;
@Data
public class PresencaDTO {
    private UUID idAluno;
    private UUID idAula;
    private boolean presente;

    // Getters e setters
}
