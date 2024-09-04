package school.sptech.apicodando.service.perguntaService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import school.sptech.apicodando.api.domain.pergunta.repository.PerguntaRepository;

@Service
@RequiredArgsConstructor
public class PerguntaService {

    private final PerguntaRepository perguntaRepository;

}
