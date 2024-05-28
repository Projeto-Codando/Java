package school.sptech.apicodando.api.domain.grade.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.apicodando.api.domain.grade.Grade;

import java.util.List;
import java.util.UUID;

public interface GradeRepository extends JpaRepository<Grade, UUID> {

//    @Override
    List<Grade> findAllByFkTurma_IdTurma(UUID integers);
}
