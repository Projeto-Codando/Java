package school.sptech.apicodando.domain.turma.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.apicodando.domain.turma.Turma;

public interface TurmaRepository extends JpaRepository<Turma, Integer> {
}
