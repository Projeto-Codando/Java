package school.sptech.apicodando.api.domain.aluno.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.apicodando.api.domain.aluno.Aluno;

import java.util.List;
import java.util.Optional;

public interface AlunoRepository extends JpaRepository<Aluno, Integer> {
    Optional<Aluno> findByApelido(String apelido);

    // Método para buscar todos os alunos de uma turma
    List<Aluno> findAllByTurmaIdTurma(Integer idTurma);
}
