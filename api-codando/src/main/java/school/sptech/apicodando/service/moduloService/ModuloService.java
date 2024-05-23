package school.sptech.apicodando.service.moduloService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import school.sptech.apicodando.api.domain.grade.repository.GradeRepository;
import school.sptech.apicodando.api.domain.modulo.Modulo;
import school.sptech.apicodando.api.domain.modulo.repository.ModuloRepository;
import school.sptech.apicodando.service.moduloService.dto.ModuloCadastroDTO;
import school.sptech.apicodando.service.moduloService.dto.ModuloListagemDTO;
import school.sptech.apicodando.api.mapper.ModuloMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ModuloService {

    private final ModuloRepository moduloRepository;
    private final GradeRepository gradeRepository;

    public void criar(ModuloCadastroDTO moduloCadastro, Integer idGrade) {

        if (gradeRepository.findById(idGrade).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        Modulo modulo = ModuloMapper.toEntity(moduloCadastro);
        modulo.setGrade(gradeRepository.findById(idGrade).get());
        modulo.getGrade().setFkTurma(gradeRepository.findById(idGrade).get().getFkTurma());
        modulo.getGrade().getFkTurma().setAlunos(gradeRepository.findById(idGrade).get().getFkTurma().getAlunos());
        moduloRepository.save(modulo);

    }

    public List<ModuloListagemDTO> listarModulos() {

        return ModuloMapper.toDto(moduloRepository.findAll());
    }


}
