package school.sptech.apicodando.service.turmaService.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import school.sptech.apicodando.api.domain.aluno.Aluno;
import school.sptech.apicodando.api.domain.educador.Educador;
import school.sptech.apicodando.api.domain.escolaridade.Escolaridade;
import school.sptech.apicodando.service.alunoService.dto.AlunoListagemDTO;
import school.sptech.apicodando.service.aulaService.dto.AulaListagemDTO;

import java.util.ArrayList;
import java.util.List;
@Data
public class TurmaCadastroDTO {

    @NotBlank
    @Size(min = 3, max = 255)
    @NotNull
    protected String nome;
    @NotBlank
    @Size(min = 3, max = 255)
    @NotNull
    protected String senha;
    @ManyToOne
    @JoinColumn(name ="idEscolaridade")
    protected int fkEscolaridade;
    @ManyToOne
    @JoinColumn(name ="idEducador")
    protected int fkEducador;
    @OneToMany
    @JoinColumn(name ="idModulo")
    protected int fkModulo;
    @OneToMany
    protected List<Aluno> alunos;

    protected List<AulaListagemDTO> aulas;

    protected boolean statusTurma;

    public TurmaCadastroDTO() {
        this.alunos = new ArrayList<>();
        statusTurma = true;
        this.aulas = new ArrayList<>();
    }


}
