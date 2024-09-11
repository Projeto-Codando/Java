package school.sptech.apicodando.service.respostaService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import school.sptech.apicodando.api.domain.aluno.Aluno;
import school.sptech.apicodando.api.domain.pergunta.Pergunta;
import school.sptech.apicodando.api.domain.resposta.Resposta;
import school.sptech.apicodando.api.domain.resposta.repository.RespostaRepository;
import school.sptech.apicodando.api.mapper.RespostaMapper;
import school.sptech.apicodando.service.alunoService.AlunoService;
import school.sptech.apicodando.service.perguntaService.PerguntaService;
import school.sptech.apicodando.service.respostaService.dto.RespostaCadastroDTO;
import school.sptech.apicodando.service.respostaService.dto.RespostaListagemDTO;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RespostaService {

    private final RespostaRepository respostaRepository;
    private final PerguntaService perguntaService;
    private final AlunoService alunoService;

    public Resposta criar (RespostaCadastroDTO respostaCadastroDTO) {
        if (respostaCadastroDTO == null) {
            throw new RuntimeException("Resposta não informada");
        }
        if (respostaCadastroDTO.getIdPergunta() == null) {
            throw new RuntimeException("Pergunta não informada");
        }
        Pergunta pergunta = perguntaService.buscarPorId(respostaCadastroDTO.getIdPergunta());
        Resposta resposta = RespostaMapper.toEntity(respostaCadastroDTO, pergunta);
        respostaRepository.save(resposta);
        return resposta;
    }

    public Resposta buscarPorId (Integer idResposta) {
        Resposta resposta = respostaRepository.findById(idResposta).orElseThrow(()
                -> new RuntimeException("Resposta não encontrada"));
        return resposta;
    }

    public List<RespostaListagemDTO> buscarPorIdPergunta (Integer idPergunta) {
        List<Resposta> resposta = respostaRepository.findByPergunta_Id(idPergunta);
        return RespostaMapper.toDto(resposta);
    }

    public RespostaListagemDTO incrementarContador (Integer idResposta, Integer idAluno) {
        Resposta resposta = buscarPorId(idResposta);
        Aluno aluno = alunoService.listarUmPorId(idAluno).get();
        resposta.setContador(resposta.getContador() + 1);
        resposta.getAlunos().add(aluno);
        aluno.getRespostas().add(resposta);
        alunoService.salvar(aluno);
        respostaRepository.save(resposta);
        return RespostaMapper.toDto(resposta);
    }

}
