package school.sptech.apicodando.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.apicodando.entity.ProgressoAluno;

public interface ProgressoAlunoRepository extends JpaRepository<ProgressoAluno, Integer> {
}
