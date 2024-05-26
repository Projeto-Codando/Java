package school.sptech.apicodando.api.domain.avatar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.apicodando.api.domain.avatar.Avatar;

public interface AvatarRepository extends JpaRepository<Avatar, Integer> {
}
