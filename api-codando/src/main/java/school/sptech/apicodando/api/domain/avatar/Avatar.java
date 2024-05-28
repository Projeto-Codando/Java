package school.sptech.apicodando.api.domain.avatar;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import school.sptech.apicodando.api.domain.aluno.Aluno;

import java.util.UUID;

@Entity
@Data
public class Avatar {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    protected UUID id;
    protected String descricao;
    protected int preco;
    protected String imagemURL;

    @ManyToOne
    @JoinColumn(name = "id_aluno")
    protected Aluno aluno;
}
