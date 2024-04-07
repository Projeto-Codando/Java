package school.sptech.apicodando.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.apicodando.entity.Aula;

public interface AulaRepository extends JpaRepository<Aula, Integer> {
}
