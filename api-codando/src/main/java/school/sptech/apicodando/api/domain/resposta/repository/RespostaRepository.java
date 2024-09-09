package school.sptech.apicodando.api.domain.resposta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import school.sptech.apicodando.api.domain.resposta.Resposta;

import java.util.List;

@Repository
public interface RespostaRepository extends JpaRepository<Resposta, Integer> {

    List<Resposta> findByPergunta_Id(Integer idPergunta);

}
