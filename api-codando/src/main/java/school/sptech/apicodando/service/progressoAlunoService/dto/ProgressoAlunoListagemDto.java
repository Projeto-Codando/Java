package school.sptech.apicodando.service.progressoAlunoService.dto;

import lombok.Data;
import school.sptech.apicodando.api.domain.aula.Aula;

@Data
public class ProgressoAlunoListagemDto {
    private Integer idProgressoAluno;
    private Integer pontuacaoAluno;
    private String statusAula;
    private AlunoListagemDto aluno;
    private AulaListagemDto aula;

    @Data
    public class AlunoListagemDto {
        private Integer idAluno;
        private String nome;
        private String sobrenome;
        private String apelido;
        private Boolean status;
        private String escolaridade;
    }

    @Data
    public class AulaListagemDto {
        private Integer idAula;
        private String nome;
        private String descricao;
        private Integer pontuacaoMaxima;
        private Integer idModulo;
    }

}
