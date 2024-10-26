package school.sptech.apicodando.api.domain.escolaridade.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import school.sptech.apicodando.api.domain.escolaridade.Escolaridade;

@Repository
public interface EscolaridadeRepository extends JpaRepository<Escolaridade, Integer> {
    long count();
}
