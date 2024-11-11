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
//    SE TIVER USANDO NO MOBILE, SÓ DESCOMENTAR NA PERGUNTA MAPPER
//    private Integer contador;
    private List<RespostaListagemDTO> respostas;

    public String getPorcentagemRespostasIncorretas() {
        int porcentagem = 100 - porcentagemRespostasCorretas();
        return porcentagem == 0 ? "N/A" : porcentagem + "%";
    }

    public String getPorcentagemRespostasCorretas() {
        int porcentagem = porcentagemRespostasCorretas();
        return porcentagem == 0 ? "N/A" : porcentagem + "%";
    }

    public int porcentagemRespostasCorretas() {
        int totalRespostas = 0;
        int totalRespostasCorretas = 0;

        for (RespostaListagemDTO resposta : respostas) {
            totalRespostas += resposta.getContador();
            if (resposta.getTentativasIncorretas() < resposta.getContador()) {
                totalRespostasCorretas += resposta.getContador() - resposta.getTentativasIncorretas();
            }
        }
        return totalRespostas == 0 ? 0 : (totalRespostasCorretas * 100 / totalRespostas);
    }


}
