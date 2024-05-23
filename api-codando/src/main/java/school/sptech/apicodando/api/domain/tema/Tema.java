package school.sptech.apicodando.api.domain.tema;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import school.sptech.apicodando.api.domain.aula.Aula;
import school.sptech.apicodando.api.domain.modulo.Modulo;

import java.util.List;

@Entity
@Data
public class Tema {

//    @NotBlank
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer idTema;
//    @NotBlank
//    @Size(min = 3, max = 255)
//    @NotNull
    protected String nome;

    @OneToMany(mappedBy = "tema")
    private List<Aula> aulas;

    @ManyToOne
    @JoinColumn(name = "idModulo")
    private Modulo modulo;

    public Tema() {
    }
}
