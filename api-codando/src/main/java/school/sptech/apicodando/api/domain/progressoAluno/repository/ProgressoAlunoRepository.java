package school.sptech.apicodando.api.domain.progressoAluno.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.apicodando.api.domain.progressoAluno.ProgressoAluno;

public interface ProgressoAlunoRepository extends JpaRepository<ProgressoAluno, Integer> {
}
