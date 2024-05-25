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

//    @NotBlank
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;
    protected String titulo;
    protected String descricao;
    protected Integer nivelDificuldade;
    protected Integer pontuacaoMaxima;
    @ManyToOne
    private Tema tema;

    public Aula() {
    }
}
