package school.sptech.apicodando.service.turmaService;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import school.sptech.apicodando.api.domain.educador.Educador;
import school.sptech.apicodando.api.domain.educador.repository.EducadorRepository;
import school.sptech.apicodando.api.domain.escolaridade.Escolaridade;
import school.sptech.apicodando.api.domain.escolaridade.repository.EscolaridadeRepository;
import school.sptech.apicodando.api.domain.grade.repository.GradeRepository;
import school.sptech.apicodando.api.domain.turma.Turma;
import school.sptech.apicodando.api.domain.turma.repository.TurmaRepository;
import school.sptech.apicodando.api.mapper.TurmaMapper;
import school.sptech.apicodando.service.turmaService.dto.TurmaAtualizaDTO;
import school.sptech.apicodando.service.turmaService.dto.TurmaCadastroDTO;
import school.sptech.apicodando.service.turmaService.dto.TurmaListagemDTO;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Formatter;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TurmaService {

    private final TurmaRepository turmaRepository;
    private final EscolaridadeRepository escolaridadeRepository;
    private final EducadorRepository educadorRepository;
    private final GradeRepository gradeRepository;

    public void criar(TurmaCadastroDTO turmaCadastro) {
        Escolaridade escolaridade = escolaridadeRepository.findById(turmaCadastro.getFkEscolaridade())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Escolaridade não encontrada."));

        Educador educador = educadorRepository.findById(turmaCadastro.getFkEducador())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Educador não encontrado."));

        final Turma novaTurma = TurmaMapper.toEntity(turmaCadastro, escolaridade, educador);

        novaTurma.setGrade(gradeRepository.findAllByFkTurma_IdTurma(novaTurma.getIdTurma()));

        if (existeTurmaByCodigo(turmaCadastro.getSenha())){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Turma já criada.");
        }
        turmaRepository.save(novaTurma);
    }

    public Turma desativar(int id, int idProfessor) {
        Turma turma = buscarPorIdTurmaAndIdProfessor(id, idProfessor);
        if (turma == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Turma não encontrada.");
        } else {
            turma.setStatusTurma(false);
            return this.turmaRepository.save(turma);
        }
    }

//    public void atualizar(TurmaAtualizaDTO turmaAtualizada, int id){
//        Turma turma = buscarPorIdTurmaAndIdProfessor(id, turmaAtualizada.getEducador().getIdEducador());
//        if(turma == null){
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Turma não encontrada.");
//        } else {
//            turmaAtualizada.setIdTurma(id);
//            this.turmaRepository.save(turmaAtualizada);
//        }
//    }

    public List<TurmaListagemDTO> listarTodasTurmasPorProfessor(int idProfessor) {
        List<Turma> turmas = this.listarPorProfessor(idProfessor);

        final List<TurmaListagemDTO> turmasListagem = TurmaMapper.toDto(turmas);

        if (turmas.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return turmasListagem;
    }

    public TurmaListagemDTO listarPorIdAndProfessor(int idTurma, int idProfessor) {
        Optional<Turma> turma = this.turmaRepository.findByIdTurmaAndEducadorIdEducador(idTurma, idProfessor);

        if (turma.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Turma não encontrada.");
        }

        return TurmaMapper.toDto(turma.get());
    }

    public List<Turma> listarPorProfessor(int idProfessor) {
        return this.turmaRepository.findByEducadorIdEducador(idProfessor);
    }

    // Metodo para verificar se a Turma já existe.
    public boolean existeTurmaByCodigo(String codigo) {
        return this.turmaRepository.findBySenha(codigo).isPresent();
    }

    public Integer getIdPorSenha(String senha) {
        Optional<Turma> turma = this.turmaRepository.findBySenha(senha);
        if (turma.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Turma não encontrada.");
        }
        return turma.get().getIdTurma();
    }

    public Turma findBySenha(String senha) {
        Optional<Turma> turma = this.turmaRepository.findBySenha(senha);
        if (turma.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Turma não encontrada.");
        }
        return turma.get();
    }

    public Turma buscarPorIdTurmaAndIdProfessor(int idTurma, int idProfessor) {
        Optional<Turma> turma = this.turmaRepository.findByIdTurmaAndEducadorIdEducador(idTurma, idProfessor);
        if (turma.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Turma não encontrada.");
        }
        return turma.get();
    }



}
