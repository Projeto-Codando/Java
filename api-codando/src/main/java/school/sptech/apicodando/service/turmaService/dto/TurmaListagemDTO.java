package school.sptech.apicodando.service.turmaService.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import school.sptech.apicodando.api.domain.aluno.Aluno;
import school.sptech.apicodando.api.domain.educador.Educador;
import school.sptech.apicodando.api.domain.escolaridade.Escolaridade;
import school.sptech.apicodando.api.mapper.AlunoMapper;
import school.sptech.apicodando.service.alunoService.dto.AlunoListagemDTO;

import java.util.List;
@Data
@Getter
@Setter
public class TurmaListagemDTO {
    private int idTurma;
    private String nome;
    private String senha;
    private Escolaridade fkEscolaridade;
    private Educador fkEducador;
    private List<AlunoListagemDTO> alunos;
    private boolean statusTurma;

//    public String getFkEscolaridade() {
//
//    }

    public String getStatusTurma(){
        return statusTurma ? "Ativa" : "Inativa";
    }

    @Data
    public static class AlunoListagemDTO {
        private Integer idAluno;
        private String nome;
        private String sobrenome;
        private String apelido;
        private String status;
        private Integer moedas;
    }
}
