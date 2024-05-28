package school.sptech.apicodando.api.domain.escolaridade.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.apicodando.api.domain.escolaridade.Escolaridade;

import java.util.UUID;

public interface EscolaridadeRepository extends JpaRepository<Escolaridade, UUID> {
}
