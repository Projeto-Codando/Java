package school.sptech.apicodando.service.avatarService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import school.sptech.apicodando.api.domain.aluno.repository.AlunoRepository;
import school.sptech.apicodando.api.domain.avatar.Avatar;
import school.sptech.apicodando.api.domain.avatar.repository.AvatarRepository;
import school.sptech.apicodando.service.alunoService.AlunoService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AvatarService {
    private final AvatarRepository repository;
    private final AlunoService alunoService;

    public List<Avatar> listar() {
        return repository.findAll();
    }

    public Avatar buscarPorId(int id){
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Avatar não encontrado."));
    }

    public Avatar atualizarAvatar(int idAluno, int idAvatar) {
        Avatar avatar = buscarPorId(idAvatar);
        alunoService.listarUmPorId(idAluno).ifPresent(aluno -> {

            if(aluno.getAvatares().contains(avatar)){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Avatar já adquirido.");
            }

            if(aluno.getMoedas() < avatar.getPreco()){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Moedas insuficientes.");
            }

            aluno.setMoedas(aluno.getMoedas() - avatar.getPreco());
            aluno.getAvatares().add(avatar);
            alunoService.atualizar(aluno, idAluno);
        });
        return avatar;
    }

    public Avatar atualizarAvatarEscolhido(int idAluno, int idAvatar) {
        Avatar avatar = buscarPorId(idAvatar);
        alunoService.listarUmPorId(idAluno).ifPresent(aluno -> {
            if(!aluno.getAvatares().contains(avatar)){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Avatar não adquirido.");
            }
            aluno.setIdAvatar(avatar.getId());
            alunoService.atualizar(aluno, idAluno);
        });
        return avatar;
    }
}
