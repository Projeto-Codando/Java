package school.sptech.apicodando.record.DTOs.educador;

import jakarta.validation.constraints.NotBlank;

public class EducadorLoginDTO {
    @NotBlank
    protected String email;
    @NotBlank
    protected String senha;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
