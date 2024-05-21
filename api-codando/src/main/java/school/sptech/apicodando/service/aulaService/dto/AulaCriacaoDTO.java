package school.sptech.apicodando.service.aulaService.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Data;
import school.sptech.apicodando.api.domain.tema.Tema;

@Data
public class AulaCriacaoDTO {


    @NotBlank
    @Size(min = 3, max = 255)
    @NotNull
    protected String titulo;
    @NotBlank
    @Size(min = 3, max = 255)
    @NotNull
    protected String descricao;
    @NotBlank
    @PositiveOrZero
    protected Integer nivelDificuldade;
    @NotBlank
    @PositiveOrZero
    protected Integer pontuacaoMaxima;
    @ManyToOne
    private Integer temaId;

}
