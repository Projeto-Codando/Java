package school.sptech.apicodando.mapper;

import school.sptech.apicodando.entity.Aluno;
import school.sptech.apicodando.record.DTOs.aluno.AlunoCadastroDTO;
import school.sptech.apicodando.record.DTOs.aluno.AlunoListagemDTO;

import java.util.ArrayList;
import java.util.List;

public class AlunoMapper {
    public static Aluno toEntity(AlunoCadastroDTO dto) {
        if (dto == null) {
            return null;
        }

        Aluno aluno = new Aluno();
        aluno.setNome(dto.getNome());
        aluno.setSobrenome(dto.getSobrenome());
        aluno.setApelido(dto.getApelido());
        aluno.setSenha(dto.getSenha());
        aluno.setStatus(dto.getStatus());
        aluno.setMoedas(dto.getMoedas());

        return aluno;
    }

    public static AlunoListagemDTO toDto(Aluno entidade) {
        if (entidade == null) return null;

        AlunoListagemDTO listagemDto = new AlunoListagemDTO();
        listagemDto.setIdAluno(entidade.getIdAluno());
        listagemDto.setNome(entidade.getNome());
        listagemDto.setSobrenome(entidade.getSobrenome());
        listagemDto.setApelido(entidade.getApelido());
        listagemDto.setStatus(entidade.getStatus());
        listagemDto.setMoedas(entidade.getMoedas());

        return listagemDto;
    }

    public static List<AlunoListagemDTO> toDto(List<Aluno> entidade) {
        List<AlunoListagemDTO> dtos = new ArrayList<>();
        for (Aluno e : entidade) {
            dtos.add(toDto(e));
        }
        return dtos;
    }
}
