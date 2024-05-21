package school.sptech.apicodando.service.aulaService.dto;

import lombok.Data;
import school.sptech.apicodando.api.domain.tema.Tema;
@Data
public class AulaListagemDTO {

    protected Integer id;
    protected String titulo;
    protected String descricao;
    protected Integer nivelDificuldade;
    protected Integer pontuacaoMaxima;

    private TemaDto tema;

    @Data
    public static class TemaDto {
        private Integer id;
        private String nome;
    }

}
