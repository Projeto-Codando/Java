package school.sptech.apicodando.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Tema {

    @NotBlank
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer idTema;
    @NotBlank
    @Size(min = 3, max = 255)
    @NotNull
    protected String nome;
//    @ManyToOne
//    @JoinColumn(name ="idModulo")
//    protected Integer fkModulo;

    public Tema() {
    }

    public Integer getIdTema() {
        return idTema;
    }

    public void setIdTema(Integer idTema) {
        this.idTema = idTema;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

//    public Integer getFkModulo() {
//        return fkModulo;
//    }
//
//    public void setFkModulo(Integer fkModulo) {
//        this.fkModulo = fkModulo;
//    }
}
