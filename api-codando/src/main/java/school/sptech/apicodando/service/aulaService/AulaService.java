package school.sptech.apicodando.service.aulaService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import school.sptech.apicodando.api.domain.aula.Aula;
import school.sptech.apicodando.api.domain.aula.repository.AulaRepository;
import school.sptech.apicodando.api.domain.tema.repository.TemaRepository;
import school.sptech.apicodando.service.aulaService.dto.AulaCriacaoDTO;
import school.sptech.apicodando.api.mapper.AulaMapper;
import school.sptech.apicodando.service.aulaService.dto.AulaListagemDTO;
import school.sptech.apicodando.service.moduloService.ModuloService;
import school.sptech.apicodando.service.moduloService.dto.ModuloListagemDTO;
import school.sptech.apicodando.service.temaService.TemaService;
import school.sptech.apicodando.service.temaService.dto.TemaListagemDTO;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AulaService {

    private final AulaRepository aulaRepository;
    private final TemaRepository temaRepository;
    private final ModuloService moduloService;
    private final TemaService temaService;

    public List<Aula> listarAulas() {
        return aulaRepository.findAll();
    }

    public List<AulaListagemDTO> listarAulasPorTema(int idTema) {

        List<Aula> aulas = aulaRepository.findAllByTema_IdTema(idTema);

        if (aulas.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return AulaMapper.toDto(aulas);
    }

    public Aula criar(AulaCriacaoDTO novaAula) {

        if (temaRepository.findById(novaAula.getTemaId()).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        Aula aula = AulaMapper.toEntity(novaAula);
        aula.setTema(temaRepository.findById(novaAula.getTemaId()).get());
        return aulaRepository.save(aula);
    }

    public List<AulaListagemDTO> listarAulasPorGrade(Integer idGrade) {
        List<ModuloListagemDTO> modulos = moduloService.listarPorGrade(idGrade);
        List<Aula> aulas = new ArrayList<>();

        for (ModuloListagemDTO modulo : modulos) {
            List<TemaListagemDTO> temas = temaService.listarPorModulo(modulo.getIdModulo());
            for (TemaListagemDTO tema : temas) {
                aulas.addAll(aulaRepository.findAllByTema_IdTema(tema.getIdTema()));
            }
        }
        return AulaMapper.toDto(aulas);
    }
}
