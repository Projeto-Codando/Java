package school.sptech.apicodando.api.mapper;

import school.sptech.apicodando.api.domain.aula.Aula;
import school.sptech.apicodando.api.domain.grade.Grade;
import school.sptech.apicodando.api.domain.modulo.Modulo;
import school.sptech.apicodando.api.domain.tema.Tema;
import school.sptech.apicodando.api.domain.turma.Turma;
import school.sptech.apicodando.service.gradeService.dto.GradeCriacaoDto;

import java.util.ArrayList;
import java.util.List;

public class GradeMapper {

    // METOODS DE CONVERSAO DE DTO PARA ENTITY
    public static Grade toEntity(GradeCriacaoDto gradeCriacaoDto, Turma turma, List<Modulo> modulosNovos) {
        Grade grade = new Grade();
        grade.setIdGrade(gradeCriacaoDto.getIdGrade());
        grade.setFkTurma(turma);
        grade.setModulos(modulosNovos);
        return grade;
    }

    public static Modulo toModulo(GradeCriacaoDto.ModuloListagemGradeDto moduloListagemGradeDto) {
        Modulo modulo = new Modulo();
        modulo.setIdModulo(moduloListagemGradeDto.getIdModulo());
        modulo.setNome(moduloListagemGradeDto.getNome());
        List<Tema> temas = new ArrayList<>();
        for (GradeCriacaoDto.ModuloListagemGradeDto.TemaListagemGradeDto temaListagemGradeDto : moduloListagemGradeDto.getTemas()) {
            temas.add(toTema(temaListagemGradeDto));
        }
        modulo.setTemas(temas);
        return modulo;
    }

    public static Tema toTema(GradeCriacaoDto.ModuloListagemGradeDto.TemaListagemGradeDto temaListagemGradeDto) {
        Tema tema = new Tema();
        tema.setIdTema(temaListagemGradeDto.getIdTema());
        tema.setNome(temaListagemGradeDto.getNome());
        List<Aula> aulas = new ArrayList<>();
        for (GradeCriacaoDto.ModuloListagemGradeDto.TemaListagemGradeDto.AulaListagemGradeDto aulaListagemGradeDto : temaListagemGradeDto.getAulas()) {
            aulas.add(toAula(aulaListagemGradeDto));
        }
        tema.setAulas(aulas);
        return tema;
    }

    public static Aula toAula(GradeCriacaoDto.ModuloListagemGradeDto.TemaListagemGradeDto.AulaListagemGradeDto aulaListagemGradeDto) {
        Aula aula = new Aula();
        aula.setId(aulaListagemGradeDto.getId());
        aula.setTitulo(aulaListagemGradeDto.getTitulo());
        aula.setDescricao(aulaListagemGradeDto.getDescricao());
        aula.setNivelDificuldade(aulaListagemGradeDto.getNivelDificuldade());
        aula.setPontuacaoMaxima(aulaListagemGradeDto.getPontuacaoMaxima());
        return aula;
    }

    // METOODS DE CONVERSAO DE ENTITY PARA DTO

    public static GradeCriacaoDto toGradeCriacaoDto(Grade grade) {
        GradeCriacaoDto gradeCriacaoDto = new GradeCriacaoDto();
        gradeCriacaoDto.setIdGrade(grade.getIdGrade());
        gradeCriacaoDto.setTurma(grade.getFkTurma());
        List<GradeCriacaoDto.ModuloListagemGradeDto> modulos = new ArrayList<>();
        for (Modulo modulo : grade.getModulos()) {
            modulos.add(toModuloListagemDto(modulo));
        }
        gradeCriacaoDto.setModulo(modulos);
        return gradeCriacaoDto;
    }

    public static GradeCriacaoDto.ModuloListagemGradeDto.TemaListagemGradeDto.AulaListagemGradeDto toAulaListagemDto(Aula aula) {
        GradeCriacaoDto.ModuloListagemGradeDto.TemaListagemGradeDto.AulaListagemGradeDto aulaListagemGradeDto = new GradeCriacaoDto.ModuloListagemGradeDto.TemaListagemGradeDto.AulaListagemGradeDto();
        aulaListagemGradeDto.setId(aula.getId());
        aulaListagemGradeDto.setTitulo(aula.getTitulo());
        aulaListagemGradeDto.setDescricao(aula.getDescricao());
        aulaListagemGradeDto.setNivelDificuldade(aula.getNivelDificuldade());
        aulaListagemGradeDto.setPontuacaoMaxima(aula.getPontuacaoMaxima());
        return aulaListagemGradeDto;
    }

    public static GradeCriacaoDto.ModuloListagemGradeDto.TemaListagemGradeDto toTemaListagemDto(Tema tema) {
        GradeCriacaoDto.ModuloListagemGradeDto.TemaListagemGradeDto temaListagemGradeDto = new GradeCriacaoDto.ModuloListagemGradeDto.TemaListagemGradeDto();
        temaListagemGradeDto.setIdTema(tema.getIdTema());
        temaListagemGradeDto.setNome(tema.getNome());
        List<GradeCriacaoDto.ModuloListagemGradeDto.TemaListagemGradeDto.AulaListagemGradeDto> aulas = new ArrayList<>();
        for (Aula aula : tema.getAulas()) {
            aulas.add(toAulaListagemDto(aula));
        }
        temaListagemGradeDto.setAulas(aulas);
        return temaListagemGradeDto;
    }

    public static GradeCriacaoDto.ModuloListagemGradeDto toModuloListagemDto(Modulo modulo) {
        GradeCriacaoDto.ModuloListagemGradeDto moduloListagemGradeDto = new GradeCriacaoDto.ModuloListagemGradeDto();
        moduloListagemGradeDto.setIdModulo(modulo.getIdModulo());
        moduloListagemGradeDto.setNome(modulo.getNome());
        List<GradeCriacaoDto.ModuloListagemGradeDto.TemaListagemGradeDto> temas = new ArrayList<>();
        for (Tema tema : modulo.getTemas()) {
            temas.add(toTemaListagemDto(tema));
        }
        moduloListagemGradeDto.setTemas(temas);
        return moduloListagemGradeDto;
    }



}
