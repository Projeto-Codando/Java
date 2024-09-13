package school.sptech.apicodando.service.respostaService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import school.sptech.apicodando.api.domain.aluno.Aluno;
import school.sptech.apicodando.api.domain.pergunta.Pergunta;
import school.sptech.apicodando.api.domain.resposta.Resposta;
import school.sptech.apicodando.api.domain.resposta.repository.RespostaRepository;
import school.sptech.apicodando.api.domain.turma.Turma;
import school.sptech.apicodando.api.mapper.RespostaMapper;
import school.sptech.apicodando.service.alunoService.AlunoService;
import school.sptech.apicodando.service.perguntaService.PerguntaService;
import school.sptech.apicodando.service.respostaService.dto.RespostaCadastroDTO;
import school.sptech.apicodando.service.respostaService.dto.RespostaListagemDTO;
import school.sptech.apicodando.service.turmaService.TurmaService;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RespostaService {

    private final RespostaRepository respostaRepository;
    private final PerguntaService perguntaService;
    private final AlunoService alunoService;
    private final TurmaService turmaService;

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

        if (resposta.getAlunos().contains(aluno)) {
            throw new RuntimeException("Aluno já respondeu essa pergunta");
        }
        if (resposta == null) {
            throw new RuntimeException("Resposta não encontrada");
        }
        if (aluno == null) {
            throw new RuntimeException("Aluno não encontrado");
        }

        resposta.setContador(resposta.getContador() + 1);
        resposta.getAlunos().add(aluno);
        aluno.getRespostas().add(resposta);
        alunoService.salvar(aluno);
        respostaRepository.save(resposta);
        return RespostaMapper.toDto(resposta);
    }

//    public List<RespostaListagemDTO> buscarPorIdAluno (Integer idAluno) {
//        Aluno aluno = alunoService.listarUmPorId(idAluno).get();
//        List<Resposta> respostas = respostaRepository.findByAlunos(aluno);
//        return RespostaMapper.toDto(respostas);
//    }

    public List<RespostaListagemDTO> listarAlunoQueMaisErrou () {
        List<Resposta> respostas = respostaRepository.findAll();
        respostas.sort((r1, r2) -> r2.getContador().compareTo(r1.getContador()));
        return RespostaMapper.toDto(respostas);
    }

    public List<RespostaListagemDTO> listarAlunosDeUmaTurmaPorRespostasCorretas(Integer idTurma) {
        List<Resposta> respostas = respostaRepository.findAll();
        Turma turma = turmaService.buscarPorId(idTurma);
        List<Resposta> r = new ArrayList<>();
        for (Resposta resposta : respostas) {
            if (resposta.getCorreta() && resposta.getAlunos().get(0).getTurma().getIdTurma().equals(turma.getIdTurma())) {
                r.add(resposta);
            }
        }
        //metodo para organizar por quantidade de respostas corretas
        r.sort((r1, r2) -> r2.getContador().compareTo(r1.getContador()));
        return RespostaMapper.toDto(r);
    }
}
