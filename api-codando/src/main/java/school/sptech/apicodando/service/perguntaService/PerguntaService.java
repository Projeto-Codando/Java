package school.sptech.apicodando.service.perguntaService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import school.sptech.apicodando.api.domain.aula.Aula;
import school.sptech.apicodando.api.domain.pergunta.Pergunta;
import school.sptech.apicodando.api.domain.pergunta.repository.PerguntaRepository;
import school.sptech.apicodando.api.mapper.PerguntaMapper;
import school.sptech.apicodando.service.aulaService.AulaService;
import school.sptech.apicodando.service.perguntaService.dto.PerguntaCadastroDTO;
import school.sptech.apicodando.service.perguntaService.dto.PerguntaListagemDTO;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PerguntaService {

    private final PerguntaRepository perguntaRepository;
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

    public List<PerguntaListagemDTO> buscarPorIdAula (Integer idAula) {
        List<Pergunta> perguntas = perguntaRepository.findByAula_Id(idAula);
        return PerguntaMapper.toDto(perguntas);
    }

    public PerguntaListagemDTO buscarPorId (Integer idPergunta) {
        Pergunta pergunta = perguntaRepository.findById(idPergunta).orElseThrow(()
                -> new RuntimeException("Pergunta não encontrada"));
        return PerguntaMapper.toDto(pergunta);
    }

}
