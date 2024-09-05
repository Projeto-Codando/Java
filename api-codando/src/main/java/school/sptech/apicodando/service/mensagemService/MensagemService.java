package school.sptech.apicodando.service.mensagemService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import school.sptech.apicodando.api.domain.mensagem.repository.MensagemRepository;

@Service
@RequiredArgsConstructor
public class MensagemService {

    private final MensagemRepository mensagemRepository;

}
