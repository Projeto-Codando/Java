package school.sptech.apicodando.api.domain.progressoAluno;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Data;
import school.sptech.apicodando.api.domain.aluno.Aluno;
import school.sptech.apicodando.api.domain.aula.Aula;

@Entity
@Data
public class ProgressoAluno {

    //nao entendi mt bem essa tabela
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer idProgressoAluno;
    @NotNull
    @PositiveOrZero
    protected Integer pontuacaoAluno;
    @NotBlank
    @Size(min = 3, max = 255)
    @NotNull
    protected String statusAula;
    @OneToOne
    @JoinColumn(name = "fkAluno")
    protected Aluno fkAluno;
    @OneToOne
    @JoinColumn(name = "fkAula")
    protected Aula fkAula;

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
