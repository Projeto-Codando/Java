package school.sptech.apicodando.api.domain.escolaridade.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.apicodando.api.domain.escolaridade.Escolaridade;

public interface EscolaridadeRepository extends JpaRepository<Escolaridade, Integer> {
}
