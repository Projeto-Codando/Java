package school.sptech.apicodando.api.domain.tema.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import school.sptech.apicodando.api.domain.tema.Tema;

import java.util.List;

public interface TemaRepository extends JpaRepository<Tema, Integer> {

    List<Tema> findAllByModulo_IdModulo(Integer integer);
    @Query("SELECT DISTINCT t FROM Tema t JOIN t.aulas a WHERE t.modulo.idModulo = :idModulo AND a.turma.idTurma = :idTurma")
    List<Tema> buscarTemaPorModuloTurma(@Param("idModulo") Integer idModulo, @Param("idTurma") Integer idTurma);
    long count();
}
