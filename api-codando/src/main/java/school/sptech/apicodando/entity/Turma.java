package school.sptech.apicodando.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Turma {

    @NotBlank
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
    protected String sobrenome;
    @ManyToOne
    @JoinColumn(name ="idEscolaridade")
    protected Integer fkEscolaridade;
    @ManyToOne
    @JoinColumn(name ="idEducador")
    protected Integer fkEducador;

    public Turma() {
    }

    public Integer getIdTurma() {
        return idTurma;
    }

    public void setIdTurma(Integer idTurma) {
        this.idTurma = idTurma;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public Integer getFkEscolaridade() {
        return fkEscolaridade;
    }

    public void setFkEscolaridade(Integer fkEscolaridade) {
        this.fkEscolaridade = fkEscolaridade;
    }

    public Integer getFkEducador() {
        return fkEducador;
    }

    public void setFkEducador(Integer fkEducador) {
        this.fkEducador = fkEducador;
    }
}
