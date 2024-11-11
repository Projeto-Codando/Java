package school.sptech.apicodando.service.perguntaService;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import school.sptech.apicodando.api.controller.RespostaController;
import school.sptech.apicodando.api.domain.aluno.Aluno;
import school.sptech.apicodando.api.domain.aluno.repository.AlunoRepository;
import school.sptech.apicodando.api.domain.aula.Aula;
import school.sptech.apicodando.api.domain.pergunta.Pergunta;
import school.sptech.apicodando.api.domain.pergunta.repository.PerguntaRepository;
import school.sptech.apicodando.api.domain.resposta.Resposta;
import school.sptech.apicodando.api.domain.resposta.repository.RespostaRepository;
import school.sptech.apicodando.api.mapper.PerguntaMapper;
import school.sptech.apicodando.service.aulaService.AulaService;
import school.sptech.apicodando.service.perguntaService.dto.PerguntaCadastroDTO;
import school.sptech.apicodando.service.perguntaService.dto.PerguntaListagemDTO;
import school.sptech.apicodando.service.respostaService.RespostaService;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PerguntaService {

    private final PerguntaRepository perguntaRepository;
    //    private final RespostaService respostaService;
    private final RespostaRepository respostaRepository;
    private final AlunoRepository alunoRepository;
    private final AulaService aulaService;


    public List<Integer> inserirDadosIniciaisSeNecessario(List<Integer> idsAulas) {
        if (idsAulas != null) {
            List<Pergunta> perguntas = List.of(
                    new Pergunta("Em uma aventura na selva, um macaco curioso encontrou uma árvore mágica cheia de frutas diferentes. Para saber quais frutas ele ainda não experimentou, ele precisa comparar se as duas frutas são diferentes. Qual símbolo ele deve usar para fazer essa comparação?", aulaService.buscarPorId(idsAulas.get(0))),
                    new Pergunta("Em uma aventura na floresta, um grupo de macacos encontra uma árvore carregada de bananas. Eles estão usando um código JavaScript para decidir o que fazer com as bananas:", aulaService.buscarPorId(idsAulas.get(0))),
                    new Pergunta("Você está ajudando um grupo de macacos programadores a desenvolver um sistema de acesso a uma caverna misteriosa na floresta. Para garantir a segurança, eles precisam verificar se a senha inserida pelo explorador tem pelo menos oito caracteres. Qual seria a melhor estrutura para realizar essa verificação?", aulaService.buscarPorId(idsAulas.get(0))),
                    new Pergunta("Em uma aventura noturna, os macacos precisam determinar se a lua está cheia para realizar um ritual especial. Eles têm um sensor que retorna o valor true se a lua estiver cheia e false caso contrário. Como os macacos podem usar uma estrutura de if para verificar se a lua está cheia e imprimir 'A lua está cheia!'?", aulaService.buscarPorId(idsAulas.get(0))),
                    new Pergunta("Os macacos querem verificar se a temperatura está acima de 30 graus para decidir se vão nadar no rio. Eles possuem uma variável chamada temperatura. Qual estrutura de if é adequada para essa verificação?", aulaService.buscarPorId(idsAulas.get(0))),

                    new Pergunta("Os macacos precisam verificar se a altura de uma árvore é maior que 15 metros para escolher a árvore certa para a competição. Eles possuem uma variável alturaArvore. Qual estrutura de if usariam?", aulaService.buscarPorId(idsAulas.get(1))),
                    new Pergunta("Um macaco curioso está testando diferentes tipos de frutas para ver quais são comestíveis. Ele tem uma variável frutaComestivel que retorna true se a fruta for comestível e false caso contrário. Como ele pode usar uma estrutura de if para verificar se a fruta é comestível e imprimir 'A fruta é comestível!'?", aulaService.buscarPorId(idsAulas.get(1))),
                    new Pergunta("Na floresta, os macacos estão aprendendo a linguagem dos pássaros para se comunicarem melhor. Para entender os sons dos pássaros, eles precisam identificar se dois cantos são iguais. Qual símbolo eles usam para fazer essa comparação?", aulaService.buscarPorId(idsAulas.get(1))),
                    new Pergunta("Os macacos estão classificando diferentes tipos de folhas que encontram na selva. Eles usam um código onde 1 representa folhas grandes, 2 representa folhas médias e 3 representa folhas pequenas. Como eles podem usar um switch case para imprimir o tipo de folha?", aulaService.buscarPorId(idsAulas.get(1))),

                    new Pergunta("Durante uma celebração na floresta, os macacos querem decidir que tipo de dança fazer. Eles têm uma variável tipoDanca onde a representa a dança do fogo, b representa a dança da chuva, e c representa a dança do sol. Qual switch case eles usariam?", aulaService.buscarPorId(idsAulas.get(2))),
                    new Pergunta("Os macacos estão escolhendo frutas para um banquete. Eles têm uma variável fruta onde 1 representa bananas, 2 representa maçãs, e 3 representa mangas. Qual switch case eles usariam para imprimir a fruta escolhida?", aulaService.buscarPorId(idsAulas.get(2))),
                    new Pergunta("Os macacos estão decidindo a cor das flores para decorar suas casas. Eles têm uma variável corFlor onde red representa flores vermelhas, blue representa flores azuis, e yellow representa flores amarelas. Como usariam um switch case para decidir a cor?", aulaService.buscarPorId(idsAulas.get(2))),
                    new Pergunta("Em um jogo de esportes na selva, os macacos precisam decidir qual jogo jogar baseado no valor da variável jogo, onde 1 representa futebol, 2 representa basquete, e 3 representa vôlei. Qual switch case eles usariam?", aulaService.buscarPorId(idsAulas.get(2)))
            );
            perguntaRepository.saveAll(perguntas);
            System.out.println("Dados iniciais das perguntas inseridos.");
            return perguntas.stream().map(Pergunta::getId).collect(Collectors.toList());
        }
        System.out.println("Dado da pergunta ja inserido.");
        return new ArrayList<>();
    }

    public Pergunta criar(PerguntaCadastroDTO perguntaCadastroDTO) {

        if (perguntaCadastroDTO == null) {
            throw new RuntimeException("Pergunta não informada");
        }

        if (perguntaCadastroDTO.getIdAula() == null) {
            throw new RuntimeException("Aula não informada");
        }

        Aula aula = aulaService.buscarPorId(perguntaCadastroDTO.getIdAula());
        Pergunta pergunta = PerguntaMapper.toEntity(perguntaCadastroDTO, aula);
        perguntaRepository.save(pergunta);
        return pergunta;
    }

    public List<Pergunta> buscarPorIdAula(Integer idAula) {
        List<Pergunta> perguntas = perguntaRepository.findByAula_Id(idAula);
        return perguntas;
    }

    public Pergunta buscarPorId(Integer idPergunta) {
        Pergunta pergunta = perguntaRepository.findById(idPergunta).orElseThrow(()
                -> new RuntimeException("Pergunta não encontrada"));
        return pergunta;
    }

    public void deletar(Integer id) {
        perguntaRepository.deleteById(id);
    }

    public Pergunta atualizar(Integer id, PerguntaCadastroDTO perguntaCadastroDTO) {
        Pergunta pergunta = perguntaRepository.findById(id).orElseThrow(()
                -> new RuntimeException("Pergunta não encontrada"));
        pergunta.setTexto(perguntaCadastroDTO.getTexto());
//        pergunta.setResposta(perguntaCadastroDTO.getResposta());

        perguntaRepository.save(pergunta);
        return pergunta;
    }

    public List<Pergunta> listarPerguntasComMaisErros() {
        System.out.println("                                                        PERGUNTA SERVICE");
        List<Pergunta> perguntas = perguntaRepository.findAll();
        List<Pergunta> perguntasComMaisErros = new ArrayList<>();

        for (Pergunta pergunta : perguntas) {
            System.out.println(" ");
            System.out.println("PERGUNTA ATUAL: " + pergunta.getTexto());
            System.out.println("ID PERGUNTA ATUAL: " + pergunta.getId());

            List<Resposta> respostas = respostaRepository.findByPergunta_Id(pergunta.getId());
            int acertos = 0;
            int erros = 0;


            for (Aluno aluno : alunoRepository.findAll()) {
                boolean acertou = false;

                for (Resposta resposta : respostas) {
                    if (resposta.getAlunos().contains(aluno)) {
                    System.out.println("RESPOSTA ATUAL: " + resposta.getTexto());
                    System.out.println("ID RESPOSTA ATUAL: " + resposta.getIdResposta());

                        if (resposta.getCorreta() && !acertou) {
                            acertos++;
//                            acertos += resposta.getAlunos().size();
                            acertou = true;
                        } else  {
//                            erros++;
                            erros += resposta.getTentativasIncorretas();
                        }
                    }
                }
            }

            int totalTentativas = acertos + erros;
            System.out.println("PERGUNTA CONTADOR DENTRO DA SERVICE: " + pergunta.getContador());
            System.out.println("PERGUNTA TENTATIVAS INCORRETAS DENTRO DA SERVICE: " + pergunta.getTentativasIncorretas());
            double porcentagemCorretas = totalTentativas > 0 ? (acertos * 100.0) / totalTentativas : 0;
            double porcentagemIncorretas = 100 - porcentagemCorretas;

            System.out.printf("Pergunta ID: %d, Acertos: %d, Erros: %d, Corretas: %.2f%%, Incorretas: %.2f%%%n",
                    pergunta.getId(), acertos, erros, porcentagemCorretas, porcentagemIncorretas);

//            if (erros >= acertos && !perguntasComMaisErros.contains(pergunta) && erros!=0) {
            if (erros > 0) {
                perguntasComMaisErros.add(pergunta);
            }
        }

        for (int i = 0; i < perguntasComMaisErros.size(); i++) {
                for (int j = 0; j < perguntasComMaisErros.size(); j++) {
                    if (perguntasComMaisErros.get(i).getContador() == perguntasComMaisErros.get(j).getContador()) {
                        continue;
                    }
                    if (perguntasComMaisErros.get(i).getContador() > perguntasComMaisErros.get(j).getContador()) {
                        Pergunta aux = perguntasComMaisErros.get(i);
                        perguntasComMaisErros.set(i, perguntasComMaisErros.get(j));
                        perguntasComMaisErros.set(j, aux);
                    }
                }
            }

        perguntasComMaisErros.sort(Comparator.comparingInt(Pergunta::getTentativasIncorretas).reversed());

        return perguntasComMaisErros;
        }
    }
//        List<Pergunta> perguntas = perguntaRepository.findAll();
//        List<Pergunta> perguntasComMaisErros = new ArrayList<>();
//
//        for (Pergunta pergunta : perguntas) {
//            System.out.println("Contador de Perguntas (tentativas): " + pergunta.getContador());
//
//            // Inicialize o contador de tentativas se estiver nulo
//            if (pergunta.getContador() == null) {
//                pergunta.setContador(0);
//            }
//
//            // Obtenha todas as respostas para a pergunta
//            List<Resposta> respostas = respostaRepository.findByPergunta_Id(pergunta.getId());
//
//            // Contadores para acertos e erros
//            int acertos = 0;
//            int erros = 0;
//
//            // Contabilize acertos e erros
//            for (Resposta resposta : respostas) {
//
//                if (pergunta.getContador() > 0 && !resposta.getCorreta()) {
////                    if (!perguntasComMaisErros.contains(pergunta)) {
////                        System.out.println("                                     LISTA PERGUNTA COM MAIS ERROS: " + pergunta.getContadorIncorretas());
////                        perguntasComMaisErros.add(pergunta);
////                    }
////                }
//                if (resposta.getCorreta()) {
//                    acertos++;
//                    System.out.println("ACERTOS: " + acertos);
//                } else {
//                    erros++;
//                    System.out.println("ACERTOS: " + acertos);
//                }
//            }
//
//            // Atualize o contador de tentativas e limite para 4
//            if (pergunta.getContador() < 4) {
//                pergunta.setContador(pergunta.getContador() + 1);
//            }
//
//            // Calcule a média de acertos e erros
//            int totalTentativas = acertos + erros;
//            double porcentagemCorretas = totalTentativas > 0 ? (acertos * 100.0) / totalTentativas : 0;
//            double porcentagemIncorretas = 100 - porcentagemCorretas;
//
//            // Exibir para depuração
//            System.out.printf("Pergunta ID: %d, Acertos: %d, Erros: %d, Corretas: %.2f%%, Incorretas: %.2f%%%n",
//                    pergunta.getId(), acertos, erros, porcentagemCorretas, porcentagemIncorretas);
//
//            // Atualize a lista de perguntas com mais erros
//            if (erros > acertos && !perguntasComMaisErros.contains(pergunta)) {
//                perguntasComMaisErros.add(pergunta);
//            }
//            for (int i = 0; i < perguntasComMaisErros.size(); i++) {
//                for (int j = 0; j < perguntasComMaisErros.size(); j++) {
//                    if (perguntasComMaisErros.get(i).getContador() == perguntasComMaisErros.get(j).getContador()) {
//                        continue;
//                    }
//                    if (perguntasComMaisErros.get(i).getContador() > perguntasComMaisErros.get(j).getContador()) {
//                        Pergunta aux = perguntasComMaisErros.get(i);
//                        perguntasComMaisErros.set(i, perguntasComMaisErros.get(j));
//                        perguntasComMaisErros.set(j, aux);
//                    }
//                }
//            }
//
////            // Salvar as atualizações de contador e estatísticas no banco (se necessário)
////            perguntaRepository.save(pergunta);
//
//
//        return perguntasComMaisErros;


//}
//        List<Pergunta> perguntasComMaisErros = new ArrayList<>();
//
//
//        for (Pergunta pergunta : perguntas) {
//            System.out.println("Contador de Perguntas (tentativas): " + pergunta.getContador());
//            if (pergunta.getContador() == null) {
//                pergunta.setContador(0);
//            } //TODO trocar para tipo primitivo.
//            List<Resposta> respostas = respostaRepository.findByPergunta_Id(pergunta.getId());
//            for (Resposta resposta : respostas) {
//                if (pergunta.getContador() > 0 && !resposta.getCorreta()) {
//                    if (!perguntasComMaisErros.contains(pergunta)) {
//                        System.out.println("                                     LISTA PERGUNTA COM MAIS ERROS: " + pergunta.getContadorIncorretas());
//                        perguntasComMaisErros.add(pergunta);
//                    }
//                }
//            }
//        }
//        //parte responsavel por organizar o array por ordem de quantidade de erros
//        for (int i = 0; i < perguntasComMaisErros.size(); i++) {
//            for (int j = 0; j < perguntasComMaisErros.size(); j++) {
//                if (perguntasComMaisErros.get(i).getContador() == perguntasComMaisErros.get(j).getContador()) {
//                    continue;
//                }
//                if (perguntasComMaisErros.get(i).getContador() > perguntasComMaisErros.get(j).getContador()) {
//                    Pergunta aux = perguntasComMaisErros.get(i);
//                    perguntasComMaisErros.set(i, perguntasComMaisErros.get(j));
//                    perguntasComMaisErros.set(j, aux);
//                }
//            }
//        }
//        return perguntasComMaisErros;
//    }


//}

