package school.sptech.apicodando.api.domain.resposta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import school.sptech.apicodando.api.domain.resposta.Resposta;

import java.util.List;

@Repository
public interface RespostaRepository extends JpaRepository<Resposta, Integer> {

    List<Resposta> findByPergunta_Id(Integer idPergunta);

//    List<Resposta> findByPergunta_IdAndCorreta(Integer idPergunta, Boolean correta);
//
//    List<Resposta>findByAlunos_IdAndPergunta_Id(Integer idAluno, Integer idPergunta);

}
