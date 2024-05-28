package school.sptech.apicodando.service.gradeService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import school.sptech.apicodando.api.domain.grade.Grade;
import school.sptech.apicodando.api.domain.grade.repository.GradeRepository;
import school.sptech.apicodando.api.domain.turma.Turma;
import school.sptech.apicodando.api.domain.turma.repository.TurmaRepository;
import school.sptech.apicodando.api.mapper.GradeMapper;
import school.sptech.apicodando.service.gradeService.dto.GradeCadastroDto;
import school.sptech.apicodando.service.gradeService.dto.GradeListagemDto;
import school.sptech.apicodando.service.moduloService.ModuloService;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GradeService {

    private final GradeRepository gradeRepository;
    private final TurmaRepository turmaRepository;
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

    public GradeListagemDto listarPorId(UUID id){
        GradeListagemDto dto = GradeMapper.toDto(gradeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Grade não encontrada.")));
        
        dto.setModulo(moduloService.listarPorGrade(dto.getIdGrade()));

        return dto;
    }

    public List<GradeListagemDto> listarTodos(){

        List<Grade> grades = gradeRepository.findAll();

        List<GradeListagemDto> dtos = GradeMapper.toDto(grades);

        for (GradeListagemDto dto : dtos) {
            dto.setModulo(moduloService.listarPorGrade(dto.getIdGrade()));
        }

        return dtos;
    }

    public List<GradeListagemDto> listarPorTurma(UUID idTurma){
        List<Grade> grades = gradeRepository.findAllByFkTurma_IdTurma(idTurma);

        if (turmaRepository.existsById(idTurma)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Turma não encontrada.");
        }

        List<GradeListagemDto> dtos = GradeMapper.toDto(grades);

        for (GradeListagemDto dto : dtos) {
            dto.setModulo(moduloService.listarPorGrade(dto.getIdGrade()));
        }

        return dtos;
    }

}
