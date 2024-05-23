package school.sptech.apicodando.api.domain.grade;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import school.sptech.apicodando.api.domain.modulo.Modulo;
import school.sptech.apicodando.api.domain.tema.Tema;
import school.sptech.apicodando.api.domain.turma.Turma;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Grade {

//    @NotBlank
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer idGrade;

    @OneToOne
    @JoinColumn(name = "idTurma")
    protected Turma fkTurma;

    @OneToMany(mappedBy = "grade")
    protected List<Modulo> modulos;

    public Grade() {
        this.modulos = new ArrayList<>();
    }
}
