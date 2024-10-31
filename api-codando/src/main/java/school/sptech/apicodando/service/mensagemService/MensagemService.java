package school.sptech.apicodando.service.mensagemService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import school.sptech.apicodando.api.domain.mensagem.Mensagem;
import school.sptech.apicodando.api.domain.mensagem.repository.MensagemRepository;
import school.sptech.apicodando.api.domain.turma.Turma;
import school.sptech.apicodando.api.mapper.MensagemMapper;
import school.sptech.apicodando.service.mensagemService.dto.MensagemCadastroDTO;
import school.sptech.apicodando.service.mensagemService.dto.MensagemListagemDTO;
import school.sptech.apicodando.service.notificacaoService.NotificationService;
import school.sptech.apicodando.service.turmaService.TurmaService;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MensagemService {

    private final MensagemRepository mensagemRepository;
    private final TurmaService turmaService;
    private final NotificationService notificationService;

    public Mensagem criar(MensagemCadastroDTO mensagem) {
        if (!turmaService.existeTurma(mensagem.getIdTurma())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Turma não encontrada.");
        }

        Turma turma = turmaService.buscarTurma(mensagem.getIdTurma()).get();
        Mensagem mensagemEntity = MensagemMapper.toEntity(mensagem);
        mensagemEntity.setTurma(turma);
        mensagemEntity.setDataEnvio(LocalDateTime.now());

        turma.getMensagens().add(mensagemEntity);
        mensagemRepository.save(mensagemEntity);

        // Enviar notificação para os alunos da turma
        String titulo = "Nova Mensagem na Turma";
        String conteudo = mensagemEntity.getTexto();
        notificationService.sendNotificationToTurma(titulo, conteudo, turma.getIdTurma());

        return mensagemEntity;
    }


    public List<MensagemListagemDTO> exibirPorTurma (int idTurma) {
        if (!turmaService.existeTurma(idTurma)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Turma não encontrada.");
        }
        return MensagemMapper.toDto(mensagemRepository.findAllByTurmaIdTurma(idTurma));
    }

    public void deletar(int id) {
        if (!mensagemRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Mensagem não encontrada.");
        }
        mensagemRepository.deleteById(id);
    }

}
