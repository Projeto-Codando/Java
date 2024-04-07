package school.sptech.apicodando.mapper;

import school.sptech.apicodando.entity.Aluno;
import school.sptech.apicodando.entity.Educador;
import school.sptech.apicodando.record.DTOs.aluno.AlunoListagemDTO;
import school.sptech.apicodando.record.DTOs.educador.EducadorCadastroDTO;
import school.sptech.apicodando.record.DTOs.educador.EducadorListagemDTO;

import java.util.ArrayList;
import java.util.List;

public class EducadorMapper {
    public static EducadorListagemDTO toDto(Educador entity) {
        if (entity == null) return null;

        EducadorListagemDTO listagemDTO = new EducadorListagemDTO();
        listagemDTO.setIdEducador(entity.getIdEducador());
        listagemDTO.setNome(entity.getNome());
        listagemDTO.setSobrenome(entity.getSobrenome());
        listagemDTO.setEmail(entity.getEmail());

        return listagemDTO;
    }

    public static Educador toEntity(EducadorCadastroDTO dto) {
        if (dto == null) return null;

        Educador entity = new Educador();
        entity.setNome(dto.getNome());
        entity.setSobrenome(dto.getSobrenome());
        entity.setEmail(dto.getEmail());
        entity.setSenha(dto.getSenha());

        return entity;
    }

    public static List<EducadorListagemDTO> toDto(List<Educador> entidade) {
        List<EducadorListagemDTO> dtos = new ArrayList<>();
        for (Educador e : entidade) {
            dtos.add(toDto(e));
        }
        return dtos;
    }
}
