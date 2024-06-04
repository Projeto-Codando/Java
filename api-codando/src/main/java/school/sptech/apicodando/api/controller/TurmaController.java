package school.sptech.apicodando.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.apicodando.api.domain.aluno.Aluno;
import school.sptech.apicodando.api.domain.turma.Turma;
import school.sptech.apicodando.api.mapper.AlunoMapper;
import school.sptech.apicodando.api.mapper.TurmaMapper;
import school.sptech.apicodando.service.alunoService.dto.AlunoListagemDTO;
import school.sptech.apicodando.service.presencaService.PresencaDTO;
import school.sptech.apicodando.service.presencaService.PresencaService;
import school.sptech.apicodando.service.turmaService.TurmaService;
import school.sptech.apicodando.service.turmaService.dto.TurmaAtualizaDTO;
import school.sptech.apicodando.service.turmaService.dto.TurmaCadastroDTO;
import school.sptech.apicodando.service.turmaService.dto.TurmaListagemDTO;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Formatter;
import java.util.List;
import java.util.UUID;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/turmas")
@RequiredArgsConstructor
@SecurityRequirement(name = "Bearer")
public class TurmaController {

    private final TurmaService turmaService;

    @Operation(summary = "Cadastar", description = "Método que cadastra uma turma!", tags = "Turma")
    @PostMapping
    public ResponseEntity<TurmaListagemDTO> criar(@RequestBody @Valid TurmaCadastroDTO novaTurma) {
        Turma turmaCadastrada = turmaService.criar(novaTurma);
        return status(201).body(TurmaMapper.toDto(turmaCadastrada));
    }

    @Operation(summary = "Listar", description = "Método que lista todas as turmas de um determinado Educador!", tags = "Turma")
    @GetMapping("/{idProfessor}")
    public ResponseEntity<List<TurmaListagemDTO>> listarPorProfessor(@PathVariable UUID idProfessor) {
        return status(200).body(this.turmaService.listarTodasTurmasPorProfessor(idProfessor));
    }

    @Operation(summary = "Editar", description = "Método que edita uma turma!", tags = "Turma")
    @PutMapping("/{id}")
    public ResponseEntity<TurmaListagemDTO> editar(@RequestBody @Valid TurmaAtualizaDTO turmaAtualizada, @PathVariable UUID id) {
        Turma turmaCadastrada = turmaService.atualizar(turmaAtualizada, id);
        return status(200).body(TurmaMapper.toDto(turmaCadastrada));
    }

    @Operation(summary = "Desativar", description = "Método que seta o status da turma como desativado!", tags = "Turma")
    @PostMapping("/desativar/{idProfessor}/{id}")
    public ResponseEntity<TurmaListagemDTO> desativar(@PathVariable UUID id, @PathVariable UUID idProfessor) {
        return status(200).body(TurmaMapper.toDto(this.turmaService.desativar(id, idProfessor)));
    }

    @Operation(summary = "Listar por ID", description = "Método que retorna a turma buscada por ID!", tags = "Turma")
    @GetMapping("/buscaPorId/{idProfessor}/{id}")
    public ResponseEntity<TurmaListagemDTO> buscaPorId(@PathVariable UUID id, @PathVariable UUID idProfessor) {
        return status(200).body(this.turmaService.listarPorIdAndProfessor(id, idProfessor));
    }

    @Operation(summary = "Gerar Arquivo", description = "Método que gera o arquivo CSV!", tags = "Escola")
    @GetMapping("/gerarCSV/{idProfessor}/{idTurma}")
    public ResponseEntity<Resource> gerarEbaixarCSV(@PathVariable UUID idTurma, @PathVariable UUID idProfessor) {
        TurmaListagemDTO turmaCSV = turmaService.listarPorIdAndProfessor(idTurma, idProfessor);

        if (turmaCSV == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        FileWriter file = null;
        Formatter saida = null;
        Boolean badWay = false;

        String fileName = "alunos.csv";

        try {
            file = new FileWriter(fileName);
            saida = new Formatter(file);
        } catch (Exception e) {
            System.out.println("Erro ao criar arquivo: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }

        try {
            saida.format("%s;%s;%s;%s;%s;%s;%s\n",
                    "IDTURMA",
                    "NOMETURMA",
                    "DESCRICAOESCOLARIDADE",
                    "IDEDUCADOR",
                    "IDALUNO",
                    "NOMEALUNO",
                    "APELIDOALUNO");

            for (TurmaListagemDTO.AlunoListagemDTO aluno : turmaCSV.getAlunos()) {
                saida.format("%s;%s;%s;%s;%s;%s;%s\n",
                        turmaCSV.getIdTurma(),
                        turmaCSV.getNome().toUpperCase(),
                        turmaCSV.getFkEscolaridade().getDescricao().toUpperCase(),
                        turmaCSV.getFkEducador().getIdEducador(),
                        aluno.getIdAluno(),
                        aluno.getNome().toUpperCase() + " " + aluno.getSobrenome().toUpperCase(),
                        aluno.getApelido().toUpperCase());
            }

        } catch (Exception e) {
            System.out.println("Erro ao gravar no arquivo: " + e.getMessage());
            e.printStackTrace();
            badWay = true;
        } finally {
            saida.close();
            try {
                file.close();
            } catch (IOException e) {
                System.out.println("Erro ao fechar arquivo: " + e.getMessage());
                e.printStackTrace();
                badWay = true;
            }
            if (badWay) {
                System.exit(1);
            }
        }

        Path path = Paths.get(fileName);
        org.springframework.core.io.Resource resource = new FileSystemResource(path);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName);
        headers.add(HttpHeaders.CONTENT_TYPE, "text/csv");

        return ResponseEntity.ok().headers(headers).body(resource);
    }

    @GetMapping("/presencas/{idProfessor}/{idTurma}")
    public ResponseEntity<PresencaDTO[][]> getPresencas(@PathVariable UUID idProfessor, @PathVariable UUID idTurma){
        PresencaDTO[][] presencas = turmaService.getPresencas(idProfessor, idTurma);
        return ResponseEntity.ok(presencas);
    }
}

