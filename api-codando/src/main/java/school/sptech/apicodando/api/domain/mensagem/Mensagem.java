package school.sptech.apicodando.api.domain.mensagem;


import jakarta.persistence.*;
import lombok.Data;
import school.sptech.apicodando.api.domain.turma.Turma;

import java.time.LocalDateTime;

@Entity
@Data
public class Mensagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idMensagem;

    private String texto;
    private LocalDateTime dataEnvio;

//    @ManyToOne
//    private Educador educador;

    @ManyToOne
    @JoinColumn(name = "idTurma")
    private Turma turma;

    public Mensagem() {
    }

}
