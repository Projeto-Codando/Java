package school.sptech.apicodando.service.aulaService.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AulaCriacaoDTO {

    @Size(min = 3, max = 255)
    protected String titulo;

    @Size(min = 3, max = 255)
    protected String descricao;

    @NotNull
    @Min(0)
    protected Integer nivelDificuldade;

    @NotNull
    @Min(0)
    protected Integer pontuacaoMaxima;

    @NotNull
    private Integer temaId;
}