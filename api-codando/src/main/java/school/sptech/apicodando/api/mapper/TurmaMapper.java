package school.sptech.apicodando.api.mapper;

import school.sptech.apicodando.api.domain.aluno.Aluno;
import school.sptech.apicodando.api.domain.turma.Turma;
import school.sptech.apicodando.service.turmaService.dto.TurmaAtualizaDTO;
import school.sptech.apicodando.api.mapper.AlunoMapper;
import school.sptech.apicodando.service.turmaService.dto.TurmaCadastroDTO;
import school.sptech.apicodando.service.turmaService.dto.TurmaListagemDTO;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TurmaMapper {
    public static Turma toEntity(TurmaCadastroDTO dto) {
        if (dto == null) {
            return null;
        }

        Turma turma = new Turma();
        turma.setNome(dto.getNome());
        turma.setSenha(dto.getSenha());
        turma.setFkEscolaridade(dto.getFkEscolaridade());
        turma.setFkEducador(dto.getFkEducador());
        turma.setAlunos(dto.getAlunos());

        return turma;
    }

    public static List<Aluno> toDtoTurmaAluno(List<Aluno> alunos) {
        if (alunos == null) return null;

        return alunos.stream().map(aluno -> {
            Aluno alunoDto = new Aluno();
            alunoDto.setIdAluno(aluno.getIdAluno());
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
        listagemDto.setFkEscolaridade(entidade.getFkEscolaridade());
        listagemDto.setFkEducador(entidade.getFkEducador());
        listagemDto.setStatusTurma(entidade.isStatusTurma());

        listagemDto.setAlunos(toAlunoDto(entidade.getAlunos()));

        return listagemDto;
    }

    private static List<TurmaListagemDTO.AlunoListagemDTO> toAlunoDto(List<Aluno> entidades){
        if (entidades == null) return null;

        List<TurmaListagemDTO.AlunoListagemDTO> dtos = new ArrayList<>();
        for (Aluno e : entidades) {
            TurmaListagemDTO.AlunoListagemDTO dto = new TurmaListagemDTO.AlunoListagemDTO();
            dto.setIdAluno(e.getIdAluno());
            dto.setNome(e.getNome());
            dto.setSobrenome(e.getSobrenome());
            dto.setApelido(e.getApelido());
            dto.setStatus(e.getStatus());
            dto.setMoedas(e.getMoedas());
            dtos.add(dto);
        }
        return dtos;
    }

    public static List<TurmaListagemDTO> toDto(List<Turma> entidade) {
        List<TurmaListagemDTO> dtos = new ArrayList<>();
        for (Turma e : entidade) {
            dtos.add(toDto(e));
        }
        return dtos;
    }





}
