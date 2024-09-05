package school.sptech.apicodando.api.domain.educador;


import jakarta.persistence.*;
import lombok.Data;
import school.sptech.apicodando.api.domain.mensagem.Mensagem;

import java.util.List;

@Entity
@Data
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

    @OneToMany(mappedBy = "educador")
    protected List<Mensagem> mensagens;

    public Educador() {
    }

}
