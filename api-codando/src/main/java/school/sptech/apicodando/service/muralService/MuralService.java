package school.sptech.apicodando.service.muralService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import school.sptech.apicodando.api.domain.mural.Mural;
import school.sptech.apicodando.api.domain.mural.repository.MuralRepository;
import school.sptech.apicodando.service.muralService.dto.MuralCadastroDTO;

@Service
@RequiredArgsConstructor
public class MuralService {

    private final MuralRepository muralRepository;

//    public Mural criar(MuralCadastroDTO mural) {
////        Mural muralEntity = MuralMapper.toEntity(mural);
////        return muralRepository.save(muralEntity);
//    }

}
