package school.sptech.apicodando.service.perguntaService.dto;

import lombok.Data;
import school.sptech.apicodando.api.domain.resposta.Resposta;
import school.sptech.apicodando.service.respostaService.dto.RespostaListagemDTO;

import java.util.List;

@Data
public class PerguntaListagemDTO {

    private Integer idPergunta;
    private String texto;
    private Integer idAula;
    private Integer contador;
    private List<RespostaListagemDTO> respostas;

    public String getPorcentagemRespostasIncorretas() {
        return 100 - porcentagemRespostasCorretas() + "%";
    }

    public String getPorcentagemRespostasCorretas() {
        return porcentagemRespostasCorretas() + "%";
    }

    public int porcentagemRespostasCorretas() {
        int totalRespostas = 0;
        int totalRespostasCorretas = 0;
        for (RespostaListagemDTO resposta : respostas) {
            totalRespostas++;
            if (resposta.getCorreta()) {
                totalRespostasCorretas++;
            }
        }
        return totalRespostas == 0 ? 0 : (totalRespostasCorretas * 100 / totalRespostas);
    }


}
