package school.sptech.apicodando.service.turmaService;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import school.sptech.apicodando.api.domain.aluno.Aluno;
import school.sptech.apicodando.api.domain.aula.Aula;
import school.sptech.apicodando.api.domain.educador.Educador;
import school.sptech.apicodando.api.domain.educador.repository.EducadorRepository;
import school.sptech.apicodando.api.domain.escolaridade.Escolaridade;
import school.sptech.apicodando.api.domain.escolaridade.repository.EscolaridadeRepository;
import school.sptech.apicodando.api.domain.grade.Grade;
import school.sptech.apicodando.api.domain.grade.repository.GradeRepository;
import school.sptech.apicodando.api.domain.modulo.Modulo;
import school.sptech.apicodando.api.domain.tema.Tema;
import school.sptech.apicodando.api.domain.turma.Turma;
import school.sptech.apicodando.api.domain.turma.repository.TurmaRepository;
import school.sptech.apicodando.api.mapper.TurmaMapper;
import school.sptech.apicodando.service.alunoService.AlunoService;
import school.sptech.apicodando.service.presencaService.PresencaDTO;
import school.sptech.apicodando.service.presencaService.PresencaService;
import school.sptech.apicodando.service.turmaService.dto.TurmaAtualizaDTO;
import school.sptech.apicodando.service.turmaService.dto.TurmaCadastroDTO;
import school.sptech.apicodando.service.turmaService.dto.TurmaListagemDTO;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Service
@RequiredArgsConstructor
public class TurmaService {

    private final TurmaRepository turmaRepository;
    private final EscolaridadeRepository escolaridadeRepository;
    private final EducadorRepository educadorRepository;
    private final GradeRepository gradeRepository;
    private final AlunoService alunoService;
    private final PresencaService presencaService;

    public Turma criar(TurmaCadastroDTO turmaCadastro) {
        Escolaridade escolaridade = escolaridadeRepository.findById(turmaCadastro.getFkEscolaridade())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Escolaridade não encontrada."));

        Educador educador = educadorRepository.findById(turmaCadastro.getFkEducador())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Educador não encontrado."));

        final Turma novaTurma = TurmaMapper.toEntity(turmaCadastro, escolaridade, educador);

        novaTurma.setGrade(gradeRepository.findAllByFkTurma_IdTurma(novaTurma.getIdTurma()));

        if (existeTurmaByCodigo(turmaCadastro.getSenha())){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Turma já criada.");
        }
        return turmaRepository.save(novaTurma);
    }

    public Turma desativar(UUID id, UUID idProfessor) {
        Turma turma = buscarPorIdTurmaAndIdProfessor(id, idProfessor);
        if (turma == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Turma não encontrada.");
        } else {
            turma.setStatusTurma(false);
            return this.turmaRepository.save(turma);
        }
    }

    public Turma atualizar(TurmaAtualizaDTO turmaAtualizada, UUID id){
        Turma turma = buscarPorIdTurmaAndIdProfessor(id, turmaAtualizada.getFkEducador().getIdEducador());
        if(turma == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Turma não encontrada.");
        } else {

            turma.setNome(turmaAtualizada.getNome());
            turma.setSenha(turmaAtualizada.getSenha());
            turma.setEscolaridade(turmaAtualizada.getFkEscolaridade());
            turma.setEducador(turmaAtualizada.getFkEducador());

            return turmaRepository.save(turma);
        }
    }

    public List<TurmaListagemDTO> listarTodasTurmasPorProfessor(UUID idProfessor) {
        List<Turma> turmas = this.listarPorProfessor(idProfessor);

        final List<TurmaListagemDTO> turmasListagem = TurmaMapper.toDto(turmas);

        if (turmas.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return turmasListagem;
    }

    public TurmaListagemDTO listarPorIdAndProfessor(UUID idTurma, UUID idProfessor) {
        Optional<Turma> turma = this.turmaRepository.findByIdTurmaAndEducadorIdEducador(idTurma, idProfessor);

        if (turma.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Turma não encontrada.");
        }

        return TurmaMapper.toDto(turma.get());
    }

    public List<Turma> listarPorProfessor(UUID idProfessor) {
        return this.turmaRepository.findByEducadorIdEducador(idProfessor);
    }

    // Metodo para verificar se a Turma já existe.
    public boolean existeTurmaByCodigo(String codigo) {
        return this.turmaRepository.findBySenha(codigo).isPresent();
    }

    public UUID getIdPorSenha(String senha) {
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

    public Turma buscarPorIdTurmaAndIdProfessor(UUID idTurma, UUID idProfessor) {
        Optional<Turma> turma = this.turmaRepository.findByIdTurmaAndEducadorIdEducador(idTurma, idProfessor);
        if (turma.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Turma não encontrada.");
        }
        return turma.get();
    }

    public PresencaDTO[][] getPresencas(UUID idProfessor, UUID idTurma) {
        Turma turma = buscarPorIdTurmaAndIdProfessor(idTurma,idProfessor);
        List<Aluno> alunos = turma.getAlunos();
        List<Aula> aulas = getAulasDaGrade(turma.getGrade());

        int numAlunos = alunos.size();
        int numAulas = aulas.size();

        PresencaDTO[][] presencas = new PresencaDTO[numAlunos][numAulas];
        for (int i = 0; i < numAlunos; i++) {
            for (int j = 0; j < numAulas; j++) {
                PresencaDTO presenca = new PresencaDTO();
                presenca.setIdAluno(alunos.get(i).getIdAluno());
                presenca.setIdAula(aulas.get(j).getId());
                presenca.setPresente(presencaService.verificarPresenca(alunos.get(i).getIdAluno(), aulas.get(j).getId()));
                presencas[i][j] = presenca;
            }
        }
        return presencas;
    }

    public List<Aula> getAulasDaGrade(List<Grade> grades) {
        List<Aula> aulas = new ArrayList<>();

        for (Grade grade : grades) {
            for (Modulo modulo : grade.getModulos()) {
                for (Tema tema : modulo.getTemas()) {
                    aulas.addAll(tema.getAulas());
                }
            }
        }

        return aulas;
    }




}
