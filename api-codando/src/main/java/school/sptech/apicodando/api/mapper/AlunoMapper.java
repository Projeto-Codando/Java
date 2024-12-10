package school.sptech.apicodando.api.mapper;

import school.sptech.apicodando.api.domain.aluno.Aluno;
import school.sptech.apicodando.api.domain.avatar.Avatar;
import school.sptech.apicodando.api.utils.exception.MapperException;
import school.sptech.apicodando.service.alunoService.dto.AlunoAtualizadoDTO;
import school.sptech.apicodando.service.alunoService.dto.AlunoCadastroDTO;
import school.sptech.apicodando.service.alunoService.dto.AlunoListagemDTO;
import school.sptech.apicodando.service.alunoService.dto.dtoAuthAluno.AlunoTokenDto;
import school.sptech.apicodando.service.avatarService.dto.AvatarListagemDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


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
        aluno.setSenhaTurma(dto.getSenhaTurma());
        aluno.setRespostas(new ArrayList<>());

        return aluno;
    }

    public static AlunoListagemDTO toDto(Aluno entidade) {
        if (entidade == null) return null;
        try {
            AlunoListagemDTO listagemDto = new AlunoListagemDTO();

            listagemDto.setIdAluno(entidade.getId());
            listagemDto.setNome(entidade.getNome());
            listagemDto.setSobrenome(entidade.getSobrenome());
            listagemDto.setApelido(entidade.getApelido());
            listagemDto.setMoedas(entidade.getMoedas());
            listagemDto.setStatus(entidade.getStatus());

            listagemDto.setIdAvatar(entidade.getIdAvatar());

            if (entidade.getTurma() == null) {
                listagemDto.setIdTurma("Sem turma informada.");
            } else {
                listagemDto.setIdTurma(entidade.getTurma().getIdTurma().toString());
            }

            listagemDto.setAvatares(toDtoAvatar(entidade.getAvatares()));
            List<AlunoListagemDTO.RespostaListagemDto> respostas = new ArrayList<>();
            entidade.getRespostas().forEach(resposta -> {
                AlunoListagemDTO.RespostaListagemDto respostaListagemDto = new AlunoListagemDTO.RespostaListagemDto();
                respostaListagemDto.setIdResposta(resposta.getIdResposta());
                respostaListagemDto.setTexto(resposta.getTexto());
                respostaListagemDto.setCorreta(resposta.getCorreta());
//                respostaListagemDto.setContador(resposta.getContador());
                respostaListagemDto.setIdPergunta(resposta.getPergunta().getId());
                respostas.add(respostaListagemDto);
            });
            listagemDto.setIdRespostas(respostas);

            return listagemDto;
        } catch (Exception e) {
            throw new MapperException("Erro ao mapear Aluno para AlunoListagemDTO", e);
        }
    }

    public static Aluno toEntity(AlunoAtualizadoDTO dto, int id) {
        if (dto == null) {
            return null;
        }

        Aluno aluno = new Aluno();
        aluno.setId(id);
        aluno.setNome(dto.getNome());
        aluno.setSobrenome(dto.getSobrenome());
        aluno.setApelido(dto.getApelido());
        aluno.setSenha(dto.getSenha());

        return aluno;
    }

    public static AlunoListagemDTO.AvatarListagemDTO toDtoAvatar(Avatar avatar) {
        if (avatar == null) return null;

        AlunoListagemDTO.AvatarListagemDTO avatarListagemDTO = new AlunoListagemDTO.AvatarListagemDTO();
        avatarListagemDTO.setId(avatar.getId());
        avatarListagemDTO.setDescricao(avatar.getDescricao());
        avatarListagemDTO.setPreco(avatar.getPreco());
        avatarListagemDTO.setImagemURL(avatar.getImagemURL());

        return avatarListagemDTO;
    }

    public static List<AlunoListagemDTO.AvatarListagemDTO> toDtoAvatar(List<Avatar> avatares) {
        return avatares.stream().map(AlunoMapper::toDtoAvatar).collect(Collectors.toList());
    }
    public static List<AlunoListagemDTO> toDto(List<Aluno> entidade) {
        List<AlunoListagemDTO> dtos = new ArrayList<>();
        for (Aluno e : entidade) {
            dtos.add(toDto(e));
        }
        return dtos;
    }

    public static AlunoTokenDto of(Aluno aluno, String token) {

        AlunoTokenDto alunoTokenDto = new AlunoTokenDto();

        alunoTokenDto.setUserId(aluno.getId());
        alunoTokenDto.setToken(token);
        alunoTokenDto.setApelido(aluno.getApelido());
        alunoTokenDto.setNome(aluno.getNome());

        // Setando o firebase token
        alunoTokenDto.setFcmToken(aluno.getFcmToken());

        AlunoTokenDto.AlunoListagemDTO alunoListagemDTO = new AlunoTokenDto.AlunoListagemDTO();
        alunoListagemDTO.setIdAluno(aluno.getId());
        alunoListagemDTO.setNome(aluno.getNome());
        alunoListagemDTO.setSobrenome(aluno.getSobrenome());
        alunoListagemDTO.setApelido(aluno.getApelido());
        alunoListagemDTO.setStatus(aluno.getStatus());
        alunoListagemDTO.setMoedas(aluno.getMoedas());
        alunoListagemDTO.setIdTurma(aluno.getTurma().getIdTurma().toString());
        alunoListagemDTO.setIdAvatar(aluno.getIdAvatar());

        alunoListagemDTO.setAvatares(toDtoAvatarEntity(aluno.getAvatares()));
        alunoTokenDto.setAlunoListagemDTO(alunoListagemDTO);

        return alunoTokenDto;
    }

    public static AvatarListagemDTO toDtoAvatarEntity(Avatar avatar) {
        if (avatar == null) return null;

        AvatarListagemDTO avatarListagemDTO = new AvatarListagemDTO();
        avatarListagemDTO.setId(avatar.getId());
        avatarListagemDTO.setDescricao(avatar.getDescricao());
        avatarListagemDTO.setPreço(avatar.getPreco());
        avatarListagemDTO.setImagemURL(avatar.getImagemURL());

        return avatarListagemDTO;
    }

    public static List<AlunoTokenDto.AlunoListagemDTO.AvatarListagemDTO> toDtoAvatarEntity(List<Avatar> avatares) {
        return avatares.stream().map(avatar -> {
            AlunoTokenDto.AlunoListagemDTO.AvatarListagemDTO avatarListagemDTO = new AlunoTokenDto.AlunoListagemDTO.AvatarListagemDTO();
            avatarListagemDTO.setId(avatar.getId());
            avatarListagemDTO.setDescricao(avatar.getDescricao());
            avatarListagemDTO.setPreço(avatar.getPreco());
            avatarListagemDTO.setImagemURL(avatar.getImagemURL());
            return avatarListagemDTO;
        }).collect(Collectors.toList());
    }


}
