package school.sptech.apicodando.service.turmaService.dto;

import jakarta.validation.constraints.Size;
import lombok.Data;
import school.sptech.apicodando.api.domain.aluno.Aluno;
import school.sptech.apicodando.api.domain.educador.Educador;
import school.sptech.apicodando.api.domain.escolaridade.Escolaridade;

import java.util.List;

@Data
public class TurmaAtualizaDTO {
    @Size(min = 3, max = 255)
    private String nome;
    @Size(min = 3, max = 255)
    private String senha;
    private Escolaridade fkEscolaridade;
    private Educador fkEducador;
}
