package school.sptech.apicodando.api.domain.turma;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import school.sptech.apicodando.api.domain.educador.Educador;
import school.sptech.apicodando.api.domain.escolaridade.Escolaridade;

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
    protected String senha;
    @ManyToOne
    @JoinColumn(name ="idEscolaridade")
    protected Escolaridade fkEscolaridade;
    @ManyToOne
    @JoinColumn(name ="idEducador")
    protected Educador fkEducador;

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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

//    public Integer getFkEscolaridade() {
//        return fkEscolaridade;
//    }
//
//    public void setFkEscolaridade(Integer fkEscolaridade) {
//        this.fkEscolaridade = fkEscolaridade;
//    }
//
//    public Integer getFkEducador() {
//        return fkEducador;
//    }
//
//    public void setFkEducador(Integer fkEducador) {
//        this.fkEducador = fkEducador;
//    }
}
