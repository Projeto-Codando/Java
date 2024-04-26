package school.sptech.apicodando.domain.aula.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.apicodando.domain.aula.Aula;

public interface AulaRepository extends JpaRepository<Aula, Integer> {
}
