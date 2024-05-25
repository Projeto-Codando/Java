package school.sptech.apicodando.api.domain.aula.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import school.sptech.apicodando.api.domain.aula.Aula;

import java.util.List;

public interface AulaRepository extends JpaRepository<Aula, Integer> {

    List<Aula> findAllByTema_IdTema(Integer idModulo);
}
