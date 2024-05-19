package school.sptech.apicodando.service.turmaService;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import school.sptech.apicodando.api.domain.escolaridade.Escolaridade;
import school.sptech.apicodando.api.domain.escolaridade.repository.EscolaridadeRepository;
import school.sptech.apicodando.api.domain.turma.Turma;
import school.sptech.apicodando.api.domain.turma.repository.TurmaRepository;
import school.sptech.apicodando.api.mapper.TurmaMapper;
import school.sptech.apicodando.service.turmaService.dto.TurmaCadastroDTO;
import school.sptech.apicodando.service.turmaService.dto.TurmaListagemDTO;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TurmaService {

    private final TurmaRepository turmaRepository;
    private final EscolaridadeRepository escolaridadeRepository;

    public void criar(TurmaCadastroDTO turmaCadastro) {
        Escolaridade escolaridade = escolaridadeRepository.findById(turmaCadastro.getFkEscolaridade())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Escolaridade não encontrada."));

        final Turma novaTurma = TurmaMapper.toEntity(turmaCadastro, escolaridade);
        novaTurma.setEscolaridade(escolaridade);

        if (existeTurmaByCodigo(turmaCadastro.getSenha())){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Turma já criada.");
        }
        turmaRepository.save(novaTurma);
    }

    public Turma desativar(int id, int idProfessor) {
        Optional<Turma> turma = listarPorIdAndProfessor(id, idProfessor);
        if (turma.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Turma não encontrada.");
        } else {
            turma.get().setStatusTurma(false);
            return this.turmaRepository.save(turma.get());
        }
    }

    public void atualizar(Turma turmaAtualizada, int id, int idProfessor){
        Optional<Turma> turma = listarPorIdAndProfessor(id, idProfessor);
        if(turma.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Turma não encontrada.");
        } else {
            turmaAtualizada.setIdTurma(id);
            this.turmaRepository.save(turmaAtualizada);
        }
    }

    public List<TurmaListagemDTO> listarTodasTurmasPorProfessor(int idProfessor) {
        List<Turma> turmas = this.listarPorProfessor(idProfessor);

        final List<TurmaListagemDTO> turmasListagem = TurmaMapper.toDto(turmas);

        if (turmas.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return turmasListagem;
    }

    public Optional<Turma> listarPorIdAndProfessor(int idTurma, int idProfessor) {
        return this.turmaRepository.findByIdTurmaAndFkEducadorIdEducador(idTurma, idProfessor);
    }

    public List<Turma> listarPorProfessor(int idProfessor) {
        return this.turmaRepository.findByFkEducadorIdEducador(idProfessor);
    }

    // Metodo para verificar se a Turma já existe.
    public boolean existeTurmaByCodigo(String codigo){
        return this.turmaRepository.findBySenha(codigo).isPresent();
    }
}
