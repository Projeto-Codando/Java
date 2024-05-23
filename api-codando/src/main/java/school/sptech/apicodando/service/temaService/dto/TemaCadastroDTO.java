package school.sptech.apicodando.service.temaService.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import school.sptech.apicodando.api.domain.aula.Aula;
import school.sptech.apicodando.api.domain.modulo.Modulo;
import school.sptech.apicodando.service.aulaService.dto.AulaListagemDTO;

import java.util.ArrayList;
import java.util.List;

@Data
public class TemaCadastroDTO {

    @NotBlank
    @Size(min = 3, max = 255)
    @NotNull
    protected String nome;
    private List<AulaListagemDTO> aulas;
    private int moduloId;

    public TemaCadastroDTO() {
        this.aulas = new ArrayList<>();
    }

}
