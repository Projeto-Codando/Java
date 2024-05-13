package school.sptech.apicodando.api.domain.educador.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.apicodando.api.domain.educador.Educador;

import java.util.Optional;

public interface EducadorRepository extends JpaRepository<Educador, Integer> {
    Optional<Educador> findByEmail(String email);
    Optional<Educador> findByIdEducador(Integer idEducador);
}
