package school.sptech.apicodando.service.progressoAlunoService.dto;

import lombok.Data;
import school.sptech.apicodando.api.domain.aula.Aula;

import java.util.UUID;

@Data
public class ProgressoAlunoListagemDto {
    private UUID idProgressoAluno;
    private Integer pontuacaoAluno;
    private String statusAula;
    private AlunoListagemDto aluno;
    private AulaListagemDto aula;

    @Data
    public class AlunoListagemDto {
        private UUID idAluno;
        private String nome;
        private String sobrenome;
        private String apelido;
        private Boolean status;
        private String escolaridade;
        private Integer moedas;
    }

    @Data
    public class AulaListagemDto {
        private UUID idAula;
        private String nome;
        private String descricao;
        private Integer pontuacaoMaxima;
    }

}
