package school.sptech.apicodando.api.domain.resposta;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
import school.sptech.apicodando.api.domain.pergunta.Pergunta;

@Entity
@Data
public class Resposta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idResposta;


    private String texto;
    private Boolean correta;
    private Integer contador;

    @ManyToOne
    private Pergunta pergunta;

}
