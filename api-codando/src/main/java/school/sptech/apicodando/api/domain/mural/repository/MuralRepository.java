package school.sptech.apicodando.api.domain.mural.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.apicodando.api.domain.mural.Mural;

public interface MuralRepository extends JpaRepository<Mural, Integer>{
}
