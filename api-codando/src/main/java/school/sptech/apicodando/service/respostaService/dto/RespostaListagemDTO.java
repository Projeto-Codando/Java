package school.sptech.apicodando.service.respostaService.dto;

import lombok.Data;

import java.util.List;

@Data
public class RespostaListagemDTO {

    private Integer idResposta;
    private String texto;
    private Boolean correta;
    private Integer contador;
    private Integer idPergunta;
    private List<AlunoListagemDto> alunos;
    private Integer tentativasIncorretas;

    @Data
    public static class AlunoListagemDto {
        private Integer idAluno;
        private String nome;
        private String sobrenome;
        private String apelido;
        private Boolean status;
        private Integer moedas;
        private Integer idTurma;

    }

}
