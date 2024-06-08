package school.sptech.apicodando.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import school.sptech.apicodando.api.domain.aula.Aula;
import school.sptech.apicodando.api.domain.aula.repository.AulaRepository;
import school.sptech.apicodando.api.domain.grade.Grade;
import school.sptech.apicodando.api.domain.grade.repository.GradeRepository;
import school.sptech.apicodando.api.domain.modulo.Modulo;
import school.sptech.apicodando.api.domain.modulo.repository.ModuloRepository;
import school.sptech.apicodando.api.domain.tema.Tema;
import school.sptech.apicodando.api.domain.tema.repository.TemaRepository;
import school.sptech.apicodando.api.domain.turma.Turma;
import school.sptech.apicodando.api.mapper.GradeMapper;
import school.sptech.apicodando.service.aulaService.AulaService;
import school.sptech.apicodando.service.aulaService.dto.AulaListagemDTO;
import school.sptech.apicodando.service.gradeService.dto.GradeListagemDto;
import school.sptech.apicodando.service.moduloService.dto.ModuloListagemDTO;
import school.sptech.apicodando.service.temaService.dto.TemaListagemDTO;
import school.sptech.apicodando.service.turmaService.TurmaService;
import school.sptech.apicodando.service.turmaService.dto.TurmaCadastroDTO;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spring.web.readers.operation.ResponseMessagesReader;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseStatus;
import school.sptech.apicodando.api.domain.turma.repository.TurmaRepository;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.validateMockitoUsage;
import static org.mockito.Mockito.when;

class TurmaServiceTest {
    @Mock
    private List<Modulo> modulo;
    @Mock
    private List<Aula> aula;
    @Mock
    private List<Tema> tema;
    @Mock
    private List<Grade> grade;
    @Mock
    ModuloRepository moduloRepository;
    @Mock
    private AulaRepository aulaRepository;
    @Mock
    private TemaRepository temaRepository;
    @Mock
    private GradeRepository gradeRepository;

    @InjectMocks
    private TurmaService turmaService;
    UUID idTurma = UUID.randomUUID();
    List<AulaListagemDTO> aulaListagemDTO;
    List<TemaListagemDTO> temaListagemDTO;
    List<ModuloListagemDTO> moduloListagemDTO;
    List<GradeListagemDto> gradeListagemDto;

    @BeforeEach
    void setUp() {

        MockitoAnnotations.initMocks(this);

        AulaListagemDTO aulaListagemDTO = new AulaListagemDTO();
        aulaListagemDTO.setId(UUID.randomUUID());
        aulaListagemDTO.setTitulo("Aula 1");
        aulaListagemDTO.setDescricao("Descrição da aula 1");
        aulaListagemDTO.setNivelDificuldade(6);
        aulaListagemDTO.setPontuacaoMaxima(60);
        this.aulaListagemDTO = List.of(aulaListagemDTO);

        TemaListagemDTO temaListagemDTO = new TemaListagemDTO();
        temaListagemDTO.setIdTema(UUID.randomUUID());
        temaListagemDTO.setNome("Tema 1");
        temaListagemDTO.setAulas(List.of(aulaListagemDTO));
        this.temaListagemDTO = List.of(temaListagemDTO);

        ModuloListagemDTO moduloListagemDTO = new ModuloListagemDTO();
        moduloListagemDTO.setIdModulo(UUID.randomUUID());
        moduloListagemDTO.setNome("Modulo 1");
        moduloListagemDTO.setTemas(List.of(temaListagemDTO));
        this.moduloListagemDTO = List.of(moduloListagemDTO);

        GradeListagemDto gradeListagemDto = new GradeListagemDto();
        gradeListagemDto.setIdGrade(UUID.randomUUID());
        gradeListagemDto.setModulo(List.of(moduloListagemDTO));
        gradeListagemDto.setIdTurma(idTurma);
        this.gradeListagemDto = List.of(gradeListagemDto);

        List<GradeListagemDto> gradeListagemDtos = new ArrayList<>();
        gradeListagemDtos.add(gradeListagemDto);


        BeanUtils.copyProperties(gradeListagemDto, gradeListagemDto);
        BeanUtils.copyProperties(moduloListagemDTO, moduloListagemDTO);
        BeanUtils.copyProperties(temaListagemDTO, temaListagemDTO);

    }

//    @Test
//    @DisplayName("Deve criar uma turma com sucesso")
//    void testCriarTurmaComSucesso() {
//        List<AulaListagemDTO> aulas = (aulaListagemDTO);
//        List<TemaListagemDTO> temas = (temaListagemDTO);
//        List<ModuloListagemDTO> modulos = (moduloListagemDTO);
//        List<GradeListagemDto> grades = (gradeListagemDto);
//
//        when(moduloRepository.findAllByGrade_IdGrade(gradeListagemDto.get(0).getIdGrade())).thenReturn(modulo);
//        when(aulaRepository.findAllByTema_IdTema(temaListagemDTO.get(0).getIdTema())).thenReturn(aula);
//        when(temaRepository.findAllByModulo_IdModulo(moduloListagemDTO.get(0).getIdModulo())).thenReturn(tema);
//        when(gradeRepository.findAllByFkTurma_IdTurma(idTurma)).thenReturn(grade);
//
//        ResponseEntity turmaResponse = turmaService.criar(new TurmaCadastroDTO());
////        System.out.println(turmaResponse.getBody());
//        Assertions.assertNotNull(turmaResponse.getBody().);
//
//    }



}