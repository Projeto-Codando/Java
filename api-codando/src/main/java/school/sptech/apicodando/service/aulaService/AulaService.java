package school.sptech.apicodando.service.aulaService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import school.sptech.apicodando.api.domain.aula.Aula;
import school.sptech.apicodando.api.domain.aula.repository.AulaRepository;
import school.sptech.apicodando.api.domain.grade.repository.GradeRepository;
import school.sptech.apicodando.api.domain.modulo.repository.ModuloRepository;
import school.sptech.apicodando.api.domain.tema.repository.TemaRepository;
import school.sptech.apicodando.api.mapper.ModuloMapper;
import school.sptech.apicodando.api.mapper.TemaMapper;
import school.sptech.apicodando.service.aulaService.dto.AulaCriacaoDTO;
import school.sptech.apicodando.api.mapper.AulaMapper;
import school.sptech.apicodando.service.aulaService.dto.AulaListagemDTO;
import school.sptech.apicodando.service.moduloService.ModuloService;
import school.sptech.apicodando.service.moduloService.dto.ModuloListagemDTO;
import school.sptech.apicodando.service.temaService.TemaService;
import school.sptech.apicodando.service.temaService.dto.TemaListagemDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AulaService {

    private final AulaRepository aulaRepository;
    private final TemaRepository temaRepository;
    private final ModuloRepository moduloRepository;
    private final GradeRepository  gradeRepository;
//    private final ModuloService moduloService;
//    private final TemaService temaService;

    public List<Aula> listarAulas() {
        return aulaRepository.findAll();
    }

    public List<AulaListagemDTO> listarAulasPorTema(UUID idTema) {

        List<Aula> aulas = aulaRepository.findAllByTema_IdTema(idTema);

        if (aulas.isEmpty()) {
            return new ArrayList<>();
        }

        return AulaMapper.toDto(aulas);
    }

    public Aula criar(AulaCriacaoDTO novaAula) {

        if (!temaRepository.existsById(novaAula.getTemaId())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tema não encontrado.");
        }

        Aula aula = AulaMapper.toEntity(novaAula);
        aula.setTema(temaRepository.findById(novaAula.getTemaId()).get());
        return aulaRepository.save(aula);
    }

    public Aula buscarPorId(UUID id) {
        return aulaRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Integer getPontuacaoMaxima(UUID idAula) {
        return aulaRepository.findById(idAula).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)).getPontuacaoMaxima();
    }

    public List<AulaListagemDTO> listarAulasPorGrade(UUID idGrade) {


        if (gradeRepository.existsById(idGrade)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Grade não encontrada.");
        }

        List<ModuloListagemDTO> modulos = ModuloMapper.toDto(moduloRepository.findAllByGrade_IdGrade(idGrade));


        List<Aula> aulas = new ArrayList<>();

        for (ModuloListagemDTO modulo : modulos) {
            List<TemaListagemDTO> temas = TemaMapper.toDto(temaRepository.findAllByModulo_IdModulo(modulo.getIdModulo()));
            for (TemaListagemDTO tema : temas) {
                aulas.addAll(aulaRepository.findAllByTema_IdTema(tema.getIdTema()));
            }
        }
        return AulaMapper.toDto(aulas);
    }
}
