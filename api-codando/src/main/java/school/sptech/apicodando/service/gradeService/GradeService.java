package school.sptech.apicodando.service.gradeService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import school.sptech.apicodando.api.domain.aula.Aula;
import school.sptech.apicodando.api.domain.aula.repository.AulaRepository;
import school.sptech.apicodando.api.domain.grade.Grade;
import school.sptech.apicodando.api.domain.grade.repository.GradeRepository;
import school.sptech.apicodando.api.domain.modulo.Modulo;
import school.sptech.apicodando.api.domain.modulo.repository.ModuloRepository;
import school.sptech.apicodando.api.domain.tema.Tema;
import school.sptech.apicodando.api.domain.tema.repository.TemaRepository;
import school.sptech.apicodando.api.domain.turma.Turma;
import school.sptech.apicodando.api.domain.turma.repository.TurmaRepository;
import school.sptech.apicodando.api.mapper.GradeMapper;
import school.sptech.apicodando.api.mapper.ModuloMapper;
import school.sptech.apicodando.service.gradeService.dto.GradeCadastroDto;
import school.sptech.apicodando.service.gradeService.dto.GradeListagemDto;
import school.sptech.apicodando.service.moduloService.ModuloService;
import school.sptech.apicodando.service.moduloService.dto.ModuloListagemDTO;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GradeService {

    private final GradeRepository gradeRepository;
    private final TurmaRepository turmaRepository;
    private final ModuloRepository moduloRepository;
    private final TemaRepository temaRepository;
    private final AulaRepository aulaRepository;
    private final ModuloService moduloService;



    public Grade criar(GradeCadastroDto gradeCadastroDto){
        if (gradeCadastroDto.getFkTurma() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Turma ID nao pode  ser nulo");
        }
        Turma turma = turmaRepository.findById(gradeCadastroDto.getFkTurma())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Turma não encontrada."));
        final Grade novaGrade = GradeMapper.toEntity(gradeCadastroDto);
        novaGrade.setFkTurma(turma);
        return gradeRepository.save(novaGrade);
    }

    public GradeListagemDto listarPorId(Integer id){
        Grade grade = gradeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Grade não encontrada."));
        return GradeMapper.toDto(grade);
    }

    public List<GradeListagemDto> listarTodos(){

        List<Grade> grades = gradeRepository.findAll();

        List<GradeListagemDto> dtos = GradeMapper.toDto(grades);

        for (GradeListagemDto dto : dtos) {
            dto.setModulo(moduloService.listarPorGrade(dto.getIdGrade()));

        }

        return dtos;
    }

//    public GradeListagemDto.ModuloListagemGradeDto listarModuloPorIdGrade(int idGrade){
//        List<Modulo> modulos = moduloRepository.findAllByFkGrade(idGrade);
//    }


}
