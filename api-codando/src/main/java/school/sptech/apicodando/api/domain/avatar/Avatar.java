package school.sptech.apicodando.api.domain.avatar;

import jakarta.persistence.*;
import lombok.Data;
import school.sptech.apicodando.api.domain.aluno.Aluno;

@Entity
@Data
public class Avatar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;
    protected String descricao;
    protected int preco;
    protected String imagemURL;

//    @ManyToOne
//    @JoinColumn(name = "id_aluno")
//    protected Aluno aluno;
}
