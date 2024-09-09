package school.sptech.apicodando.api.domain.pergunta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.apicodando.api.domain.pergunta.Pergunta;

import java.util.List;
import java.util.Optional;

public interface PerguntaRepository extends JpaRepository<Pergunta, Integer> {

    List<Pergunta> findByAula_Id(Integer idAula);

}
