package school.sptech.apicodando.api.domain.turma;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import school.sptech.apicodando.api.domain.aluno.Aluno;
import school.sptech.apicodando.api.domain.educador.Educador;
import school.sptech.apicodando.api.domain.escolaridade.Escolaridade;
import school.sptech.apicodando.api.domain.grade.Grade;
import school.sptech.apicodando.api.domain.modulo.Modulo;
import school.sptech.apicodando.api.domain.mural.Mural;

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
    protected Escolaridade escolaridade;

    @ManyToOne
    @JoinColumn(name = "idEducador")
    protected Educador educador;

//    @ManyToOne
//    @JoinColumn(name = "idModulo")
//    protected Modulo modulo;

    protected boolean statusTurma;

    @OneToMany(mappedBy = "turma")
    protected List<Aluno> alunos;
    @OneToMany(mappedBy = "turma")
    protected List<Mural> murais;

    public Turma() {
        statusTurma = true;
    }

    public void setEscolaridade(Escolaridade escolaridade) {
        this.escolaridade = escolaridade;
    }

    public void setEducador(Educador educador) {
        this.educador = educador;
    }
}
