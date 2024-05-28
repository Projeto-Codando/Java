package school.sptech.apicodando.api.domain.turma;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import school.sptech.apicodando.api.domain.aluno.Aluno;
import school.sptech.apicodando.api.domain.educador.Educador;
import school.sptech.apicodando.api.domain.escolaridade.Escolaridade;
import school.sptech.apicodando.api.domain.grade.Grade;

import java.util.List;
import java.util.UUID;

@Entity
@Data
public class Turma {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    protected UUID idTurma;

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

    @OneToMany(mappedBy = "fkTurma")
//    @JoinColumn(name = "idGrade")
    protected List<Grade> grade;

    protected boolean statusTurma;

    @OneToMany(mappedBy = "turma")
    protected List<Aluno> alunos;

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
