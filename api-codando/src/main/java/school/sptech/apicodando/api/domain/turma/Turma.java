package school.sptech.apicodando.api.domain.turma;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import school.sptech.apicodando.api.domain.aluno.Aluno;
import school.sptech.apicodando.api.domain.educador.Educador;
import school.sptech.apicodando.api.domain.escolaridade.Escolaridade;

import java.util.List;

@Entity
@Data
public class Turma {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer idTurma;
    @NotBlank
    @Size(min = 3, max = 255)
    @NotNull
    protected String nome;
    @NotBlank
    @Size(min = 3, max = 255)
    @NotNull
    protected String senha;
    @ManyToOne
    @JoinColumn(name = "idEscolaridade")
    protected Escolaridade Escolaridade;
    @ManyToOne
    protected Educador fkEducador;

    protected boolean statusTurma;

    @OneToMany(mappedBy = "turma")
    protected List<Aluno> alunos;

    public Turma() {
        statusTurma = true;
    }

    public void setEscolaridade(Escolaridade escolaridade) {
        Escolaridade = escolaridade;
    }
}
