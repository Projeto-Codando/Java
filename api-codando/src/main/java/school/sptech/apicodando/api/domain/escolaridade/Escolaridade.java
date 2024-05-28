package school.sptech.apicodando.api.domain.escolaridade;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import school.sptech.apicodando.api.domain.aluno.Aluno;

import java.util.UUID;

@Entity
@Data
public class Escolaridade {

    @NotBlank
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    protected UUID idEscolaridade;

    protected String descricao;

}
