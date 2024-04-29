package school.sptech.apicodando.domain.grade.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.apicodando.domain.grade.Grade;

public interface GradeRepository extends JpaRepository<Grade, Integer> {
}
