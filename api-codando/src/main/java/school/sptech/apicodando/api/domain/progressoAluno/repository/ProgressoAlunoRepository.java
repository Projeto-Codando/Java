package school.sptech.apicodando.api.domain.progressoAluno.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.apicodando.api.domain.aluno.Aluno;
import school.sptech.apicodando.api.domain.aula.Aula;
import school.sptech.apicodando.api.domain.progressoAluno.ProgressoAluno;

import java.util.Optional;

public interface ProgressoAlunoRepository extends JpaRepository<ProgressoAluno, Integer> {
    boolean existsByFkAlunoAndFkAula(Aluno fkAluno, Aula fkAula);

    Optional<ProgressoAluno> findByFkAluno(Aluno aluno);

    Optional<ProgressoAluno> findByFkAlunoAndFkAula(Aluno aluno, Aula aula);
}
