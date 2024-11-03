package school.sptech.apicodando.api.domain.aula.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import school.sptech.apicodando.api.domain.aula.Aula;

import java.util.List;

public interface AulaRepository extends JpaRepository<Aula, Integer> {

    List<Aula> findAllByTema_IdTema(Integer idModulo);
    List<Aula> findAllByTema_IdTemaAndTurma_IdTurma(Integer idModulo, Integer idTurma);

}
