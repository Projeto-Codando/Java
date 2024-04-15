package school.sptech.apicodando.service.autenticacao.dto;

import jakarta.validation.constraints.NotBlank;

public class AlunoLoginDTO {

    @NotBlank
    private String apelido;

    @NotBlank
    private String senha;

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
