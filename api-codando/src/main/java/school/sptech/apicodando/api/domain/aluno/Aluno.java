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

//    @NotBlank
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer idAluno;
//    @NotBlank
//    @Size(min = 3, max = 255)
//    @NotNull
    protected String nome;
//    @NotBlank
//    @Size(min = 3, max = 255)
//    @NotNull
    protected String sobrenome;
//    @NotBlank
//    @Size(min = 3, max = 255)
//    @NotNull
    protected String apelido;
//    @NotBlank
//    @Size(min = 8, max = 255)
//    @NotNull
    protected String senha;
//    @NotBlank
//    @Size(min = 3, max = 255)
//    @NotNull
    protected Boolean status;
//    @NotBlank
//    @PositiveOrZero
    protected Integer moedas;

    protected String senhaTurma;
    @OneToMany
    @JoinColumn(name = "idAvatar")
    protected List<Avatar> avatares;
    @ManyToOne
    @JoinColumn(name = "idTurma")
    protected Turma turma;
    @OneToOne
    @JoinColumn(name = "idEscolaridade")
    protected Escolaridade escolaridade;

    public Aluno() {
        this.status = true;
        this.moedas = 0;
        this.avatares = new ArrayList<>();
    }

}
