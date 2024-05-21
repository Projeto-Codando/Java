package school.sptech.apicodando.service.temaService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import school.sptech.apicodando.api.domain.modulo.repository.ModuloRepository;
import school.sptech.apicodando.api.domain.tema.Tema;
import school.sptech.apicodando.api.domain.tema.repository.TemaRepository;
import school.sptech.apicodando.service.temaService.dto.TemaCadastroDTO;
import school.sptech.apicodando.service.temaService.dto.TemaListagemDTO;
import school.sptech.apicodando.service.temaService.dto.TemaMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TemaService {

    public final TemaRepository temaRepository;
    public final ModuloRepository moduloRepository;


    public Tema criar(TemaCadastroDTO dto, int moduloId) {
        if (!moduloRepository.existsById(moduloId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        Tema tema = TemaMapper.toEntity(dto);
        tema.setModulo(moduloRepository.findById(moduloId).get());
        return temaRepository.save(tema);
    }

    public List<TemaListagemDTO> listar(){
        return TemaMapper.toDto(temaRepository.findAll());
    }


}
