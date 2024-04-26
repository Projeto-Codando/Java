package school.sptech.apicodando.service.educadorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import school.sptech.apicodando.domain.educador.Educador;
import school.sptech.apicodando.mapper.EducadorMapper;
import school.sptech.apicodando.domain.educador.repository.EducadorRepository;
import school.sptech.apicodando.service.educadorService.dto.EducadorCadastroDTO;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.ResponseEntity.ok;

@Service
public class EducadorService {

    @Autowired
    private EducadorRepository educadorRepository;

    public void criar(EducadorCadastroDTO educadorCadastroDTO) {
        final Educador novoEducador = EducadorMapper.toEntity(educadorCadastroDTO);
        this.educadorRepository.save(novoEducador);
    }

    public void excluir(int id) {
        educadorRepository.deleteById(id);
    }

    public void atualizar(Educador educadorAtualizado, int id){
        educadorAtualizado.setIdEducador(id);
        educadorRepository.save(educadorAtualizado);
    }

    public Optional<Educador> listarUmPorId(int id){
        return educadorRepository.findById(id);
    }

    public List<Educador> listarTodos(){
        return educadorRepository.findAll();
    }


}
