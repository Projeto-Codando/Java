package school.sptech.apicodando.api.domain.quiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.apicodando.api.domain.quiz.Quiz;

public interface QuizRepository extends JpaRepository<Quiz, Integer> {
}
