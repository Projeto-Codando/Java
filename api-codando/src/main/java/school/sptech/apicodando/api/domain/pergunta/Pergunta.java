package school.sptech.apicodando.api.domain.pergunta;

import jakarta.persistence.*;
import lombok.Data;
import school.sptech.apicodando.api.domain.aula.Aula;
import school.sptech.apicodando.api.domain.resposta.Resposta;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Pergunta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String texto;
    private Integer contador;

    @ManyToOne
    private Aula aula;


    @OneToMany(mappedBy = "pergunta")
    private List<Resposta> respostas;

    public Pergunta() {
        this.respostas = new ArrayList<>();
        this.contador = 0;
    }
}
