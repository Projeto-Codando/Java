package school.sptech.apicodando.api.mapper;

import lombok.Data;
import school.sptech.apicodando.api.domain.pergunta.Pergunta;
import school.sptech.apicodando.api.domain.resposta.Resposta;
import school.sptech.apicodando.service.respostaService.dto.RespostaCadastroDTO;
import school.sptech.apicodando.service.respostaService.dto.RespostaListagemDTO;

import java.util.ArrayList;
import java.util.List;

@Data
public class RespostaMapper {

    public static Resposta toEntity(RespostaCadastroDTO respostaCadastroDTO, Pergunta pergunta) {
        Resposta resposta = new Resposta();
        resposta.setTexto(respostaCadastroDTO.getTexto());
        resposta.setCorreta(respostaCadastroDTO.getCorreta());
        resposta.setPergunta(pergunta);
        resposta.setAlunos(new ArrayList<>());
        return resposta;
    }

    public static RespostaListagemDTO toDto (Resposta resposta) {
        RespostaListagemDTO respostaListagem = new RespostaListagemDTO();
        respostaListagem.setIdResposta(resposta.getIdResposta());
        respostaListagem.setTexto(resposta.getTexto());
        respostaListagem.setCorreta(resposta.getCorreta());
        respostaListagem.setContador(resposta.getContador());
        respostaListagem.setIdPergunta(resposta.getPergunta().getId());

        List<RespostaListagemDTO.AlunoListagemDto> idAlunos = new ArrayList<>();
        resposta.getAlunos().forEach(aluno -> {
            RespostaListagemDTO.AlunoListagemDto alunoListagemDto = new RespostaListagemDTO.AlunoListagemDto();
            alunoListagemDto.setIdAluno(aluno.getIdAluno());
            alunoListagemDto.setNome(aluno.getNome());
            alunoListagemDto.setSobrenome(aluno.getSobrenome());
            alunoListagemDto.setApelido(aluno.getApelido());
            alunoListagemDto.setStatus(aluno.getStatus());
            alunoListagemDto.setMoedas(aluno.getMoedas());
            alunoListagemDto.setIdTurma(aluno.getTurma().getIdTurma());
            idAlunos.add(alunoListagemDto);
        });

        respostaListagem.setAlunos(idAlunos);
        return respostaListagem;
    }

    public static List<RespostaListagemDTO> toDto(List<Resposta> respostas) {
        return respostas.stream().map(RespostaMapper::toDto).toList();
    }



}
