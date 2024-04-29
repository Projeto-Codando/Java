package school.sptech.apicodando.domain.escolaridade.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.apicodando.domain.escolaridade.Escolaridade;

public interface EscolaridadeRepository extends JpaRepository<Escolaridade, Integer> {
}
