package school.sptech.apicodando.api.domain.aluno.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import school.sptech.apicodando.api.domain.aluno.Aluno;

import java.util.List;
import java.util.Optional;

public interface AlunoRepository extends JpaRepository<Aluno, Integer> {
    Optional<Aluno> findByApelido(String apelido);
    
    List<String> findTokensByTurmaId(Integer turmaId);
}
