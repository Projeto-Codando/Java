package school.sptech.apicodando.api.domain.avatar;

import jakarta.persistence.*;
import lombok.Data;
import school.sptech.apicodando.api.domain.aluno.Aluno;

import java.util.List;

@Entity
@Data
public class Avatar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;
    protected String descricao;
    protected int preco;
    protected String imagemURL;

    public Avatar(String descricao, int preco, String imagemURL) {
        this.descricao = descricao;
        this.preco = preco;
        this.imagemURL = imagemURL;
    }

    @ManyToMany(mappedBy = "avatares")
    private List<Aluno> alunos;

    public Avatar() {

    }
}
