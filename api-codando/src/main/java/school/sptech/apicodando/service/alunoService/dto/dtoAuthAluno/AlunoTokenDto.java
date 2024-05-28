package school.sptech.apicodando.service.alunoService.dto.dtoAuthAluno;

import java.util.UUID;

public class AlunoTokenDto {

    private UUID userId;
    private String nome;
    private String apelido;
    private String token;

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
