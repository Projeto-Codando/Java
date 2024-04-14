package school.sptech.apicodando.service.alunoService.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

public class AlunoCadastroDTO {

    @NotBlank
    @Size(min = 3, max = 255)
    @NotNull
    private String nome;

    @NotBlank
    @Size(min = 3, max = 255)
    @NotNull
    private String sobrenome;

    @NotBlank
    @Size(min = 3, max = 255)
    @NotNull
    private String apelido;

    @NotBlank
    @Size(min = 8, max = 255)
    @NotNull
    private String senha;

    @NotBlank
    @Size(min = 3, max = 255)
    @NotNull
    private String status;

    @NotBlank
    @PositiveOrZero
    private Integer moedas;

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getMoedas() {
        return moedas;
    }

    public void setMoedas(Integer moedas) {
        this.moedas = moedas;
    }
}
