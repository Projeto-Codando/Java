package school.sptech.apicodando.domain.progressoAluno;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

@Entity
public class ProgressoAluno {

    //nao entendi mt bem essa tabela
    @NotBlank
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer idProgressoAluno;
    @NotBlank
    @PositiveOrZero
    protected Integer pontuacaoAluno;
    @NotBlank
    @Size(min = 3, max = 255)
    @NotNull
    protected String statusAula;

    public ProgressoAluno() {
    }

    public Integer getIdProgressoAluno() {
        return idProgressoAluno;
    }

    public void setIdProgressoAluno(Integer idProgressoAluno) {
        this.idProgressoAluno = idProgressoAluno;
    }

    public Integer getPontuacaoAluno() {
        return pontuacaoAluno;
    }

    public void setPontuacaoAluno(Integer pontuacaoAluno) {
        this.pontuacaoAluno = pontuacaoAluno;
    }

    public String getStatusAula() {
        return statusAula;
    }

    public void setStatusAula(String statusAula) {
        this.statusAula = statusAula;
    }
}
