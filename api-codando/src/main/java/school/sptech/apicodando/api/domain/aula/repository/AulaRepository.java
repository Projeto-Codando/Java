package school.sptech.apicodando.api.domain.aula.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import school.sptech.apicodando.api.domain.aula.Aula;

import java.util.List;
import java.util.UUID;

public interface AulaRepository extends JpaRepository<Aula, UUID> {

    List<Aula> findAllByTema_IdTema(UUID idModulo);
}
