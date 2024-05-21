package school.sptech.apicodando.service.moduloService.dto;

import lombok.Data;
import school.sptech.apicodando.api.domain.grade.Grade;
import school.sptech.apicodando.api.domain.modulo.Modulo;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class ModuloMapper {

    public static ModuloListagemDTO toDto(Modulo modulo) {
        ModuloListagemDTO dto = new ModuloListagemDTO();
        dto.setIdModulo(modulo.getIdModulo());
        dto.setNome(modulo.getNome());
        dto.setGrade(toDto(modulo.getGrade()));
        return dto;
    }

    public static ModuloListagemDTO.GradeListagemDTO toDto(Grade grade) {
        ModuloListagemDTO.GradeListagemDTO dto = new ModuloListagemDTO.GradeListagemDTO();
        dto.setIdGrade(grade.getIdGrade());
        return dto;
    }
    public static List<ModuloListagemDTO> toDto (List<Modulo> modulos) {
        return modulos.stream().map(ModuloMapper::toDto).collect(Collectors.toList());
    }


    public static Modulo toEntity(ModuloCadastroDTO dto) {
        Modulo modulo = new Modulo();
        modulo.setNome(dto.getNome());
        return modulo;
    }

}
