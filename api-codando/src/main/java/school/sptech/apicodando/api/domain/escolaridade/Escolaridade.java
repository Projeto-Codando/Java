package school.sptech.apicodando.api.domain.escolaridade;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import school.sptech.apicodando.api.domain.aluno.Aluno;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Escolaridade {

    @NotBlank
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer idEscolaridade;

    protected String descricao;

    public Escolaridade(String descricao) {
        this.descricao = descricao;
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
