package school.sptech.apicodando.service.aulaService;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import school.sptech.apicodando.api.domain.aula.Aula;
import school.sptech.apicodando.api.domain.aula.repository.AulaRepository;
import school.sptech.apicodando.api.domain.grade.repository.GradeRepository;
import school.sptech.apicodando.api.domain.modulo.repository.ModuloRepository;
import school.sptech.apicodando.api.domain.tema.repository.TemaRepository;
import school.sptech.apicodando.api.domain.turma.Turma;
import school.sptech.apicodando.api.domain.turma.repository.TurmaRepository;
import school.sptech.apicodando.api.mapper.ModuloMapper;
import school.sptech.apicodando.api.mapper.TemaMapper;
import school.sptech.apicodando.service.aulaService.dto.AulaCriacaoDTO;
import school.sptech.apicodando.api.mapper.AulaMapper;
import school.sptech.apicodando.service.aulaService.dto.AulaListagemDTO;
import school.sptech.apicodando.service.moduloService.ModuloService;
import school.sptech.apicodando.service.moduloService.dto.ModuloListagemDTO;
import school.sptech.apicodando.service.temaService.TemaService;
import school.sptech.apicodando.service.temaService.dto.TemaListagemDTO;
import school.sptech.apicodando.service.turmaService.TurmaService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AulaService {

    private final AulaRepository aulaRepository;
    private final TemaRepository temaRepository;
    private final ModuloRepository moduloRepository;
    private final GradeRepository gradeRepository;
    //    private final TurmaService turmaService;
    private final TurmaRepository turmaRepository;
//    private final ModuloService moduloService;
//    private final TemaService temaService;


    public List<Integer> inserirDadosIniciaisSeNecessario(Integer idTurma) {
        if (idTurma != null) {
            List<Aula> aulas = List.of(
                    new Aula("Aula 1", "Aula 1", 500, 1000,
                            temaRepository.findById(1).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tema não encontrado.")),
                            turmaRepository.findById(idTurma).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Turma não encontrada."))),
                    new Aula("If / Else", "Aula detalhada sobre a combinação das estruturas condicionais if e else, incluindo exemplos de uso em fluxos de controle.", 500, 1000,
                            temaRepository.findById(1).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tema não encontrado.")),
                            turmaRepository.findById(idTurma).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Turma não encontrada."))),
                    new Aula("Switch Case", "Aula explicativa sobre a estrutura condicional switch case, ideal para selecionar entre várias opções baseadas em uma única variável.", 500, 1000,
                            temaRepository.findById(1).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tema não encontrado.")),
                            turmaRepository.findById(idTurma).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Turma não encontrada."))),
                    new Aula("Variável", "Uma variável é um espaço de memória identificado por um nome que armazena valores que podem ser alterados durante a execução do programa.", 500, 1000,
                            temaRepository.findById(2).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tema não encontrado.")),
                            turmaRepository.findById(idTurma).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Turma não encontrada.")))
            );

            aulaRepository.saveAll(aulas);
            return aulas.stream().map(Aula::getId).collect(Collectors.toList());
        } else {
            System.out.println("Dado da aula ja inserido.");
            return new ArrayList<>();
        }
    }

    public List<Aula> listarAulas() {
        return aulaRepository.findAll();
    }

    public List<AulaListagemDTO> listarAulasPorTema(int idTema, int idTurma) {


        List<Aula> aulas = aulaRepository.findAllByTema_IdTemaAndTurma_IdTurma(idTema, idTurma);

        if (aulas.isEmpty()) {
            return new ArrayList<>();
        }

        return AulaMapper.toDto(aulas);
    }

    public List<AulaListagemDTO> listarAulasPorTema(int idTema) {


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

        if (!turmaRepository.existsById(novaAula.getTurmaId())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Turma não encontrada.");
        }

        Turma turma = turmaRepository.findById(novaAula.getTurmaId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Turma não encontrada."));
        Aula aula = AulaMapper.toEntity(novaAula);
        aula.setTema(temaRepository.findById(novaAula.getTemaId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tema não encontrado.")));
        aula.setTurma(turma);
        turma.getAulas().add(aula);
        return aulaRepository.save(aula);
    }

    public Aula buscarPorId(int id) {
        return aulaRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Integer getPontuacaoMaxima(int idAula) {
        return aulaRepository.findById(idAula).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)).getPontuacaoMaxima();
    }

    public List<AulaListagemDTO> listarAulasPorGrade(Integer idGrade) {
        if (!gradeRepository.existsById(idGrade)) {
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
