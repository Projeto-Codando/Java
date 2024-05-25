package school.sptech.apicodando.api.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import school.sptech.apicodando.api.domain.aluno.Aluno;
import school.sptech.apicodando.api.domain.aula.Aula;
import school.sptech.apicodando.api.domain.progressoAluno.ProgressoAluno;
import school.sptech.apicodando.service.alunoService.AlunoService;
import school.sptech.apicodando.service.aulaService.AulaService;
import school.sptech.apicodando.service.progressoAlunoService.dto.ProgressoAlunoCadastroDTO;
import school.sptech.apicodando.service.progressoAlunoService.dto.ProgressoAlunoListagemDto;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProgressoAlunoMapper {

    public static ProgressoAluno toEntity(ProgressoAlunoCadastroDTO progressoAlunoCadastro) {
        ProgressoAluno progressoAluno = new ProgressoAluno();
        progressoAluno.setPontuacaoAluno(progressoAlunoCadastro.getPontuacaoAluno());
        progressoAluno.setStatusAula(progressoAlunoCadastro.getStatusAula());
        return progressoAluno;
    }

    public ProgressoAlunoCadastroDTO toDTO(ProgressoAluno progressoAluno) {
        ProgressoAlunoCadastroDTO progressoAlunoCadastroDTO = new ProgressoAlunoCadastroDTO();
        progressoAlunoCadastroDTO.setPontuacaoAluno(progressoAluno.getPontuacaoAluno());
        progressoAlunoCadastroDTO.setStatusAula(progressoAluno.getStatusAula());
        progressoAlunoCadastroDTO.setFkAluno(progressoAluno.getFkAluno().getIdAluno());
        progressoAlunoCadastroDTO.setFkAula(progressoAluno.getFkAula().getId());
        return progressoAlunoCadastroDTO;
    }

    public static ProgressoAlunoListagemDto toListagemDto(ProgressoAluno progressoAluno) {
        ProgressoAlunoListagemDto progressoAlunoListagemDto = new ProgressoAlunoListagemDto();
        progressoAlunoListagemDto.setIdProgressoAluno(progressoAluno.getIdProgressoAluno());
        progressoAlunoListagemDto.setPontuacaoAluno(progressoAluno.getPontuacaoAluno());
        progressoAlunoListagemDto.setStatusAula(progressoAluno.getStatusAula());
        progressoAlunoListagemDto.setAluno(toAlunoListagemDto(progressoAluno.getFkAluno()));
        progressoAlunoListagemDto.setAula(toAulaListagemDto(progressoAluno.getFkAula()));
        return progressoAlunoListagemDto;
    }

    public static ProgressoAlunoListagemDto.AlunoListagemDto toAlunoListagemDto(Aluno aluno) {
        ProgressoAlunoListagemDto.AlunoListagemDto alunoListagemDto = new ProgressoAlunoListagemDto().new AlunoListagemDto();
        alunoListagemDto.setIdAluno(aluno.getIdAluno());
        alunoListagemDto.setNome(aluno.getNome());
        alunoListagemDto.setSobrenome(aluno.getSobrenome());
        alunoListagemDto.setApelido(aluno.getApelido());
        alunoListagemDto.setStatus(aluno.getStatus());
        return alunoListagemDto;
    }

    public static ProgressoAlunoListagemDto.AulaListagemDto toAulaListagemDto(Aula aula) {
        ProgressoAlunoListagemDto.AulaListagemDto aulaListagemDto = new ProgressoAlunoListagemDto().new AulaListagemDto();
        aulaListagemDto.setIdAula(aula.getId());
        aulaListagemDto.setNome(aula.getTitulo());
        aulaListagemDto.setDescricao(aula.getDescricao());
        aulaListagemDto.setPontuacaoMaxima(aula.getPontuacaoMaxima());
        return aulaListagemDto;
    }

    public static ProgressoAluno toEntity(ProgressoAlunoCadastroDTO progressoAlunoCadastro, Aluno aluno, Aula aula) {
        ProgressoAluno progressoAluno = new ProgressoAluno();
        progressoAluno.setPontuacaoAluno(progressoAlunoCadastro.getPontuacaoAluno());
        progressoAluno.setStatusAula(progressoAlunoCadastro.getStatusAula());
        progressoAluno.setFkAluno(aluno);
        progressoAluno.setFkAula(aula);
        return progressoAluno;
    }

    public static List<ProgressoAlunoListagemDto> toListagemDto(List<ProgressoAluno> progressoAlunos) {
        return progressoAlunos.stream().map(ProgressoAlunoMapper::toListagemDto).collect(Collectors.toList());
    }

}
