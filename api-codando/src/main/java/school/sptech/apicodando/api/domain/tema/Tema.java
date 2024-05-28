package school.sptech.apicodando.api.domain.tema;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import school.sptech.apicodando.api.domain.aula.Aula;
import school.sptech.apicodando.api.domain.modulo.Modulo;

import java.util.List;
import java.util.UUID;

@Entity
@Data
public class Tema {

    //    @NotBlank
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    protected UUID idTema;

    protected String nome;

    @OneToMany(mappedBy = "tema")
    private List<Aula> aulas;

    @ManyToOne
    @JoinColumn(name = "idModulo")
    private Modulo modulo;

    public Tema() {
    }
}
