package school.sptech.apicodando.service.progressoAlunoService.dto;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import school.sptech.apicodando.service.aulaService.dto.AulaListagemDTO;

import java.util.UUID;

@Data
public class ProgressoAlunoCadastroDTO {

    private Integer pontuacaoAluno = 0;
    @OneToOne
    @NotNull
    @JoinColumn(name = "idAluno")
    private UUID fkAluno;
    @OneToOne
    @NotNull
    @JoinColumn(name = "idAula")
    private UUID fkAula;

}
