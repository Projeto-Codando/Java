package school.sptech.apicodando.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.apicodando.entity.Escolaridade;

public interface EscolaridadeRepository extends JpaRepository<Escolaridade, Integer> {
}
