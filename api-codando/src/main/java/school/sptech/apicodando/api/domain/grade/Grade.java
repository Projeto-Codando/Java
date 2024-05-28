package school.sptech.apicodando.api.domain.grade;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import school.sptech.apicodando.api.domain.modulo.Modulo;
import school.sptech.apicodando.api.domain.tema.Tema;
import school.sptech.apicodando.api.domain.turma.Turma;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Data
public class Grade {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    protected UUID idGrade;

    @ManyToOne
    @JoinColumn(name = "idTurma")
    protected Turma fkTurma;

    @OneToMany(mappedBy = "grade")
    protected List<Modulo> modulos;

    public Grade() {
        this.modulos = new ArrayList<>();
    }
}
