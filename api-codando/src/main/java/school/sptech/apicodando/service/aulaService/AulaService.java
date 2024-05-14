package school.sptech.apicodando.service.aulaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import school.sptech.apicodando.api.domain.aula.repository.AulaRepository;

@Service
public class AulaService {

    @Autowired
    private AulaRepository aulaRepository;

    public void criar() {
        
    }

}
