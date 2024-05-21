package school.sptech.apicodando.service.gradeService.dto;

import school.sptech.apicodando.api.domain.grade.Grade;

public class GradeMapper {

    public static GradeListagemDto toDto(Grade grade) {
        GradeListagemDto dto = new GradeListagemDto();
        dto.setIdGrade(grade.getIdGrade());
        return dto;
    }



    public static Grade toEntity(GradeCadastroDto gradeCadastroDto) {
        Grade grade = new Grade();
        return grade;
    }

}
