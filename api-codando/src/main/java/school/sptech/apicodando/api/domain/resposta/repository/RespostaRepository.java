package school.sptech.apicodando.api.domain.resposta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.apicodando.api.domain.resposta.Resposta;

public interface RespostaRepository extends JpaRepository<Resposta, Integer> {
}
