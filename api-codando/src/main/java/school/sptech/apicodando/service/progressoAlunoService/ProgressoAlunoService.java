package school.sptech.apicodando.service.progressoAlunoService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import school.sptech.apicodando.api.domain.aluno.Aluno;
import school.sptech.apicodando.api.domain.aula.Aula;
import school.sptech.apicodando.api.domain.pontosAluno.PontosDTO;
import school.sptech.apicodando.api.domain.progressoAluno.ProgressoAluno;
import school.sptech.apicodando.api.domain.progressoAluno.repository.ProgressoAlunoRepository;
import school.sptech.apicodando.api.mapper.ProgressoAlunoMapper;
import school.sptech.apicodando.service.alunoService.AlunoService;
import school.sptech.apicodando.service.aulaService.AulaService;
import school.sptech.apicodando.service.progressoAlunoService.dto.ProgressoAlunoCadastroDTO;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProgressoAlunoService {
    private final ProgressoAlunoRepository repository;
    private final AlunoService alunoService;
    private final AulaService aulaService;

    public ProgressoAluno criar(ProgressoAlunoCadastroDTO progressoAlunoCadastro) {
        if (progressoAlunoCadastro == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Dados inválidos.");
        }

        Aluno aluno = alunoService.listarUmPorId(progressoAlunoCadastro.getFkAluno()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Aluno não encontrado.")
        );
        Aula aula = aulaService.buscarPorId(progressoAlunoCadastro.getFkAula());

        // Verifica se já existe um registro com o mesmo aluno e aula
        if (repository.existsByFkAlunoAndFkAula(aluno, aula)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Progresso já cadastrado para este aluno e aula.");
        }

        ProgressoAluno progressoAluno = ProgressoAlunoMapper.toEntity(progressoAlunoCadastro, aluno, aula);
        ProgressoAluno progressoSalvo = repository.save(progressoAluno);
        return progressoSalvo;
    }


    public ProgressoAluno buscarPorId(UUID id) {
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Progresso do aluno não encontrado."));
    }

    public ProgressoAluno buscarPorIdAluno(UUID idAluno){
        Aluno aluno = alunoService.listarUmPorId(idAluno).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Aluno não encontrado."));

        return repository.findByFkAluno(aluno).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Progresso do aluno não encontrado."));
    }

    public List<ProgressoAluno> listarTodos() {
        return repository.findAll();
    }

    public ProgressoAluno atualizarPontos(UUID idAluno, PontosDTO pontosDTO, UUID idAula) {
        Aluno aluno = alunoService.listarUmPorId(idAluno).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Aluno não encontrado."));

        Aula aula = aulaService.buscarPorId(idAula);

        ProgressoAluno progressoAluno = repository.findByFkAlunoAndFkAula(aluno, aula).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Progresso do aluno não encontrado."));

        int pontuacaoMaximaAula = aulaService.getPontuacaoMaxima(progressoAluno.getFkAula().getId());

        if (progressoAluno.getPontuacaoAluno() + pontosDTO.getPontos() > pontuacaoMaximaAula) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A pontuação atual mais os novos pontos excede a pontuação máxima para esta aula.");
        }

        int novaPontuacao = progressoAluno.getPontuacaoAluno() + pontosDTO.getPontos();

        aluno.setMoedas(aluno.getMoedas() + pontosDTO.getPontos());

        if (novaPontuacao == pontuacaoMaximaAula) {
            progressoAluno.setStatusAula("Finalizada");
        }

        progressoAluno.setPontuacaoAluno(novaPontuacao);
        return repository.save(progressoAluno);
    }



}
