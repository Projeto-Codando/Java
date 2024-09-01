package school.sptech.apicodando.api.domain.pergunta;

import jakarta.persistence.*;
import jdk.dynalink.linker.LinkerServices;
import lombok.Data;
import school.sptech.apicodando.api.domain.quiz.Quiz;
import school.sptech.apicodando.api.domain.resposta.Resposta;

import java.util.List;

@Data
@Entity
public class Pergunta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPergunta;

    @ManyToOne
    @JoinColumn(name = "idQuiz")
    private Quiz quiz;

    private String texto;

    @OneToMany(mappedBy = "pergunta")
    private List<Resposta> respostas;
}
