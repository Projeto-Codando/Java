package school.sptech.apicodando.service.educadorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import school.sptech.apicodando.entity.Educador;
import school.sptech.apicodando.mapper.EducadorMapper;
import school.sptech.apicodando.repository.EducadorRepository;
import school.sptech.apicodando.service.educadorService.dto.EducadorCadastroDTO;

@Service
public class EducadorService {

    @Autowired
    private EducadorRepository educadorRepository;

    public void criar(EducadorCadastroDTO educadorCadastroDTO){
        final Educador novoEducador = EducadorMapper.toEntity(educadorCadastroDTO);
        this.educadorRepository.save(novoEducador);
    }

}
