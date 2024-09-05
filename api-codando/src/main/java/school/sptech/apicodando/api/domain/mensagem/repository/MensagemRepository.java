package school.sptech.apicodando.api.domain.mensagem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.apicodando.api.domain.mensagem.Mensagem;

public interface MensagemRepository extends JpaRepository<Mensagem, Integer> {
}
