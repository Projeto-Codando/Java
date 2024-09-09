package school.sptech.apicodando.api.mapper;

import school.sptech.apicodando.api.domain.aula.Aula;
import school.sptech.apicodando.api.domain.tema.Tema;
import school.sptech.apicodando.service.aulaService.dto.AulaCriacaoDTO;
import school.sptech.apicodando.service.aulaService.dto.AulaListagemDTO;

import java.util.ArrayList;
import java.util.List;

import static com.fasterxml.jackson.databind.type.LogicalType.Collection;

public class AulaMapper {

    public static AulaListagemDTO toDto(Aula aula) {

        if (aula == null) {
            return null;
        }

        AulaListagemDTO aulaListagemDTO = new AulaListagemDTO();
        aulaListagemDTO.setId(aula.getId());
        aulaListagemDTO.setTitulo(aula.getTitulo());
        aulaListagemDTO.setDescricao(aula.getDescricao());
        aulaListagemDTO.setNivelDificuldade(aula.getNivelDificuldade());
        aulaListagemDTO.setPontuacaoMaxima(aula.getPontuacaoMaxima());

//        aulaListagemDTO.setTema(toTemaDto(aula.getTema()));
        return aulaListagemDTO;
    }

//    private static AulaListagemDTO.TemaDto toTemaDto(Tema tema) {
//
//        if (tema == null) {
//            return null;
//        }
//
//        AulaListagemDTO.TemaDto temaDto = new AulaListagemDTO.TemaDto();
//        temaDto.setId(tema.getIdTema());
//        temaDto.setNome(tema.getNome());
//
//        return temaDto;
//    }

    public static List<AulaListagemDTO> toDto(List<Aula> aulas) {

        if (aulas == null) {
            return new ArrayList<>();
        }

        return aulas.stream().map(AulaMapper::toDto).toList();
    }

    public static Aula toEntity(AulaCriacaoDTO aulaCriacaoDTO) {

        if (aulaCriacaoDTO == null) {
            return null;
        }

        Aula aula = new Aula();
        aula.setTitulo(aulaCriacaoDTO.getTitulo());
        aula.setDescricao(aulaCriacaoDTO.getDescricao());
        aula.setNivelDificuldade(aulaCriacaoDTO.getNivelDificuldade());
        aula.setPontuacaoMaxima(aulaCriacaoDTO.getPontuacaoMaxima());

        return aula;
    }

}
