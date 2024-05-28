package school.sptech.apicodando.api.domain.modulo;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import school.sptech.apicodando.api.domain.grade.Grade;
import school.sptech.apicodando.api.domain.tema.Tema;

import java.util.List;
import java.util.UUID;

@Entity
@Data
public class Modulo {


    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    protected UUID idModulo;

    protected String nome;
    @OneToMany(mappedBy = "modulo")
    private List<Tema> temas;
    @ManyToOne
    protected Grade grade;


    public Modulo() {
    }


}
