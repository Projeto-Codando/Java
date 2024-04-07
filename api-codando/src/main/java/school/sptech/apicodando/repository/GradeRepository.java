package school.sptech.apicodando.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.apicodando.entity.Grade;

public interface GradeRepository extends JpaRepository<Grade, Integer> {
}
