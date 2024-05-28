package school.sptech.apicodando.api.domain.avatar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.apicodando.api.domain.avatar.Avatar;

import java.util.UUID;

public interface AvatarRepository extends JpaRepository<Avatar, UUID> {
}
