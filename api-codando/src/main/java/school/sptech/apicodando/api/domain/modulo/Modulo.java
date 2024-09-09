package school.sptech.apicodando.api.domain.modulo;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import school.sptech.apicodando.api.domain.grade.Grade;
import school.sptech.apicodando.api.domain.tema.Tema;

import java.util.List;

@Entity
@Data
public class Modulo {

//    @NotBlank
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer idModulo;
//    @NotBlank
//    @Size(min = 3, max = 255)
//    @NotNull
    protected String nome;
    @OneToMany(mappedBy = "modulo")
    private List<Tema> temas;
    @ManyToOne
//    @Column(name = "moduloGrade")
    protected Grade grade;


    public Modulo() {
    }



}
