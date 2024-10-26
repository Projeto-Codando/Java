package school.sptech.apicodando.api.domain.avatar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.apicodando.api.domain.avatar.Avatar;

import java.util.Optional;

public interface AvatarRepository extends JpaRepository<Avatar, Integer> {
        Optional<Avatar> findByDescricao(String descricao);

        long count();
}
