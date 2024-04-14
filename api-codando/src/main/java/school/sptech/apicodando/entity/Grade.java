package school.sptech.apicodando.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Grade {

    @NotBlank
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer idGrade;
//    @ManyToOne
//    @JoinColumn(name ="idTurma")
//    protected Integer fkTurma;
//    @ManyToOne
//    @JoinColumn(name ="idTema")
//    protected Integer fkTema;

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
