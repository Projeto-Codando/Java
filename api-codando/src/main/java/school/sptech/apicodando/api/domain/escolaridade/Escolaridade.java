package school.sptech.apicodando.api.domain.escolaridade;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Escolaridade {

    @NotBlank
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer idEscolaridade;
    @NotBlank
    @Size(min = 3, max = 255)
    @NotNull
    protected String descricao;

    public Escolaridade() {
    }

    public Integer getIdEscolaridade() {
        return idEscolaridade;
    }

    public void setIdEscolaridade(Integer idEscolaridade) {
        this.idEscolaridade = idEscolaridade;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
