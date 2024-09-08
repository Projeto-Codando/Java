package school.sptech.apicodando.api.domain.quiz;

import jakarta.persistence.*;
import lombok.Data;
import school.sptech.apicodando.api.domain.aula.Aula;
import school.sptech.apicodando.api.domain.pergunta.Pergunta;
import school.sptech.apicodando.api.domain.tema.Tema;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
 public class Quiz {
//
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idQuiz;

    private String texto;

//    @ManyToOne
//    private Aula aula;
//
//    @OneToMany(mappedBy = "quiz")
//    private List<Pergunta> pergunta;
////
////    @ManyToOne
////    @JoinColumn(name = "id")
////    private Tema tema;
//
////    public Quiz() {
////        pergunta = new ArrayList<>();
////    }

}
