package school.sptech.apicodando.domain.csvFile;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class csv {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private Integer id;

    @NotBlank
    @Size(max = 4)
    private Integer anoLetivo;

    @NotBlank
    @Size(max = 23)
    private String nmDiretoria;

    @NotBlank
    @Size(max = 26)
    private String nmMunicipio;

    @NotNull
    private Boolean cdRedeEnsino;

    @NotNull
    private Integer cdEscola;

    @NotBlank
    @Size(max = 80)
    private String nmCompletoEscola;

    @NotNull
    private Integer cdTpIdentificador;

    @NotNull
    @Size(max = 5)
    private Double apr1;

    @NotNull
    @Size(max = 5)
    private Double rep1;

    @NotNull
    @Size(max = 5)
    private Double aba1;

    @NotNull
    @Size(max = 5)
    private Double apr2;

    @NotNull
    @Size(max = 5)
    private Double rep2;

    @NotNull
    @Size(max = 5)
    private Double aba2;

    @NotNull
    @Size(max = 5)
    private Double apr3;

    @NotNull
    @Size(max = 5)
    private Double rep3;

    @NotNull
    @Size(max = 5)
    private Double aba3;
}