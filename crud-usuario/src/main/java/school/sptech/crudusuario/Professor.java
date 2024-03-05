package school.sptech.crudusuario;

import java.time.LocalDate;

public class Professor extends Usuario{

    private String email;

    public Professor() {
        super();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
