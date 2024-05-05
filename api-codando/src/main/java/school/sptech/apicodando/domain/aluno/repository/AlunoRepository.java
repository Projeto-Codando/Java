package school.sptech.apicodando.domain.aluno.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.apicodando.domain.aluno.Aluno;

import java.util.Optional;

public interface AlunoRepository extends JpaRepository<Aluno, Integer> {
    Optional<Aluno> findByApelido(String apelido);
}
