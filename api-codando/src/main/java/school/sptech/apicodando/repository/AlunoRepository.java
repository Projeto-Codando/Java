package school.sptech.apicodando.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.apicodando.entity.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno, Integer> {
}
