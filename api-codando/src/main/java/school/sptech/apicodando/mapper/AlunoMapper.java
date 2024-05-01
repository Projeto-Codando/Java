package school.sptech.apicodando.mapper;

import school.sptech.apicodando.domain.aluno.Aluno;
import school.sptech.apicodando.service.alunoService.dto.AlunoCadastroDTO;
import school.sptech.apicodando.service.alunoService.dto.AlunoListagemDTO;
import school.sptech.apicodando.service.alunoService.dto.dtoAuthAluno.AlunoTokenDto;

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

    public static AlunoTokenDto of(Aluno aluno, String token){

        AlunoTokenDto alunoTokenDto = new AlunoTokenDto();

        alunoTokenDto.setUserId(aluno.getIdAluno());
        alunoTokenDto.setToken(token);
        alunoTokenDto.setApelido(aluno.getApelido());
        alunoTokenDto.setNome(aluno.getNome());

        return alunoTokenDto;
    }
}
