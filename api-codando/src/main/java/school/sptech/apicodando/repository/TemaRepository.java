package school.sptech.apicodando.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.apicodando.entity.Tema;

public interface TemaRepository extends JpaRepository<Tema, Integer> {
}
