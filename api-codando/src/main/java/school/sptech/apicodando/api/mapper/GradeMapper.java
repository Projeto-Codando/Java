package school.sptech.apicodando.api.mapper;

import school.sptech.apicodando.api.domain.aluno.Aluno;
import school.sptech.apicodando.api.domain.aula.Aula;
import school.sptech.apicodando.api.domain.educador.Educador;
import school.sptech.apicodando.api.domain.escolaridade.Escolaridade;
import school.sptech.apicodando.api.domain.grade.Grade;
import school.sptech.apicodando.api.domain.modulo.Modulo;
import school.sptech.apicodando.api.domain.tema.Tema;
import school.sptech.apicodando.api.domain.turma.Turma;
import school.sptech.apicodando.service.gradeService.dto.GradeCadastroDto;
import school.sptech.apicodando.service.gradeService.dto.GradeListagemDto;
import school.sptech.apicodando.service.turmaService.dto.TurmaListagemDTO;

import java.util.ArrayList;
import java.util.List;



public class GradeMapper {

    public static GradeListagemDto toDto(Grade grade) {
        GradeListagemDto dto = new GradeListagemDto();
        dto.setIdGrade(grade.getIdGrade());
        return dto;
    }


    public static Grade toEntity(GradeCadastroDto gradeCadastroDto) {
        Grade grade = new Grade();
//        grade.setFkTurma(gradeCadastroDto.getFkTurma());

        return grade;
    }

}
