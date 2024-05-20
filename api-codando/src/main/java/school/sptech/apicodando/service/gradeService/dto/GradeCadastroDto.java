package school.sptech.apicodando.service.gradeService.dto;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;
import school.sptech.apicodando.api.domain.modulo.Modulo;

import java.util.List;
@Data
public class GradeCadastroDto {

    @OneToMany(mappedBy = "idModulo")
    private List<Modulo> modulo;
    @ManyToOne
    @JoinColumn(name = "idTurma")
    private int fkTurma;

}
