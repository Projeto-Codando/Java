package school.sptech.apicodando.service.respostaService;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.stereotype.Service;
import school.sptech.apicodando.api.domain.aluno.Aluno;
import school.sptech.apicodando.api.domain.pergunta.Pergunta;
import school.sptech.apicodando.api.domain.pergunta.repository.PerguntaRepository;
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
    private final PerguntaRepository perguntaRepository;

    @PostConstruct
    public void inserirDadosIniciaisSeNecessario() {
        if (respostaRepository.count() == 0) {
            respostaRepository.saveAll(criarRespostas());
            System.out.println("Dado inicial da resposta inserido.");
        } else {
            System.out.println("Dado da resposta ja inserido.");
        }
    }

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

    public List<Resposta> buscarPorIdPergunta (Integer idPergunta) {
        return respostaRepository.findByPergunta_Id(idPergunta);

    }

    public RespostaListagemDTO incrementarContador (Integer idResposta, Integer idAluno) {
        Resposta resposta = buscarPorId(idResposta);
        Aluno aluno = alunoService.listarUmPorId(idAluno).get();
        Pergunta pergunta = resposta.getPergunta();

        if (resposta.getAlunos().contains(aluno)) {
            throw new RuntimeException("Aluno já respondeu essa pergunta");
        }
        if (resposta == null) {
            throw new RuntimeException("Resposta não encontrada");
        }
        if (aluno == null) {
            throw new RuntimeException("Aluno não encontrado");
        }

        if (pergunta.getContador() == null) {
            pergunta.setContador(0);
        }

        pergunta.setContador(pergunta.getContador() + 1);
        resposta.getAlunos().add(aluno);
        aluno.getRespostas().add(resposta);
        alunoService.salvar(aluno);
        respostaRepository.save(resposta);
        return RespostaMapper.toDto(resposta);
    }

    private List<Resposta> criarRespostas() {
        return List.of(
                new Resposta("==", false, perguntaService.buscarPorId(1)),
                new Resposta("!=", true, perguntaService.buscarPorId(1)),
                new Resposta("!", false, perguntaService.buscarPorId(1)),
                new Resposta("?", false, perguntaService.buscarPorId(1)),

                new Resposta("A quantidade de bananas é menor que 5!", false, perguntaService.buscarPorId(2)),
                new Resposta("A quantidade de bananas é igual a 5!", false, perguntaService.buscarPorId(2)),
                new Resposta("A quantidade de bananas é diferente que 5", true, perguntaService.buscarPorId(2)),
                new Resposta("Nenhuma das anteriores", false, perguntaService.buscarPorId(2)),

                new Resposta("Iteração para contar caracteres", false, perguntaService.buscarPorId(3)),
                new Resposta("Verificação do comprimento da string", true, perguntaService.buscarPorId(3)),
                new Resposta("Loop para verificar repetidamente", false, perguntaService.buscarPorId(3)),
                new Resposta("Avaliação da complexidade da senha", false, perguntaService.buscarPorId(3)),

                new Resposta("luaCheia", true, perguntaService.buscarPorId(4)),
                new Resposta("luaCheia == false", false, perguntaService.buscarPorId(4)),
                new Resposta("luaCheia != true", false, perguntaService.buscarPorId(4)),
                new Resposta("luaCheia == false", false, perguntaService.buscarPorId(4)),

                new Resposta("temperatura < 30", false, perguntaService.buscarPorId(5)),
                new Resposta("temperatura >= 30", true, perguntaService.buscarPorId(5)),
                new Resposta("temperatura <= 30", false, perguntaService.buscarPorId(5)),
                new Resposta("temperatura != 30", false, perguntaService.buscarPorId(5)),

                new Resposta("alturaArvore <= 15", false, perguntaService.buscarPorId(6)),
                new Resposta("alturaArvore < 15", false, perguntaService.buscarPorId(6)),
                new Resposta("alturaArvore > 15", true, perguntaService.buscarPorId(6)),
                new Resposta("alturaArvore == 15", false, perguntaService.buscarPorId(6)),

                new Resposta("frutaComestivel != true", false, perguntaService.buscarPorId(7)),
                new Resposta("frutaComestivel == false", false, perguntaService.buscarPorId(7)),
                new Resposta("frutaComestivel == true", true, perguntaService.buscarPorId(7)),
                new Resposta("frutaComestivel != false", false, perguntaService.buscarPorId(7)),

                new Resposta("=", false, perguntaService.buscarPorId(8)),
                new Resposta("-", false, perguntaService.buscarPorId(8)),
                new Resposta("==", true, perguntaService.buscarPorId(8)),
                new Resposta("*", false, perguntaService.buscarPorId(8)),

                new Resposta("Folha grande", false, perguntaService.buscarPorId(9)),
                new Resposta("Folha média", true, perguntaService.buscarPorId(9)),
                new Resposta("Folha pequena", false, perguntaService.buscarPorId(9)),
                new Resposta("Tipo de folha desconhecido", false, perguntaService.buscarPorId(9)),

                new Resposta("Dança do fogo", false, perguntaService.buscarPorId(10)),
                new Resposta("Dança da chuva", false, perguntaService.buscarPorId(10)),
                new Resposta("Dança do sol", true, perguntaService.buscarPorId(10)),
                new Resposta("Tipo de dança desconhecido", false, perguntaService.buscarPorId(10)),

                new Resposta("Bananas", true, perguntaService.buscarPorId(11)),
                new Resposta("Maçãs", false, perguntaService.buscarPorId(11)),
                new Resposta("Mangas", false, perguntaService.buscarPorId(11)),
                new Resposta("Fruta desconhecida", false, perguntaService.buscarPorId(11)),

                new Resposta("temperatura < 30", false, perguntaService.buscarPorId(12)),
                new Resposta("temperatura >= 30", true, perguntaService.buscarPorId(12)),
                new Resposta("temperatura == 30", false, perguntaService.buscarPorId(12)),
                new Resposta("temperatura != 30", false, perguntaService.buscarPorId(12)),

                new Resposta("Futebol", false, perguntaService.buscarPorId(13)),
                new Resposta("Basquete", false, perguntaService.buscarPorId(13)),
                new Resposta("Vôlei", true, perguntaService.buscarPorId(13)),
                new Resposta("Jogo desconhecido", false, perguntaService.buscarPorId(13))
        );
    }

//    public List<RespostaListagemDTO> buscarPorIdAluno (Integer idAluno) {
//        Aluno aluno = alunoService.listarUmPorId(idAluno).get();
//        List<Resposta> respostas = respostaRepository.findByAlunos(aluno);
//        return RespostaMapper.toDto(respostas);
//    }

//    public List<RespostaListagemDTO> listarAlunoQueMaisErrou () {
//        List<Pergunta> perguntas = perguntaRepository.findAll();
//        perguntas.sort((r1, r2) -> r2.getContador().compareTo(r1.getContador()));
//
//
//
//        return RespostaMapper.toDto(perguntas);
//    }
//
//    public List<RespostaListagemDTO> listarAlunosDeUmaTurmaPorRespostasCorretas(Integer idTurma) {
//        List<Resposta> respostas = respostaRepository.findAll();
//        Turma turma = turmaService.buscarPorId(idTurma);
//        List<Resposta> r = new ArrayList<>();
//        for (Resposta resposta : respostas) {
//            if (resposta.getCorreta() && resposta.getAlunos().get(0).getTurma().getIdTurma().equals(turma.getIdTurma())) {
//                r.add(resposta);
//            }
//        }
//        //metodo para organizar por quantidade de respostas corretas
//        r.sort((r1, r2) -> r2.getContador().compareTo(r1.getContador()));
//        return RespostaMapper.toDto(r);
//    }
}
