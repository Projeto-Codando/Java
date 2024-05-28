package school.sptech.apicodando.api.domain.aula;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import school.sptech.apicodando.api.domain.tema.Tema;

import java.util.UUID;

@Entity
@Data
public class Aula {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    protected UUID id;
    protected String titulo;
    protected String descricao;
    protected Integer nivelDificuldade;
    protected Integer pontuacaoMaxima;
    @ManyToOne
    private Tema tema;

    public Aula() {
    }
}
