package school.sptech.apicodando.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.apicodando.entity.Turma;

public interface TurmaRepository extends JpaRepository<Turma, Integer> {
}
