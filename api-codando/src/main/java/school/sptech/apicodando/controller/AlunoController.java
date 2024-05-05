package school.sptech.apicodando.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.apicodando.service.alunoService.AlunoService;
import school.sptech.apicodando.service.alunoService.dto.AlunoCadastroDTO;
import school.sptech.apicodando.service.alunoService.dto.AlunoListagemDTO;
import school.sptech.apicodando.mapper.AlunoMapper;
import school.sptech.apicodando.domain.aluno.Aluno;
import school.sptech.apicodando.domain.aluno.repository.AlunoRepository;
import jakarta.validation.Valid;
import school.sptech.apicodando.service.autenticacao.dto.AlunoLoginDTO;
import school.sptech.apicodando.service.autenticacao.dto.AlunoTokenDto;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Formatter;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.ResponseEntity.*;
import static org.springframework.http.ResponseEntity.notFound;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    private AlunoRepository alunoRepository;
    @Autowired
    private AlunoService alunoService;

//    @Autowired
//    private Array array;

    @PostMapping
//    @SecurityRequirement(name = "Bearer")

    public ResponseEntity<Void> criar(@RequestBody @Valid AlunoCadastroDTO novoAluno) {
        this.alunoService.criar(novoAluno);
        return status(201).build();
    }

    @PostMapping("/login")
    public ResponseEntity<AlunoTokenDto> login(@RequestBody AlunoLoginDTO usuarioLoginDto) {
        AlunoTokenDto usuarioTokenDto = this.alunoService.autenticar(usuarioLoginDto);

        return ResponseEntity.status(200).body(usuarioTokenDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlunoListagemDTO> buscaPorId(@PathVariable int id) {
        Optional<Aluno> alunoOpt = alunoService.listarUmPorId(id);
        if (alunoOpt.isEmpty()) {
            return status(404).build();
        }
        AlunoListagemDTO dto = AlunoMapper.toDto(alunoOpt.get());
        return status(200).body(dto);
    }

    @GetMapping
    public ResponseEntity<List<AlunoListagemDTO>> listar() {
        List<Aluno> alunos = alunoService.listarTodos();

        if (alunos.isEmpty()) {
            return status(204).build();
        }
        List<AlunoListagemDTO> listaAuxiliar = AlunoMapper.toDto(alunos);

        return status(200).body(listaAuxiliar);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir (@PathVariable @Valid int id){
        if (alunoRepository.existsById(id)){
            alunoService.excluir(id);
            return status(200).build();
        } else {
            return status(404).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizar(@PathVariable("id") @Valid int id,
                                    @RequestBody @Valid Aluno alunoAlterado) {
        alunoService.atualizar(alunoAlterado, id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/pesquisaEscolas")
    public ResponseEntity<Object> getJsonFile() {
        try {
            Resource resource = new ClassPathResource("resultado.json");
            ObjectMapper mapper = new ObjectMapper();
            return ResponseEntity.ok(mapper.readValue(resource.getInputStream(), Object.class));
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/download/{filename}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String filename) {
        Path path = Paths.get("caminho/para/o/arquivo", filename + ".csv");
        Resource resource = new FileSystemResource(path);

        // Criar cabeçalho para o download
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename + ".csv");
        headers.add(HttpHeaders.CONTENT_TYPE, "text/csv");

        // Retornar o arquivo para download
        return ResponseEntity.ok()
                .headers(headers)
                .body(resource);
    }

    @GetMapping("/gerarCSV")
    public ResponseEntity<Resource> gerarEbaixarCSV() {
        List<Aluno> alunoss = alunoService.listarTodos();

        List<AlunoListagemDTO> alunos = AlunoMapper.toDto(alunoss);

        if(alunos.isEmpty()){
            System.out.println("Lista vazia");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
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
            for (AlunoListagemDTO aluno : alunos) {
                saida.format("%d;%s;%s\n", aluno.getIdAluno(), aluno.getNome(), aluno.getApelido());
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

        // Carregar o arquivo do sistema de arquivos
        Path path = Paths.get(fileName);
        Resource resource = new FileSystemResource(path);

        // Criar cabeçalho para o download
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName);
        headers.add(HttpHeaders.CONTENT_TYPE, "text/csv");

        // Retornar o arquivo para download
        return ResponseEntity.ok()
                .headers(headers)
                .body(resource);
    }


}
