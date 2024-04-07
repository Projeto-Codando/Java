package school.sptech.apicodando.record.DTOs.educador;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class EducadorCadastroDTO {
    @NotBlank
    @Size(min = 3, max = 255)
    @NotNull
    private String nome;

    @NotBlank
    @Size(min = 3, max = 255)
    @NotNull
    private String sobrenome;

    @NotBlank
    @Email
    @NotNull
    private String email;

    @NotBlank
    @Size(min = 8, max = 255)
    @NotNull
    private String senha;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

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

    // getters e setters
}
