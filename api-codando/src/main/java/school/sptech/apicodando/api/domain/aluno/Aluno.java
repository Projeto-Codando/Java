package school.sptech.apicodando.api.domain.aluno;

import jakarta.persistence.*;
import lombok.Data;
import school.sptech.apicodando.api.domain.avatar.Avatar;
import school.sptech.apicodando.api.domain.escolaridade.Escolaridade;
import school.sptech.apicodando.api.domain.resposta.Resposta;
import school.sptech.apicodando.api.domain.turma.Turma;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;
    protected String nome;
    protected String sobrenome;
    protected String apelido;
    protected String senha;

    protected Boolean status;
    protected Integer moedas;
    protected String senhaTurma;
    protected int idAvatar;
    protected String fcmToken;

    @ManyToMany
    @JoinTable(
            name = "aluno_avatar",
            joinColumns = @JoinColumn(name = "aluno_id"),
            inverseJoinColumns = @JoinColumn(name = "avatar_id")
    )

    private List<Avatar> avatares;

    @ManyToMany
    @JoinTable(
            name = "aluno_resposta",
            joinColumns = @JoinColumn(name = "aluno_id"),
            inverseJoinColumns = @JoinColumn(name = "resposta_id")
    )
    protected List<Resposta> respostas;

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

    public void addAvatar(Avatar avatar) {
        this.avatares.add(avatar);
    }
}
