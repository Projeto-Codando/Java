package school.sptech.apicodando.service.mensagemService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import school.sptech.apicodando.api.domain.mensagem.Mensagem;
import school.sptech.apicodando.api.domain.mensagem.repository.MensagemRepository;
import school.sptech.apicodando.api.mapper.MensagemMapper;
import school.sptech.apicodando.service.mensagemService.dto.MensagemCadastroDTO;

@Service
@RequiredArgsConstructor
public class MensagemService {

    private final MensagemRepository mensagemRepository;

    public Mensagem criar(MensagemCadastroDTO mensagem) {
        Mensagem mensagemEntity = MensagemMapper.toEntity(mensagem);
        return mensagemRepository.save(mensagemEntity);
    }

}
