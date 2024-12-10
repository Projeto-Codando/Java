package school.sptech.apicodando.service.alunoService.dto.dtoAuthAluno;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.lang.Nullable;
import jakarta.validation.constraints.NotNull;

@Getter
@Setter
@ToString
public class AlunoLoginDTO {
    @NotNull
    private String apelido;
    @NotNull
    private String senha;
    // Criando o firebase token com o @Nullable para receber ou nao o token
    @Nullable
    private String fcmToken;
}
