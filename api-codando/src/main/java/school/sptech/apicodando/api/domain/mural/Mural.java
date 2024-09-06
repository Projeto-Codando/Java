package school.sptech.apicodando.api.domain.mural;

import jakarta.persistence.*;
import jdk.jfr.Enabled;
import lombok.Data;
import school.sptech.apicodando.api.domain.mensagem.Mensagem;
import school.sptech.apicodando.api.domain.turma.Turma;

import java.util.List;

@Entity
@Data
public class Mural {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idMural;

//    @OneToOne
//    private Turma turma;
//
//    @OneToMany(mappedBy = "mural")
//    private List<Mensagem> mensagens;
}
