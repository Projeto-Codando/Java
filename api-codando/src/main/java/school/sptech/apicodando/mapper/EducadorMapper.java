package school.sptech.apicodando.mapper;

import school.sptech.apicodando.domain.educador.Educador;
import school.sptech.apicodando.service.educadorService.dto.EducadorCadastroDTO;
import school.sptech.apicodando.service.educadorService.dto.EducadorListagemDTO;
import school.sptech.apicodando.service.educadorService.dto.dtoEducador.EducadorTokenDto;

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

//    public static EducadorCadastroDTO toDto(Educador entity) {
//        if (entity == null) return null;
//
//        EducadorCadastroDTO criacaoDto = new EducadorCadastroDTO();
////        criacaoDto.setIdEducador(entity.getIdEducador());
//        criacaoDto.setNome(entity.getNome());
//        criacaoDto.setSobrenome(entity.getSobrenome());
//        criacaoDto.setEmail(entity.getEmail());
//
//        return criacaoDto;
//    }

    public static Educador toEntity(EducadorCadastroDTO dto) {
        if (dto == null) return null;

        Educador educador = new Educador();
        educador.setNome(dto.getNome());
        educador.setSobrenome(dto.getSobrenome());
        educador.setEmail(dto.getEmail());
        educador.setSenha(dto.getSenha());

        return educador;
    }

    public static List<EducadorListagemDTO> toDto(List<Educador> entidade) {
        List<EducadorListagemDTO> dtos = new ArrayList<>();
        for (Educador e : entidade) {
            dtos.add(toDto(e));
        }
        return dtos;
    }

    public static EducadorTokenDto of(Educador aluno, String token){

        EducadorTokenDto educadorTokenDto = new EducadorTokenDto();

        educadorTokenDto.setUserId(aluno.getIdEducador());
        educadorTokenDto.setToken(token);
        educadorTokenDto.setEmail(aluno.getEmail());
        educadorTokenDto.setNome(aluno.getNome());

        return educadorTokenDto;
    }
}
