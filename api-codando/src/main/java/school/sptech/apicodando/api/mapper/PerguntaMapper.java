package school.sptech.apicodando.api.mapper;

import lombok.Data;
import school.sptech.apicodando.api.domain.aula.Aula;
import school.sptech.apicodando.api.domain.pergunta.Pergunta;
import school.sptech.apicodando.service.perguntaService.dto.PerguntaCadastroDTO;
import school.sptech.apicodando.service.perguntaService.dto.PerguntaListagemDTO;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class PerguntaMapper {

    public static PerguntaListagemDTO toDto (Pergunta pergunta) {
        PerguntaListagemDTO perguntaListagemDTO = new PerguntaListagemDTO();
        perguntaListagemDTO.setIdPergunta(pergunta.getId());
        perguntaListagemDTO.setTexto(pergunta.getTexto());
        perguntaListagemDTO.setIdAula(pergunta.getAula().getId());
//        perguntaListagemDTO.setContador(pergunta.getContador());
        perguntaListagemDTO.setRespostas(RespostaMapper.toDto(pergunta.getRespostas()));
        return perguntaListagemDTO;
    }

    public static Pergunta toEntity (PerguntaCadastroDTO perguntaCadastroDTO, Aula aula) {
        Pergunta pergunta = new Pergunta();
        pergunta.setTexto(perguntaCadastroDTO.getTexto());
        pergunta.setAula(aula);
        return pergunta;
    }

    public static List<PerguntaListagemDTO> toDto (List<Pergunta> perguntas) {
        return perguntas.stream().map(PerguntaMapper::toDto).collect(Collectors.toList());
    }

}
