package school.sptech.apicodando.api.domain.modulo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.apicodando.api.domain.modulo.Modulo;

import java.util.List;
import java.util.UUID;

public interface ModuloRepository extends JpaRepository<Modulo, UUID> {


    List<Modulo> findAllByGrade_IdGrade(UUID idGrade);
}
