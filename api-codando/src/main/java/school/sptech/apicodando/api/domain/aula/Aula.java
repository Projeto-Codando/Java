package school.sptech.apicodando.api.domain.aula;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Data;
import school.sptech.apicodando.api.domain.tema.Tema;

@Entity
@Data
public class Aula {

    @NotBlank
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;
    @NotBlank
    @Size(min = 3, max = 255)
    @NotNull
    protected String titulo;
    @NotBlank
    @Size(min = 3, max = 255)
    @NotNull
    protected String descricao;
    @NotBlank
    @PositiveOrZero
    protected Integer nivelDificuldade;
    @NotBlank
    @PositiveOrZero
    protected Integer pontuacaoMaxima;
    @ManyToOne
    private Tema tema;

    public Aula() {
    }
}
