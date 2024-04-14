package school.sptech.apicodando.service.educadorService.dto;

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
