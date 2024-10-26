package school.sptech.apicodando.service.perguntaService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import school.sptech.apicodando.api.controller.RespostaController;
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
import java.util.List;

@Service
@RequiredArgsConstructor
public class PerguntaService {

    private final PerguntaRepository perguntaRepository;
//    private final RespostaService respostaService;
    private final RespostaRepository respostaRepository;
    private final AulaService aulaService;

    public Pergunta criar (PerguntaCadastroDTO perguntaCadastroDTO) {

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

    public List<Pergunta> buscarPorIdAula (Integer idAula) {
        List<Pergunta> perguntas = perguntaRepository.findByAula_Id(idAula);
        return perguntas;
    }

    public Pergunta buscarPorId (Integer idPergunta) {
        Pergunta pergunta = perguntaRepository.findById(idPergunta).orElseThrow(()
                -> new RuntimeException("Pergunta não encontrada"));
        return pergunta;
    }

    public void deletar (Integer id) {
        perguntaRepository.deleteById(id);
    }

    public Pergunta atualizar (Integer id, PerguntaCadastroDTO perguntaCadastroDTO) {
        Pergunta pergunta = perguntaRepository.findById(id).orElseThrow(()
                -> new RuntimeException("Pergunta não encontrada"));
        pergunta.setTexto(perguntaCadastroDTO.getTexto());
//        pergunta.setResposta(perguntaCadastroDTO.getResposta());

        perguntaRepository.save(pergunta);
        return pergunta;
    }

    public List<Pergunta> listarPerguntasComMaisErros(){
        List<Pergunta> perguntas = perguntaRepository.findAll();
        List<Pergunta> perguntasComMaisErros = new ArrayList<>();



        for (Pergunta pergunta : perguntas) {
            if (pergunta.getContador() == null) {
                pergunta.setContador(0);
            } //TODO trocar para tipo primitivo.
            List<Resposta> respostas = respostaRepository.findByPergunta_Id(pergunta.getId());
           for (Resposta resposta : respostas) {
               if (pergunta.getContador() > 0 && !resposta.getCorreta()) {
                   if (!perguntasComMaisErros.contains(pergunta)) {
                       perguntasComMaisErros.add(pergunta);
                   }
               }
           }
        }
        //parte responsavel por organizar o array por ordem de quantidade de erros
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
        return perguntasComMaisErros;
    }



}
