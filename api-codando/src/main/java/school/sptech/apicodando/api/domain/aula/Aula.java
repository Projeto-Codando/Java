package school.sptech.apicodando.api.domain.aula;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Data;
import school.sptech.apicodando.api.domain.pergunta.Pergunta;
import school.sptech.apicodando.api.domain.quiz.Quiz;
import school.sptech.apicodando.api.domain.tema.Tema;
import school.sptech.apicodando.api.domain.turma.Turma;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Aula {

//    @NotBlank
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;
    protected String titulo;
    protected String descricao;
    protected Integer nivelDificuldade;
    protected Integer pontuacaoMaxima;

    @OneToMany(mappedBy = "aula")
    protected List<Pergunta> perguntas;
    @ManyToOne
    private Tema tema;

    @ManyToOne
    private Turma turma;

//    public Aula() {
//        quiz = new ArrayList<>();
//    }
}
