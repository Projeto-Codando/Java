package school.sptech.apicodando.service.aulaService.dto;

import lombok.Data;

@Data
public class AulaListagemDTO {

    protected Integer id;
    protected String titulo;
    protected String descricao;
    protected Integer nivelDificuldade;
    protected Integer pontuacaoMaxima;
    protected Integer idTurma;

//    private TemaListagemDTO tema;

//    @Data
//    public static class TemaDto {
//        private Integer id;
//        private String nome;
//    }

}
