package school.sptech.apicodando.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.apicodando.entity.Educador;

public interface EducadorRepository extends JpaRepository<Educador, Integer> {
}
