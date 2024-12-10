package school.sptech.apicodando.service.respostaService;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.stereotype.Service;
import school.sptech.apicodando.api.domain.aluno.Aluno;
import school.sptech.apicodando.api.domain.aluno.repository.AlunoRepository;
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
    private final AlunoRepository alunoService;
//    private final PerguntaRepository perguntaRepository;
//    private final TurmaService turmaService;
//    private final PerguntaRepository perguntaRepository;


    public void inserirDadosIniciaisSeNecessario(List<Integer> idsPerguntas) {
        if (idsPerguntas != null) {
            respostaRepository.saveAll(criarRespostas(idsPerguntas));
            System.out.println("Dado inicial da resposta inserido.");
        } else {
            System.out.println("Dado da resposta ja inserido.");
        }
    }

    public Resposta criar(RespostaCadastroDTO respostaCadastroDTO) {
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

    public Resposta buscarPorId(Integer idResposta) {
        Resposta resposta = respostaRepository.findById(idResposta).orElseThrow(()
                -> new RuntimeException("Resposta não encontrada"));
        return resposta;
    }

    public List<Resposta> buscarPorIdPergunta(Integer idPergunta) {
        return respostaRepository.findByPergunta_Id(idPergunta);

    }

    public RespostaListagemDTO incrementarContador(Integer idResposta, Integer idAluno) {
        Resposta resposta = buscarPorId(idResposta);
        Aluno aluno = alunoService.findById(idAluno).orElseThrow(() -> new RuntimeException("Aluno não encontrado"));
        Pergunta pergunta = resposta.getPergunta();

        if (resposta.getAlunos().contains(aluno)) {
            throw new RuntimeException("Aluno já respondeu essa pergunta");
        }

        resposta.getAlunos().add(aluno);
        aluno.getRespostas().add(resposta);

        Integer idRespostaCorreta = pergunta.getRespostas()
                .stream()
                .filter(Resposta::getCorreta)
                .map(Resposta::getIdResposta)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Nenhuma resposta correta encontrada para esta pergunta"));

        boolean acertou = verificarRespostaAluno(idResposta, idRespostaCorreta);

        resposta.registrarTentativa(acertou);

        int totalTentativas = resposta.getContador();
        pergunta.setContador(pergunta.getContador() + totalTentativas);

        RespostaListagemDTO respostaDTO = RespostaMapper.toDto(resposta);
        respostaDTO.setTentativasIncorretas(resposta.getTentativasIncorretas());
        pergunta.setTentativasIncorretas(resposta.getTentativasIncorretas());
        respostaDTO.setContador(pergunta.getContador());

        respostaRepository.save(resposta);
        alunoService.save(aluno);

        return respostaDTO;
    }

    public boolean verificarRespostaAluno(Integer idRespostaEscolhida, Integer idRespostaCorreta) {
        return idRespostaEscolhida.equals(idRespostaCorreta);
    }

    private List<Resposta> criarRespostas(List<Integer> idsPerguntas) {

        return List.of(
    new Resposta("==", false, perguntaService.buscarPorId(idsPerguntas.get(0)), 0),
    new Resposta("!=", true, perguntaService.buscarPorId(idsPerguntas.get(0)), 0),
    new Resposta("!", false, perguntaService.buscarPorId(idsPerguntas.get(0)), 0),
    new Resposta("?", false, perguntaService.buscarPorId(idsPerguntas.get(0)), 0),

    new Resposta("A quantidade de bananas é menor que 5!", false, perguntaService.buscarPorId(idsPerguntas.get(1)), 0),
    new Resposta("A quantidade de bananas é igual a 5!", false, perguntaService.buscarPorId(idsPerguntas.get(1)), 0),
    new Resposta("A quantidade de bananas é diferente que 5", true, perguntaService.buscarPorId(idsPerguntas.get(1)), 0),
    new Resposta("Nenhuma das anteriores", false, perguntaService.buscarPorId(idsPerguntas.get(1)), 0),

    new Resposta("Iteração para contar caracteres", false, perguntaService.buscarPorId(idsPerguntas.get(2)), 0),
    new Resposta("Verificação do comprimento da string", true, perguntaService.buscarPorId(idsPerguntas.get(2)), 0),
    new Resposta("Loop para verificar repetidamente", false, perguntaService.buscarPorId(idsPerguntas.get(2)), 0),
    new Resposta("Avaliação da complexidade da senha", false, perguntaService.buscarPorId(idsPerguntas.get(2)), 0),

    new Resposta("luaCheia", true, perguntaService.buscarPorId(idsPerguntas.get(3)), 0),
    new Resposta("luaCheia == false", false, perguntaService.buscarPorId(idsPerguntas.get(3)), 0),
    new Resposta("luaCheia != true", false, perguntaService.buscarPorId(idsPerguntas.get(3)), 0),
    new Resposta("luaCheia =! false", false, perguntaService.buscarPorId(idsPerguntas.get(3)), 0),

    new Resposta("temperatura < 30", false, perguntaService.buscarPorId(idsPerguntas.get(4)), 0),
    new Resposta("temperatura >= 30", true, perguntaService.buscarPorId(idsPerguntas.get(4)), 0),
    new Resposta("temperatura <= 30", false, perguntaService.buscarPorId(idsPerguntas.get(4)), 0),
    new Resposta("temperatura != 30", false, perguntaService.buscarPorId(idsPerguntas.get(4)), 0),

    new Resposta("alturaArvore <= 15", false, perguntaService.buscarPorId(idsPerguntas.get(5)), 0),
    new Resposta("alturaArvore < 15", false, perguntaService.buscarPorId(idsPerguntas.get(5)), 0),
    new Resposta("alturaArvore > 15", true, perguntaService.buscarPorId(idsPerguntas.get(5)), 0),
    new Resposta("alturaArvore == 15", false, perguntaService.buscarPorId(idsPerguntas.get(5)), 0),

    new Resposta("frutaComestivel != true", false, perguntaService.buscarPorId(idsPerguntas.get(6)), 0),
    new Resposta("frutaComestivel == false", false, perguntaService.buscarPorId(idsPerguntas.get(6)), 0),
    new Resposta("frutaComestivel == true", true, perguntaService.buscarPorId(idsPerguntas.get(6)), 0),
    new Resposta("frutaComestivel != false", false, perguntaService.buscarPorId(idsPerguntas.get(6)), 0),

    new Resposta("=", false, perguntaService.buscarPorId(idsPerguntas.get(7)), 0),
    new Resposta("-", false, perguntaService.buscarPorId(idsPerguntas.get(7)), 0),
    new Resposta("==", true, perguntaService.buscarPorId(idsPerguntas.get(7)), 0),
    new Resposta("*", false, perguntaService.buscarPorId(idsPerguntas.get(7)), 0),

    new Resposta("Folha grande", false, perguntaService.buscarPorId(idsPerguntas.get(8)), 0),
    new Resposta("Folha média", true, perguntaService.buscarPorId(idsPerguntas.get(8)), 0),
    new Resposta("Folha pequena", false, perguntaService.buscarPorId(idsPerguntas.get(8)), 0),
    new Resposta("Tipo de folha desconhecido", false, perguntaService.buscarPorId(idsPerguntas.get(8)), 0),

    new Resposta("Dança do fogo", false, perguntaService.buscarPorId(idsPerguntas.get(9)), 0),
    new Resposta("Dança da chuva", false, perguntaService.buscarPorId(idsPerguntas.get(9)), 0),
    new Resposta("Dança do sol", true, perguntaService.buscarPorId(idsPerguntas.get(9)), 0),
    new Resposta("Tipo de dança desconhecido", false, perguntaService.buscarPorId(idsPerguntas.get(9)), 0),

    new Resposta("Bananas", true, perguntaService.buscarPorId(idsPerguntas.get(10)), 0),
    new Resposta("Maçãs", false, perguntaService.buscarPorId(idsPerguntas.get(10)), 0),
    new Resposta("Mangas", false, perguntaService.buscarPorId(idsPerguntas.get(10)), 0),
    new Resposta("Fruta desconhecida", false, perguntaService.buscarPorId(idsPerguntas.get(10)), 0),

    new Resposta("temperatura < 30", false, perguntaService.buscarPorId(idsPerguntas.get(11)), 0),
    new Resposta("temperatura >= 30", true, perguntaService.buscarPorId(idsPerguntas.get(11)), 0),
    new Resposta("temperatura == 30", false, perguntaService.buscarPorId(idsPerguntas.get(11)), 0),
    new Resposta("temperatura != 30", false, perguntaService.buscarPorId(idsPerguntas.get(11)), 0),

    new Resposta("Futebol", false, perguntaService.buscarPorId(idsPerguntas.get(12)), 0),
    new Resposta("Basquete", false, perguntaService.buscarPorId(idsPerguntas.get(12)), 0),
    new Resposta("Vôlei", true, perguntaService.buscarPorId(idsPerguntas.get(12)), 0),
    new Resposta("Jogo desconhecido", false, perguntaService.buscarPorId(idsPerguntas.get(12)), 0),

    new Resposta("18", false, perguntaService.buscarPorId(idsPerguntas.get(13)), 0),
    new Resposta("12", true, perguntaService.buscarPorId(idsPerguntas.get(13)), 0),
    new Resposta("10", false, perguntaService.buscarPorId(idsPerguntas.get(13)), 0),
    new Resposta("3", false, perguntaService.buscarPorId(idsPerguntas.get(13)), 0),

    new Resposta("12", false, perguntaService.buscarPorId(idsPerguntas.get(14)), 0),
    new Resposta("10", false, perguntaService.buscarPorId(idsPerguntas.get(14)), 0),
    new Resposta("13", true, perguntaService.buscarPorId(idsPerguntas.get(14)), 0),
    new Resposta("15", false, perguntaService.buscarPorId(idsPerguntas.get(14)), 0),

    new Resposta("9 metros", false, perguntaService.buscarPorId(idsPerguntas.get(15)), 0),
    new Resposta("11 metros", true, perguntaService.buscarPorId(idsPerguntas.get(15)), 0),
    new Resposta("10 metros", false, perguntaService.buscarPorId(idsPerguntas.get(15)), 0),
    new Resposta("8 metros", false, perguntaService.buscarPorId(idsPerguntas.get(15)), 0),

    new Resposta("3", true, perguntaService.buscarPorId(idsPerguntas.get(16)), 0),
    new Resposta("4", false, perguntaService.buscarPorId(idsPerguntas.get(16)), 0),
    new Resposta("5", false, perguntaService.buscarPorId(idsPerguntas.get(16)), 0),
    new Resposta("6", false, perguntaService.buscarPorId(idsPerguntas.get(16)), 0),

    new Resposta("20", true, perguntaService.buscarPorId(idsPerguntas.get(17)), 0),
    new Resposta("15", false, perguntaService.buscarPorId(idsPerguntas.get(17)), 0),
    new Resposta("10", false, perguntaService.buscarPorId(idsPerguntas.get(17)), 0),
    new Resposta("25", false, perguntaService.buscarPorId(idsPerguntas.get(17)), 0)




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
