package school.sptech.apicodando.api.domain.grade;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import school.sptech.apicodando.api.domain.tema.Tema;
import school.sptech.apicodando.api.domain.turma.Turma;

@Entity
public class Grade {

    @NotBlank
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer idGrade;
    @ManyToOne
    @JoinColumn(name ="idTurma")
    protected Turma fkTurma;
    @ManyToOne
    @JoinColumn(name ="idTema")
    protected Tema fkTema;

    public Grade() {
    }

    public Integer getIdGrade() {
        return idGrade;
    }

    public void setIdGrade(Integer idGrade) {
        this.idGrade = idGrade;
    }

//    public Integer getFkTurma() {
//        return fkTurma;
//    }
//
//    public void setFkTurma(Integer fkTurma) {
//        this.fkTurma = fkTurma;
//    }
//
//    public Integer getFkTema() {
//        return fkTema;
//    }
//
//    public void setFkTema(Integer fkTema) {
//        this.fkTema = fkTema;
//    }
}
