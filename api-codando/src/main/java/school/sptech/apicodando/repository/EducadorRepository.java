package school.sptech.apicodando.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.apicodando.entity.Educador;

import java.util.Optional;

public interface EducadorRepository extends JpaRepository<Educador, Integer> {


    Optional<Educador> findByEmail(String email);
}
