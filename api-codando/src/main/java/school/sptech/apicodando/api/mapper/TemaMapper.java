package school.sptech.apicodando.api.mapper;

import school.sptech.apicodando.api.domain.modulo.Modulo;
import school.sptech.apicodando.api.domain.tema.Tema;
import school.sptech.apicodando.service.temaService.dto.TemaCadastroDTO;
import school.sptech.apicodando.service.temaService.dto.TemaListagemDTO;

import java.util.ArrayList;
import java.util.List;

public class TemaMapper {

    public static TemaListagemDTO toDto(Tema tema) {
        TemaListagemDTO dto = new TemaListagemDTO();
        dto.setIdTema(tema.getIdTema());
        dto.setNome(tema.getNome());
        dto.setAulas(AulaMapper.toDto(tema.getAulas()));
        dto.setAulas(AulaMapper.toDto(tema.getAulas()));
//        dto.setModulo(toModuloDto(tema.getModulo()));
        return dto;
    }

//    public static TemaListagemDTO.ModuloDto toModuloDto(Modulo modulo) {
//        TemaListagemDTO.ModuloDto dto = new TemaListagemDTO.ModuloDto();
//        dto.setId(modulo.getIdModulo());
//        dto.setNome(modulo.getNome());
//        return dto;
//    }

    public static List<TemaListagemDTO> toDto(List<Tema> temas) {

        return temas.stream().map(TemaMapper::toDto).toList();
    }

    public static Tema toEntity(TemaCadastroDTO dto) {
        Tema tema = new Tema();
        tema.setNome(dto.getNome());
        return tema;
    }

}
