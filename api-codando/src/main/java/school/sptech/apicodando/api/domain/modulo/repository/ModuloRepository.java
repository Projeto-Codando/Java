package school.sptech.apicodando.api.domain.modulo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.apicodando.api.domain.modulo.Modulo;

import java.util.List;

public interface ModuloRepository extends JpaRepository<Modulo, Integer> {

    List<Modulo> findAllByGrade_IdGrade(Integer idGrade);
    long count();
}
