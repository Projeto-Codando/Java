package school.sptech.apicodando.service.muralService.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import school.sptech.apicodando.api.domain.turma.Turma;

@Data
public class MuralCadastroDTO {

    @NotBlank
    @Size(min = 3, max = 255)
    private String mensagem;

    @ManyToOne
    private Turma turma;


}
