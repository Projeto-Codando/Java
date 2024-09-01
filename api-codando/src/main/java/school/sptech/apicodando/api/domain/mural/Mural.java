package school.sptech.apicodando.api.domain.mural;

import jakarta.persistence.*;
import jdk.jfr.Enabled;
import lombok.Data;
import school.sptech.apicodando.api.domain.turma.Turma;

@Entity
@Data
public class Mural {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idMural;

    private String mensagem;

    @ManyToOne
    private Turma turma;
}
