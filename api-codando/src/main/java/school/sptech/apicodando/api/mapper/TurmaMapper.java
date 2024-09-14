package school.sptech.apicodando.api.mapper;

import school.sptech.apicodando.api.domain.aluno.Aluno;
import school.sptech.apicodando.api.domain.avatar.Avatar;
import school.sptech.apicodando.api.domain.educador.Educador;
import school.sptech.apicodando.api.domain.escolaridade.Escolaridade;
import school.sptech.apicodando.api.domain.modulo.Modulo;
import school.sptech.apicodando.api.domain.turma.Turma;
import school.sptech.apicodando.service.turmaService.dto.TurmaAtualizaDTO;
import school.sptech.apicodando.service.turmaService.dto.TurmaCadastroDTO;
import school.sptech.apicodando.service.turmaService.dto.TurmaListagemDTO;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TurmaMapper {
    public static Turma toEntity(TurmaCadastroDTO dto, Escolaridade escolaridade, Educador educador, Modulo modulo) {
        if (dto == null) {
            return null;
        }

        Turma turma = new Turma();
        turma.setNome(dto.getNome());
        turma.setSenha(dto.getSenha());
        turma.setEscolaridade(escolaridade);
        turma.setEducador(educador);
        turma.setAlunos(dto.getAlunos());
//        turma.setModulo(modulo);

        return turma;
    }

    public static List<Aluno> toDtoTurmaAluno(List<Aluno> alunos) {
        if (alunos == null) return null;

        return alunos.stream().map(aluno -> {
            Aluno alunoDto = new Aluno();
            alunoDto.setId(aluno.getId());
            alunoDto.setNome(aluno.getNome());
            alunoDto.setSobrenome(aluno.getSobrenome());
            alunoDto.setApelido(aluno.getApelido());
            alunoDto.setStatus(aluno.getStatus());
            alunoDto.setMoedas(aluno.getMoedas());
            return alunoDto;
        }).collect(Collectors.toList());
    }

    public static TurmaListagemDTO toDto(Turma entidade) {
        if (entidade == null) return null;

        TurmaListagemDTO listagemDto = new TurmaListagemDTO();
        listagemDto.setIdTurma(entidade.getIdTurma());
        listagemDto.setNome(entidade.getNome());
        listagemDto.setSenha(entidade.getSenha());
        listagemDto.setStatusTurma(entidade.isStatusTurma());

        listagemDto.setFkEscolaridade(toEscolaridadeDto(entidade.getEscolaridade()));
        listagemDto.setFkEducador(toEducadorDto(entidade.getEducador()));
        listagemDto.setAlunos(toAlunoDto(entidade.getAlunos()));
//        listagemDto.setFkModulo(entidade.getModulo().getIdModulo());
        listagemDto.setMensagens(MensagemMapper.toDto(entidade.getMensagens())); 


        return listagemDto;
    }

    public static Turma toEntity(TurmaAtualizaDTO dto, Escolaridade escolaridade, Educador educador) {
        if (dto == null) {
            return null;
        }

        Turma turma = new Turma();
        turma.setNome(dto.getNome());
        turma.setSenha(dto.getSenha());
        turma.setEscolaridade(escolaridade);
        turma.setEducador(educador);

        return turma;
    }

    private static List<TurmaListagemDTO.AlunoListagemDTO> toAlunoDto(List<Aluno> entidades){
        if (entidades == null) return null;

        List<TurmaListagemDTO.AlunoListagemDTO> dtos = new ArrayList<>();
        for (Aluno e : entidades) {
            TurmaListagemDTO.AlunoListagemDTO dto = new TurmaListagemDTO.AlunoListagemDTO();
            dto.setIdAluno(e.getId());
            dto.setNome(e.getNome());
            dto.setSobrenome(e.getSobrenome());
            dto.setApelido(e.getApelido());
            dto.setStatus(e.getStatus());
            dto.setMoedas(e.getMoedas());
            dto.setIdAvatar(e.getIdAvatar());
            dto.setAvatar(toAvatarDto(e.getAvatares()));
            dtos.add(dto);
        }
        return dtos;
    }

    public static TurmaListagemDTO.AlunoListagemDTO.AvatarListagemDTO toAvatarDto(Avatar entidades){
        if (entidades == null) return null;

        TurmaListagemDTO.AlunoListagemDTO.AvatarListagemDTO dto = new TurmaListagemDTO.AlunoListagemDTO.AvatarListagemDTO();
        dto.setIdAvatar(entidades.getId());
        dto.setDescricao(entidades.getDescricao());
        dto.setPreco(entidades.getPreco());
        dto.setImagemURL(entidades.getImagemURL());

        return dto;
    }

    public static List<TurmaListagemDTO.AlunoListagemDTO.AvatarListagemDTO> toAvatarDto(List<Avatar> entidades){
        if (entidades == null) return null;

        return entidades.stream().map(avatar -> {
            TurmaListagemDTO.AlunoListagemDTO.AvatarListagemDTO dto = new TurmaListagemDTO.AlunoListagemDTO.AvatarListagemDTO();
            dto.setIdAvatar(avatar.getId());
            dto.setDescricao(avatar.getDescricao());
            dto.setPreco(avatar.getPreco());
            dto.setImagemURL(avatar.getImagemURL());
            return dto;
        }).collect(Collectors.toList());
    }

    public static TurmaListagemDTO.EscolaridadeListagemDTO toEscolaridadeDto(Escolaridade entidades){
        if (entidades == null) return null;

        TurmaListagemDTO.EscolaridadeListagemDTO dto = new TurmaListagemDTO.EscolaridadeListagemDTO();
        dto.setIdEscolaridade(entidades.getIdEscolaridade());
        dto.setDescricao(entidades.getDescricao());
        return dto;
    }

    private static TurmaListagemDTO.EducadorListagemDTO toEducadorDto(Educador entidades){
        if (entidades == null) return null;

        TurmaListagemDTO.EducadorListagemDTO dto = new TurmaListagemDTO.EducadorListagemDTO();
        dto.setIdEducador(entidades.getIdEducador());
        dto.setNome(entidades.getNome());
        dto.setSobrenome(entidades.getSobrenome());
        return dto;
    }

    public static List<TurmaListagemDTO> toDto(List<Turma> entidade) {
        List<TurmaListagemDTO> dtos = new ArrayList<>();
        for (Turma e : entidade) {
            dtos.add(toDto(e));
        }
        return dtos;
    }

}
