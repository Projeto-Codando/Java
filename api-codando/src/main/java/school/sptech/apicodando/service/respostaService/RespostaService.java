package school.sptech.apicodando.service.respostaService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import school.sptech.apicodando.api.domain.resposta.repository.RespostaRepository;

@Service
@RequiredArgsConstructor
public class RespostaService {

    private final RespostaRepository respostaRepository;

}
