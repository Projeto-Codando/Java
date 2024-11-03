package school.sptech.apicodando.service.temaService;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import school.sptech.apicodando.api.domain.aula.Aula;
import school.sptech.apicodando.api.domain.modulo.Modulo;
import school.sptech.apicodando.api.domain.modulo.repository.ModuloRepository;
import school.sptech.apicodando.api.domain.tema.Tema;
import school.sptech.apicodando.api.domain.tema.repository.TemaRepository;
import school.sptech.apicodando.service.aulaService.AulaService;
import school.sptech.apicodando.service.aulaService.dto.AulaListagemDTO;
import school.sptech.apicodando.service.temaService.dto.TemaCadastroDTO;
import school.sptech.apicodando.service.temaService.dto.TemaListagemDTO;
import school.sptech.apicodando.api.mapper.TemaMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TemaService {

    public final TemaRepository temaRepository;
    public final ModuloRepository moduloRepository;
    private final AulaService aulaService;


    public void inserirDadosIniciaisSeNecessario() {
        if (temaRepository.count() == 0) {
            Modulo modulo = moduloRepository.findById(1).get();
            List<Tema> temas = List.of(
                    new Tema("Condicional", modulo),
                    new Tema("Laço de Repetição", modulo),
                    new Tema("Variavel", modulo)
            );
            temaRepository.saveAll(temas);
            System.out.println("Dado inicial do tema inserido.");
        } else {
            System.out.println("Dado do tema ja inserido.");
        }
    }

    private List<Tema> criarTemas() {
        return List.of(
                new Tema("Condicional"),
                new Tema("Laço de Repetição"),
                new Tema("Variáveis")
        );
    }

    public Tema criar(TemaCadastroDTO dto, int moduloId) {
        if (!moduloRepository.existsById(moduloId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Módulo não encontrado.");
        }

        Tema tema = TemaMapper.toEntity(dto);
        tema.setModulo(moduloRepository.findById(moduloId).get());

        return temaRepository.save(tema);

    }

    public List<TemaListagemDTO> listar() {
        return TemaMapper.toDto(temaRepository.findAll());
    }

    public List<TemaListagemDTO> listarPorModulo(Integer idModulo, Integer idTurma) {

        if (!moduloRepository.existsById(idModulo)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Módulo não encontrado.");
        }

        List<Tema> temas = temaRepository.findAllByModulo_IdModulo(idModulo);


        List<TemaListagemDTO> temasDto = TemaMapper.toDto(temas);

        for (TemaListagemDTO temaDto : temasDto) {
            List<AulaListagemDTO> aulasDto = aulaService.listarAulasPorTema(temaDto.getIdTema(), idTurma);
            temaDto.setAulas(aulasDto);
        }
        return temasDto;
    }
}
