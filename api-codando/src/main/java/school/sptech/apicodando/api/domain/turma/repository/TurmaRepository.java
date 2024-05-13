package school.sptech.apicodando.api.domain.turma.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.apicodando.api.domain.turma.Turma;

public interface TurmaRepository extends JpaRepository<Turma, Integer> {
}
