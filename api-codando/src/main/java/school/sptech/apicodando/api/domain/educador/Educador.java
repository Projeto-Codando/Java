package school.sptech.apicodando.api.domain.educador;


import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Entity
@Data
public class Educador {

    //    @NotBlank
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    protected UUID idEducador;

    protected String nome;

    protected String sobrenome;

    protected String email;

    protected String senha;

    public Educador() {
    }

}
