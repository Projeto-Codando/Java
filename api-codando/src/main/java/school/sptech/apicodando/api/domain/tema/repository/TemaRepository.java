package school.sptech.apicodando.api.domain.tema.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.apicodando.api.domain.tema.Tema;

public interface TemaRepository extends JpaRepository<Tema, Integer> {
}
