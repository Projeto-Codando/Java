package school.sptech.apicodando.api.domain.aluno;

public class TokenRequestDTO {
    private String token;

    public TokenRequestDTO(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}