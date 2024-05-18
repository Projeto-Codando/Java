package school.sptech.apicodando.service.turmaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import school.sptech.apicodando.api.domain.aluno.Aluno;
import school.sptech.apicodando.api.domain.turma.Turma;
import school.sptech.apicodando.api.domain.turma.repository.TurmaRepository;
import school.sptech.apicodando.api.mapper.AlunoMapper;
import school.sptech.apicodando.service.alunoService.dto.AlunoCadastroDTO;

import javax.crypto.spec.OAEPParameterSpec;
import java.util.List;
import java.util.Optional;

@Service
public class TurmaService {

    @Autowired
    private TurmaRepository turmaRepository;

    public void criar(Turma turmaCadastro) {
        if (existeTurmaByCodigo(turmaCadastro.getSenha())){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Turma já criada.");
        }
        this.turmaRepository.save(turmaCadastro);
    }

    public List<Turma> listar() {
        return this.turmaRepository.findAll();
    }

    public void excluir(int id) {
        if (!listarPorID(id).isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        this.turmaRepository.deleteById(id);
    }

    public void atualizar(Turma turmaAtualizada, int id) {
        if (!listarPorID(id).isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        turmaAtualizada.setIdTurma(id);
        this.turmaRepository.save(turmaAtualizada);
    }

    public Optional<Turma> listarPorID(int id) {
        return this.turmaRepository.findById(id);
    }

    // Metodo para verificar se a Turma já existe.
    public boolean existeTurmaByCodigo(String codigo){
        return this.turmaRepository.findBySenha(codigo).isPresent();
    }
}
