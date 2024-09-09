package school.sptech.apicodando.api.domain.grade.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.apicodando.api.domain.grade.Grade;

import java.util.List;

public interface GradeRepository extends JpaRepository<Grade, Integer> {
}
