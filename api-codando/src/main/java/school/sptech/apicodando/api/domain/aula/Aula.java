package school.sptech.apicodando.api.domain.aula;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import school.sptech.apicodando.api.domain.tema.Tema;

@Entity
public class Aula {

    @NotBlank
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;
    @NotBlank
    @Size(min = 3, max = 255)
    @NotNull
    protected String titulo;
    @NotBlank
    @Size(min = 3, max = 255)
    @NotNull
    protected String descricao;
    @NotBlank
    @PositiveOrZero
    protected Integer nivelDificuldade;
    @NotBlank
    @PositiveOrZero
    protected Integer pontuacaoMaxima;
    @ManyToOne
    private Tema tema;

    public Aula() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getNivelDificuldade() {
        return nivelDificuldade;
    }

    public void setNivelDificuldade(Integer nivelDificuldade) {
        this.nivelDificuldade = nivelDificuldade;
    }

    public Integer getPontuacaoMaxima() {
        return pontuacaoMaxima;
    }

    public void setPontuacaoMaxima(Integer pontuacaoMaxima) {
        this.pontuacaoMaxima = pontuacaoMaxima;
    }

//    public Integer getFkTema() {
//        return fkTema;
//    }
//
//    public void setFkTema(Integer fkTema) {
//        this.fkTema = fkTema;
//    }
}
