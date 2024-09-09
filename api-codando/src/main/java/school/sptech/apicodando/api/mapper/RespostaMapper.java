package school.sptech.apicodando.api.mapper;

import lombok.Data;
import school.sptech.apicodando.api.domain.pergunta.Pergunta;
import school.sptech.apicodando.api.domain.resposta.Resposta;
import school.sptech.apicodando.service.respostaService.dto.RespostaCadastroDTO;
import school.sptech.apicodando.service.respostaService.dto.RespostaListagemDTO;

import java.util.List;

@Data
public class RespostaMapper {

    public static Resposta toEntity(RespostaCadastroDTO respostaCadastroDTO, Pergunta pergunta) {
        Resposta resposta = new Resposta();
        resposta.setTexto(respostaCadastroDTO.getTexto());
        resposta.setCorreta(respostaCadastroDTO.getCorreta());
        resposta.setPergunta(pergunta);
        return resposta;
    }

    public static RespostaListagemDTO toDto (Resposta resposta) {
        RespostaListagemDTO respostaListagem = new RespostaListagemDTO();
        respostaListagem.setIdResposta(resposta.getIdResposta());
        respostaListagem.setTexto(resposta.getTexto());
        respostaListagem.setCorreta(resposta.getCorreta());
        respostaListagem.setContador(resposta.getContador());
        respostaListagem.setIdPergunta(resposta.getPergunta().getId());
        return respostaListagem;
    }

    public static List<RespostaListagemDTO> toDto(List<Resposta> respostas) {
        return respostas.stream().map(RespostaMapper::toDto).toList();
    }



}
