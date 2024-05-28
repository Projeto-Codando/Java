package school.sptech.apicodando.api.domain.aluno;

import jakarta.persistence.*;
import lombok.Data;
import school.sptech.apicodando.api.domain.avatar.Avatar;
import school.sptech.apicodando.api.domain.escolaridade.Escolaridade;
import school.sptech.apicodando.api.domain.turma.Turma;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer idAluno;
    protected String nome;
    protected String sobrenome;
    protected String apelido;
    protected String senha;
    protected Boolean status;
    protected Integer moedas;
    protected String senhaTurma;
    protected int idAvatar;
    @OneToMany
    @JoinColumn(name = "idAvatar")
    protected List<Avatar> avatares;
    @ManyToOne
    @JoinColumn(name = "idTurma")
    protected Turma turma;

    @ManyToOne
    @JoinColumn(name = "idEscolaridade")
    protected Escolaridade escolaridade;

    public Aluno() {
        this.status = true;
        this.moedas = 0;
        this.avatares = new ArrayList<>();
    }

}
