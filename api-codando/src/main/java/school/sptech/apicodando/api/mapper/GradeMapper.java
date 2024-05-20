package school.sptech.apicodando.api.mapper;

import school.sptech.apicodando.api.domain.aula.Aula;
import school.sptech.apicodando.api.domain.grade.Grade;
import school.sptech.apicodando.api.domain.modulo.Modulo;
import school.sptech.apicodando.api.domain.tema.Tema;
import school.sptech.apicodando.api.domain.turma.Turma;
import school.sptech.apicodando.service.gradeService.dto.GradeCadastroDto;
import school.sptech.apicodando.service.gradeService.dto.GradeListagemDto;

import java.util.ArrayList;
import java.util.List;

public class GradeMapper {

    // METOODS DE CONVERSAO DE DTO PARA ENTITY
    public static Grade toEntity(GradeCadastroDto gradeListagemDto, Turma turma) {
        if (gradeListagemDto == null) {
            return null;
        }

        Grade grade = new Grade();
        grade.setFkTurma(turma);
        return grade;
    }

    public static Modulo toModulo(GradeListagemDto.ModuloListagemGradeDto moduloListagemGradeDto) {
        Modulo modulo = new Modulo();
        modulo.setIdModulo(moduloListagemGradeDto.getIdModulo());
        modulo.setNome(moduloListagemGradeDto.getNome());
        List<Tema> temas = new ArrayList<>();
        for (GradeListagemDto.ModuloListagemGradeDto.TemaListagemGradeDto temaListagemGradeDto : moduloListagemGradeDto.getTemas()) {
            temas.add(toTema(temaListagemGradeDto));
        }
        modulo.setTemas(temas);
        return modulo;
    }

    public static Tema toTema(GradeListagemDto.ModuloListagemGradeDto.TemaListagemGradeDto temaListagemGradeDto) {
        Tema tema = new Tema();
        tema.setIdTema(temaListagemGradeDto.getIdTema());
        tema.setNome(temaListagemGradeDto.getNome());
        List<Aula> aulas = new ArrayList<>();
        for (GradeListagemDto.ModuloListagemGradeDto.TemaListagemGradeDto.AulaListagemGradeDto aulaListagemGradeDto : temaListagemGradeDto.getAulas()) {
            aulas.add(toAula(aulaListagemGradeDto));
        }
        tema.setAulas(aulas);
        return tema;
    }

    public static Aula toAula(GradeListagemDto.ModuloListagemGradeDto.TemaListagemGradeDto.AulaListagemGradeDto aulaListagemGradeDto) {
        Aula aula = new Aula();
        aula.setId(aulaListagemGradeDto.getId());
        aula.setTitulo(aulaListagemGradeDto.getTitulo());
        aula.setDescricao(aulaListagemGradeDto.getDescricao());
        aula.setNivelDificuldade(aulaListagemGradeDto.getNivelDificuldade());
        aula.setPontuacaoMaxima(aulaListagemGradeDto.getPontuacaoMaxima());
        return aula;
    }

    // METOODS DE CONVERSAO DE ENTITY PARA DTO

    public static GradeListagemDto toGradeCriacaoDto(Grade grade) {
        GradeListagemDto gradeListagemDto = new GradeListagemDto();
        gradeListagemDto.setIdGrade(grade.getIdGrade());
        gradeListagemDto.setTurma(grade.getFkTurma());
        List<GradeListagemDto.ModuloListagemGradeDto> modulos = new ArrayList<>();
        for (Modulo modulo : grade.getModulos()) {
            modulos.add(toModuloListagemDto(modulo));
        }
        gradeListagemDto.setModulo(modulos);
        return gradeListagemDto;
    }

    public static GradeListagemDto.ModuloListagemGradeDto.TemaListagemGradeDto.AulaListagemGradeDto toAulaListagemDto(Aula aula) {
        GradeListagemDto.ModuloListagemGradeDto.TemaListagemGradeDto.AulaListagemGradeDto aulaListagemGradeDto = new GradeListagemDto.ModuloListagemGradeDto.TemaListagemGradeDto.AulaListagemGradeDto();
        aulaListagemGradeDto.setId(aula.getId());
        aulaListagemGradeDto.setTitulo(aula.getTitulo());
        aulaListagemGradeDto.setDescricao(aula.getDescricao());
        aulaListagemGradeDto.setNivelDificuldade(aula.getNivelDificuldade());
        aulaListagemGradeDto.setPontuacaoMaxima(aula.getPontuacaoMaxima());
        return aulaListagemGradeDto;
    }

    public static GradeListagemDto.ModuloListagemGradeDto.TemaListagemGradeDto toTemaListagemDto(Tema tema) {
        GradeListagemDto.ModuloListagemGradeDto.TemaListagemGradeDto temaListagemGradeDto = new GradeListagemDto.ModuloListagemGradeDto.TemaListagemGradeDto();
        temaListagemGradeDto.setIdTema(tema.getIdTema());
        temaListagemGradeDto.setNome(tema.getNome());
        List<GradeListagemDto.ModuloListagemGradeDto.TemaListagemGradeDto.AulaListagemGradeDto> aulas = new ArrayList<>();
        for (Aula aula : tema.getAulas()) {
            aulas.add(toAulaListagemDto(aula));
        }
        temaListagemGradeDto.setAulas(aulas);
        return temaListagemGradeDto;
    }

    public static GradeListagemDto.ModuloListagemGradeDto toModuloListagemDto(Modulo modulo) {
        GradeListagemDto.ModuloListagemGradeDto moduloListagemGradeDto = new GradeListagemDto.ModuloListagemGradeDto();
        moduloListagemGradeDto.setIdModulo(modulo.getIdModulo());
        moduloListagemGradeDto.setNome(modulo.getNome());
        List<GradeListagemDto.ModuloListagemGradeDto.TemaListagemGradeDto> temas = new ArrayList<>();
        for (Tema tema : modulo.getTemas()) {
            temas.add(toTemaListagemDto(tema));
        }
        moduloListagemGradeDto.setTemas(temas);
        return moduloListagemGradeDto;
    }

    public static GradeListagemDto toDto(Grade grade) {
        if (grade == null) {
            return null;
        }

        GradeListagemDto gradeListagemDto = new GradeListagemDto();
        gradeListagemDto.setIdGrade(grade.getIdGrade());
        gradeListagemDto.setTurma(grade.getFkTurma());
        return gradeListagemDto;
    }



}
