package school.sptech.apicodando.api.mapper;

import lombok.Data;
import school.sptech.apicodando.api.domain.grade.Grade;
import school.sptech.apicodando.api.domain.modulo.Modulo;
import school.sptech.apicodando.api.domain.turma.Turma;
import school.sptech.apicodando.service.moduloService.dto.ModuloCadastroDTO;
import school.sptech.apicodando.service.moduloService.dto.ModuloListagemDTO;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class ModuloMapper {

    public static ModuloListagemDTO toDto(Modulo modulo) {
        ModuloListagemDTO dto = new ModuloListagemDTO();
        dto.setIdModulo(modulo.getIdModulo());
        dto.setNome(modulo.getNome());
        dto.setTemas(modulo.getTemas().stream().map(TemaMapper::toDto).collect(Collectors.toList()));
//        dto.setIdGrade(modulo.getGrade().getIdGrade());
//        dto.getGrade().setTurma(toTurmaListagem(modulo.getGrade().getFkTurma()));
        return dto;
    }

//    public static ModuloListagemDTO.GradeListagemDTO toGradeListagem(Grade grade) {
//        ModuloListagemDTO.GradeListagemDTO dto = new ModuloListagemDTO.GradeListagemDTO();
//        dto.setIdGrade(grade.getIdGrade());
//        return dto;
//    }
    public static List<ModuloListagemDTO> toDto (List<Modulo> modulos) {
        return modulos.stream().map(ModuloMapper::toDto).collect(Collectors.toList());
    }


    public static Modulo toEntity(ModuloCadastroDTO dto) {
        Modulo modulo = new Modulo();
        modulo.setNome(dto.getNome());
        return modulo;
    }

//    public static ModuloListagemDTO.GradeListagemDTO.TurmaListagemDTO toTurmaListagem(Turma turma) {
//        ModuloListagemDTO.GradeListagemDTO.TurmaListagemDTO dto = new ModuloListagemDTO.GradeListagemDTO.TurmaListagemDTO();
//        dto.setIdTurma(turma.getIdTurma());
//        dto.setNome(turma.getNome());
//        dto.setAlunos(turma.getAlunos().stream().map(aluno -> {
//            ModuloListagemDTO.GradeListagemDTO.TurmaListagemDTO.AlunoListagemDTO alunoDto = new ModuloListagemDTO.GradeListagemDTO.TurmaListagemDTO.AlunoListagemDTO();
//            alunoDto.setIdAluno(aluno.getIdAluno());
//            alunoDto.setNome(aluno.getNome());
//            alunoDto.setSobrenome(aluno.getSobrenome());
//            alunoDto.setApelido(aluno.getApelido());
//            alunoDto.setMoedas(aluno.getMoedas());
//            alunoDto.setStatus(aluno.getStatus());
//            return alunoDto;
//        }).collect(Collectors.toList()));
//        return dto;
//    }

}
