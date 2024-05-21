package school.sptech.apicodando.api.domain.tema.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.apicodando.api.domain.tema.Tema;

import java.util.List;
import java.util.Optional;

public interface TemaRepository extends JpaRepository<Tema, Integer> {
    @Override
    Optional<Tema> findById(Integer integer);

    ;
}
