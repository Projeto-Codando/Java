package school.sptech.apicodando.service.gradeService.dto;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;
import school.sptech.apicodando.api.domain.modulo.Modulo;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
public class GradeCadastroDto {

    @OneToMany(mappedBy = "idModulo")
    private List<Modulo> modulo;
    @ManyToOne
    @JoinColumn(name = "idTurma")
    private UUID fkTurma;

    public GradeCadastroDto() {
        this.modulo = new ArrayList<>();
    }
}
