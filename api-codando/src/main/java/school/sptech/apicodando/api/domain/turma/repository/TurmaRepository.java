package school.sptech.apicodando.api.domain.turma.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.apicodando.api.domain.turma.Turma;

import java.util.List;
import java.util.Optional;

public interface TurmaRepository extends JpaRepository<Turma, Integer> {
    Optional<Turma> findBySenha(String senha);
    Optional<Turma> findByIdTurmaAndEducadorIdEducador(int idTurma, int idProfessor);
    List<Turma> findByEducadorIdEducador(int idProfessor);

}

