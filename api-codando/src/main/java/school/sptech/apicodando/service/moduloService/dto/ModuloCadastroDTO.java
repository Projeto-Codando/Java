package school.sptech.apicodando.service.moduloService.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Data;
import school.sptech.apicodando.service.temaService.dto.TemaListagemDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
public class ModuloCadastroDTO {
    @NotBlank
    @Size(min = 3, max = 255)
    private String nome;
    private List<TemaListagemDTO> temas;
    @PositiveOrZero
    private UUID gradeId;

    public ModuloCadastroDTO() {
        this.temas = new ArrayList<>();
    }

}
