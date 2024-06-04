package school.sptech.apicodando.service.presencaService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import school.sptech.apicodando.api.domain.aluno.Aluno;
import school.sptech.apicodando.api.domain.aula.Aula;
import school.sptech.apicodando.api.domain.progressoAluno.ProgressoAluno;
import school.sptech.apicodando.api.domain.progressoAluno.repository.ProgressoAlunoRepository;
import school.sptech.apicodando.service.alunoService.AlunoService;
import school.sptech.apicodando.service.aulaService.AulaService;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class PresencaService {
    private final ProgressoAlunoRepository progressoAlunoRepository;

    private final AulaService aulaService;

    private final AlunoService alunoService;

    public boolean verificarPresenca(UUID idAluno, UUID idAula) {
        // Verificar se o aluno e a aula existem
        Aluno aluno = alunoService.listarUmPorId(idAluno).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Aluno não encontrado"));
        Aula aula = aulaService.buscarPorId(idAula);

        if(aula == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Aula não encontrada");
        }

        Optional<ProgressoAluno> progressoAluno = progressoAlunoRepository.findByFkAlunoAndFkAula(aluno, aula);
        return progressoAluno.isPresent();
    }
}
