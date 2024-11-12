package school.sptech.apicodando.api.domain.pergunta;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
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
    @Column(length = 1000)
    @Size(max = 2000)
    private String texto;
    private Integer contador;
    private Integer tentativasIncorretas;


    @ManyToOne
    private Aula aula;


    @OneToMany(mappedBy = "pergunta")
    private List<Resposta> respostas;

    public Pergunta() {
        this.respostas = new ArrayList<>();
        this.contador = 0;
        this.tentativasIncorretas = 0;
    }

    public Pergunta(String texto, Aula aula) {
        this.texto = texto;
        this.aula = aula;
        this.contador = 0;
        this.tentativasIncorretas = 0;
    }
}
