package school.sptech.apicodando.api.mapper;

import school.sptech.apicodando.api.domain.aluno.Aluno;
import school.sptech.apicodando.api.domain.avatar.Avatar;
import school.sptech.apicodando.api.domain.turma.Turma;
import school.sptech.apicodando.service.alunoService.dto.AlunoAtualizadoDTO;
import school.sptech.apicodando.service.alunoService.dto.AlunoCadastroDTO;
import school.sptech.apicodando.service.alunoService.dto.AlunoListagemDTO;
import school.sptech.apicodando.service.alunoService.dto.dtoAuthAluno.AlunoTokenDto;
import school.sptech.apicodando.service.avatarService.dto.AvatarListagemDTO;
import school.sptech.apicodando.service.turmaService.dto.TurmaCadastroDTO;
import school.sptech.apicodando.service.turmaService.dto.TurmaListagemDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
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
        aluno.setMoedas(300);

//        Avatar avatar = new Avatar();
//        avatar.setAluno(aluno);
//        avatar.setId(0);
//        avatar.setDescricao("Chimpanzé");
//        avatar.setPreco(0);
//        avatar.setImagemURL("https://raw.githubusercontent.com/Projeto-Codando/bucket-avatares/main/chimpaze.png?token=GHSAT0AAAAAACP2D2UYJDBXR47UNNGKMC5SZSTTO4Q");
//
//        aluno.getAvatares().add(avatar);

        return aluno;
    }

    public static AlunoListagemDTO toDto(Aluno entidade) {
        if (entidade == null) return null;

        AlunoListagemDTO listagemDto = new AlunoListagemDTO();

        listagemDto.setIdAluno(entidade.getIdAluno());
        listagemDto.setNome(entidade.getNome());
        listagemDto.setSobrenome(entidade.getSobrenome());
        listagemDto.setApelido(entidade.getApelido());
        listagemDto.setMoedas(entidade.getMoedas());
        listagemDto.setStatus(entidade.getStatus());
        if(entidade.getTurma() == null){
            listagemDto.setIdTurma("Sem turma informada.");
        }else{
            listagemDto.setIdTurma(entidade.getTurma().getIdTurma().toString());
        }

//        if(entidade.getAvatares().isEmpty() || entidade.getAvatares() == null){
//            entidade.setAvatares(new ArrayList<>());
//            Avatar avatar = new Avatar();
//            avatar.setDescricao("Chimpanzé");
//            avatar.setPreco(0);
//            avatar.setImagemURL("https://raw.githubusercontent.com/Projeto-Codando/bucket-avatares/main/chimpaze.png?token=GHSAT0AAAAAACP2D2UYJDBXR47UNNGKMC5SZSTTO4Q");
//            listagemDto.getAvatares().add(toDtoAvatar(avatar));
//        }else{
//            listagemDto.setAvatares(toDtoAvatar(entidade.getAvatares()));
//        }


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

    public static AvatarListagemDTO toDtoAvatarEntity(Avatar avatar) {
        if (avatar == null) return null;

        AvatarListagemDTO avatarListagemDTO = new AvatarListagemDTO();
        avatarListagemDTO.setId(avatar.getId());
        avatarListagemDTO.setDescricao(avatar.getDescricao());
        avatarListagemDTO.setPreço(avatar.getPreco());

        return avatarListagemDTO;
    }

    public static AlunoListagemDTO.AvatarListagemDTO toDtoAvatar(Avatar avatar) {
        if (avatar == null) return null;

        AlunoListagemDTO.AvatarListagemDTO avatarListagemDTO = new AlunoListagemDTO.AvatarListagemDTO();
        avatarListagemDTO.setIdAvatar(avatar.getId());
        avatarListagemDTO.setDescricao(avatar.getDescricao());
        avatarListagemDTO.setPreco(avatar.getPreco());

        return avatarListagemDTO;
    }



    public static List<AlunoListagemDTO.AvatarListagemDTO> toDtoAvatar(List<Avatar> avatares) {
        return avatares.stream().map(avatar -> {
            AlunoListagemDTO.AvatarListagemDTO avatarListagemDTO = new AlunoListagemDTO.AvatarListagemDTO();
            avatarListagemDTO.setIdAvatar(avatar.getId());
            avatarListagemDTO.setDescricao(avatar.getDescricao());
            avatarListagemDTO.setPreco(avatar.getPreco());
            return avatarListagemDTO;
        }).collect(Collectors.toList());
    }

    public static Aluno toEntity(AlunoAtualizadoDTO dto, UUID id) {
        if (dto == null) {
            return null;
        }

        Aluno aluno = new Aluno();
        aluno.setIdAluno(id);
        aluno.setNome(dto.getNome());
        aluno.setSobrenome(dto.getSobrenome());
        aluno.setApelido(dto.getApelido());
        aluno.setSenha(dto.getSenha());

        return aluno;
    }


}
