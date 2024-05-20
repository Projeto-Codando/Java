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
import school.sptech.apicodando.service.gradeService.dto.GradeCriacaoDto;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GradeService {

    private final GradeRepository gradeRepository;
    private final ModuloRepository moduloRepository;
    private final TemaRepository temaRepository;
    private final AulaRepository aulaRepository;
    private final TurmaRepository turmaRepository;

    public Grade criar(GradeCriacaoDto gradeCriacaoDto){
        Turma turma = turmaRepository.findById(gradeCriacaoDto.getTurma().getIdTurma())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Turma não encontrada."));

       List<Modulo> modulos = moduloRepository.findByGradeIdGrade(gradeCriacaoDto.getIdGrade());
        if (modulos.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Modulo não encontrado.");
        }

        for (Modulo modulo : modulos){
            List<Tema> temas = temaRepository.findByModuloIdModulo(modulo.getIdModulo());
            if (temas.isEmpty()){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tema não encontrado.");
            }
            for (Tema tema : temas){
                List<Aula> aulas = aulaRepository.findByTemaIdTema(tema.getIdTema());
                if (aulas.isEmpty()){
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Aula não encontrada.");
                }
            }

        }
        final Grade novaGrade = GradeMapper.toEntity(gradeCriacaoDto, turma, modulos);
        return gradeRepository.save(novaGrade);
    }
}
