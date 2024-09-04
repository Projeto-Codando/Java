package school.sptech.apicodando.api.domain.pergunta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.apicodando.api.domain.pergunta.Pergunta;

public interface PerguntaRepository extends JpaRepository<Pergunta, Integer> {
}
