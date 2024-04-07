package school.sptech.apicodando.record.DTOs.educador;

public class EducadorListagemDTO {
    private Integer idEducador;
    private String nome;
    private String sobrenome;
    private String email;

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

    // getters e setters
}
