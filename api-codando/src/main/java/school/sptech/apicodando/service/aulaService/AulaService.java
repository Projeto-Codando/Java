package school.sptech.apicodando.service.aulaService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import school.sptech.apicodando.api.domain.aula.Aula;
import school.sptech.apicodando.api.domain.aula.repository.AulaRepository;
import school.sptech.apicodando.api.domain.tema.repository.TemaRepository;
import school.sptech.apicodando.service.aulaService.dto.AulaCriacaoDTO;
import school.sptech.apicodando.service.aulaService.dto.AulaMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AulaService {

    private final AulaRepository aulaRepository;
    private final TemaRepository temaRepository;

    public List<Aula> listarAulas() {
        return aulaRepository.findAll();
    }

    public Aula criar(AulaCriacaoDTO novaAula, int idTema) {

        if (temaRepository.findById(idTema).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        Aula aula = AulaMapper.toEntity(novaAula);
        aula.setTema(temaRepository.findById(idTema).get());
        return aulaRepository.save(aula);
    }
}
