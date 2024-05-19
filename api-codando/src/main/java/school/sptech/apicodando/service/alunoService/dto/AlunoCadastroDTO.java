package school.sptech.apicodando.service.alunoService.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AlunoCadastroDTO {

    @NotBlank
    @Size(min = 3, max = 255)
    @NotNull
    private String nome;

    @NotBlank
    @Size(min = 3, max = 255)
    @NotNull
    private String sobrenome;

    @NotBlank
    @Size(min = 3, max = 255)
    @NotNull
    private String apelido;

    @NotBlank
    @Size(min = 8, max = 255)
    @NotNull
    private String senha;

    @NotBlank
    @Size(min = 3, max = 255)
    @NotNull
    private String senhaTurma;


}
