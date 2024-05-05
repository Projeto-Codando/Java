package school.sptech.apicodando.domain.aluno;

import jakarta.persistence.*;
import school.sptech.apicodando.domain.escolaridade.Escolaridade;
import school.sptech.apicodando.domain.turma.Turma;

import java.net.Inet4Address;
import java.util.List;

@Entity
public class Aluno {

//    @NotBlank
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer idAluno;
//    @NotBlank
//    @Size(min = 3, max = 255)
//    @NotNull
    protected String nome;
//    @NotBlank
//    @Size(min = 3, max = 255)
//    @NotNull
    protected String sobrenome;
//    @NotBlank
//    @Size(min = 3, max = 255)
//    @NotNull
    protected String apelido;
//    @NotBlank
//    @Size(min = 8, max = 255)
//    @NotNull
    protected String senha;
//    @NotBlank
//    @Size(min = 3, max = 255)
//    @NotNull
    protected String status;
//    @NotBlank
//    @PositiveOrZero
    protected Integer moedas;
    @ManyToOne
    protected Turma fkTurma;
    @OneToMany
    protected List<Escolaridade> fkEscolaridade;

    public Aluno() {
    }

    public Integer getIdAluno() {
        return idAluno;
    }

    public void setIdAluno(Integer idAluno) {
        this.idAluno = idAluno;
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

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getMoedas() {
        return moedas;
    }

    public void setMoedas(Integer moedas) {
        this.moedas = moedas;
    }

//    public Integer getFkTurma() {
//        return fkTurma;
//    }
//
//    public void setFkTurma(Integer fkTurma) {
//        this.fkTurma = fkTurma;
//    }

//    public Integer getFkEscolaridade() {
//        return fkEscolaridade;
//    }
//
//    public void setFkEscolaridade(Integer fkEscolaridade) {
//        this.fkEscolaridade = fkEscolaridade;
//    }
}
