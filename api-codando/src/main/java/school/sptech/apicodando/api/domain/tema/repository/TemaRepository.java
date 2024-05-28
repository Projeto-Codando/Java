package school.sptech.apicodando.api.domain.tema.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.apicodando.api.domain.tema.Tema;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TemaRepository extends JpaRepository<Tema, UUID> {

    List<Tema> findAllByModulo_IdModulo(UUID integer);
}
