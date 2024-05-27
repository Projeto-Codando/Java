package school.sptech.apicodando.service.alunoService.dto;

import lombok.Data;

@Data
public class AlunoAtualizadoDTO {
    private String nome;
    private String sobrenome;
    private String senha;
    private String apelido;
}
