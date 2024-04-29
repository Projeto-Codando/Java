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
public class CsvFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private Integer ANO_LETIVO;

    @NotBlank
    @Size(max = 23)
    private String NM_DIRETORIA;

    @NotBlank
    @Size(max = 26)
    private String NM_MUNICIPIO;

    @NotNull
    private Boolean CD_REDE_ENSINO;

    @NotNull
    private Integer CD_ESCOLA;

    @NotBlank
    @Size(max = 80)
    private String NM_COMPLETO_ESCOLA;

    @NotNull
    private Integer CD_TP_IDENTIFICADOR;

    @NotNull
    @Size(max = 5)
    private Double APR_1;

    @NotNull
    @Size(max = 5)
    private Double REP_1;

    @NotNull
    @Size(max = 5)
    private Double ABA_1;

    @NotNull
    @Size(max = 5)
    private Double APR_2;

    @NotNull
    @Size(max = 5)
    private Double REP_2;

    @NotNull
    @Size(max = 5)
    private Double ABA_2;

    @NotNull
    @Size(max = 5)
    private Double APR_3;

    @NotNull
    @Size(max = 5)
    private Double REP_3;

    @NotNull
    @Size(max = 5)
    private Double ABA_3;


}