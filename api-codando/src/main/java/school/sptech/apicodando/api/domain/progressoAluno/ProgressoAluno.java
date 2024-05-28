package school.sptech.apicodando.api.domain.progressoAluno;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import school.sptech.apicodando.api.domain.aluno.Aluno;
import school.sptech.apicodando.api.domain.aula.Aula;

import java.util.UUID;

@Entity
@Data
public class ProgressoAluno {

    //nao entendi mt bem essa tabela
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    protected UUID idProgressoAluno;
    @NotNull
    @PositiveOrZero
    protected Integer pontuacaoAluno;

    private String statusAula = "Em andamento";
    @OneToOne
    @JoinColumn(name = "fkAluno")
    protected Aluno fkAluno;
    @OneToOne
    @JoinColumn(name = "fkAula")
    protected Aula fkAula;

}
