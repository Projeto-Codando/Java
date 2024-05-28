package school.sptech.apicodando.api.domain.turma.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.apicodando.api.domain.turma.Turma;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TurmaRepository extends JpaRepository<Turma, UUID> {
    Optional<Turma> findBySenha(String senha);
    Optional<Turma> findByIdTurmaAndEducadorIdEducador(UUID idTurma, UUID idProfessor);
    List<Turma> findByEducadorIdEducador(UUID idProfessor);

}

