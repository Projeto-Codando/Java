package school.sptech.apicodando.domain.educador.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.apicodando.domain.educador.Educador;

import java.util.Optional;

public interface EducadorRepository extends JpaRepository<Educador, Integer> {
    Optional<Educador> findByEmail(String email);
}
