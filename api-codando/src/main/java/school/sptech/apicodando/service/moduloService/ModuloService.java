package school.sptech.apicodando.service.moduloService;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import school.sptech.apicodando.api.domain.aula.Aula;
import school.sptech.apicodando.api.domain.aula.repository.AulaRepository;
import school.sptech.apicodando.api.domain.avatar.Avatar;
import school.sptech.apicodando.api.domain.grade.Grade;
import school.sptech.apicodando.api.domain.grade.repository.GradeRepository;
import school.sptech.apicodando.api.domain.modulo.Modulo;
import school.sptech.apicodando.api.domain.modulo.repository.ModuloRepository;
import school.sptech.apicodando.api.domain.tema.Tema;
import school.sptech.apicodando.api.domain.tema.repository.TemaRepository;
import school.sptech.apicodando.api.mapper.AulaMapper;
import school.sptech.apicodando.api.mapper.GradeMapper;
import school.sptech.apicodando.api.mapper.TemaMapper;
import school.sptech.apicodando.service.aulaService.dto.AulaListagemDTO;
import school.sptech.apicodando.service.gradeService.dto.GradeListagemDto;
import school.sptech.apicodando.service.moduloService.dto.ModuloCadastroDTO;
import school.sptech.apicodando.service.moduloService.dto.ModuloListagemDTO;
import school.sptech.apicodando.api.mapper.ModuloMapper;
import school.sptech.apicodando.service.temaService.TemaService;
import school.sptech.apicodando.service.temaService.dto.TemaListagemDTO;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ModuloService {

    private final ModuloRepository moduloRepository;
    private final GradeRepository gradeRepository;
    private final TemaService temaService;
    private final AulaRepository aulaRepository;

    @PostConstruct
    public void inserirDadosIniciaisSeNecessario() {
        if (moduloRepository.count() == 0) {
            Modulo modulo = new Modulo();
            modulo.setNome("LEMBRAR DE MUDAR O NOME DO MÓDULO");
            moduloRepository.save(modulo);
            System.out.println("Dado iniciai do modulo inserido.");
        } else {
            System.out.println("Dado do moduilo ja inserido.");
        }
    }

    public Modulo criar(ModuloCadastroDTO moduloCadastro) {

//        if (gradeRepository.findById(idGrade).isEmpty()) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
//        }

        Modulo modulo = ModuloMapper.toEntity(moduloCadastro);
//        modulo.setGrade(gradeRepository.findById(idGrade).get());
//        modulo.getGrade().setFkTurma(gradeRepository.findById(idGrade).get().getFkTurma());
//        modulo.getGrade().getFkTurma().setAlunos(gradeRepository.findById(idGrade).get().getFkTurma().getAlunos());
        return moduloRepository.save(modulo);

    }

    public List<ModuloListagemDTO> listarModulos() {

        return ModuloMapper.toDto(moduloRepository.findAll());
    }

    public List<ModuloListagemDTO> listarPorGrade(Integer idGrade) {
        Grade grade = gradeRepository.findById(idGrade)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Grade não encontrada."));

        List<Modulo> modulos = moduloRepository.findAllByGrade_IdGrade(grade.getIdGrade());
        List<ModuloListagemDTO> modulosDto = ModuloMapper.toDto(modulos);

        for (ModuloListagemDTO moduloDto : modulosDto) {
            List<TemaListagemDTO> temasDto = temaService.listarPorModulo(moduloDto.getIdModulo());
            moduloDto.setTemas(temasDto);
        }

        return modulosDto;
    }

    public ModuloListagemDTO listarPorId(Integer id){

        Modulo modulo = moduloRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Modulo não encontrado."));

        ModuloListagemDTO dto = ModuloMapper.toDto(modulo);

        List<TemaListagemDTO> temasDto = temaService.listarPorModulo(dto.getIdModulo());
        dto.setTemas(temasDto);

        return dto;
    }


}
