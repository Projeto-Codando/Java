package school.sptech.apicodando.api.domain.mensagem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.apicodando.api.domain.mensagem.Mensagem;

import java.util.List;
import java.util.Optional;

public interface MensagemRepository extends JpaRepository<Mensagem, Integer> {


    List<Mensagem> findAllByTurmaIdTurma(Integer integer);
}
