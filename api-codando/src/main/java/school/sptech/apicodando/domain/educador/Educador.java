package school.sptech.apicodando.domain.educador;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Educador {

//    @NotBlank
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer idEducador;
//    @NotBlank
//    @Size(min = 3, max = 255)
//    @NotNull
    protected String nome;
//    @NotBlank
//    @Size(min = 3, max = 255)
//    @NotNull
    protected String sobrenome;
//    @NotNull
//    @NotBlank
//    @Email
    protected String email;
//    @NotBlank
//    @Size(min = 8, max = 255)
//    @NotNull
    protected String senha;

    public Educador() {
    }

    public Integer getIdEducador() {
        return idEducador;
    }

    public void setIdEducador(Integer idEducador) {
        this.idEducador = idEducador;
    }

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
}
