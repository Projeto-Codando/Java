package school.sptech.apicodando.api.domain.pergunta;

import jakarta.persistence.*;
import jdk.dynalink.linker.LinkerServices;
import lombok.Data;
import school.sptech.apicodando.api.domain.aula.Aula;
import school.sptech.apicodando.api.domain.quiz.Quiz;
import school.sptech.apicodando.api.domain.resposta.Resposta;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Pergunta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPergunta;
    private String texto;

    @ManyToOne
    private Aula aula;


    @OneToMany(mappedBy = "pergunta")
    private List<Resposta> respostas;

    public Pergunta() {
        respostas = new ArrayList<>();
    }
}
