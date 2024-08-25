package school.sptech.apicodando.service.alunoService;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import school.sptech.apicodando.api.domain.avatar.Avatar;
import school.sptech.apicodando.api.domain.avatar.repository.AvatarRepository;
import school.sptech.apicodando.api.domain.escolaridade.Escolaridade;
import school.sptech.apicodando.api.domain.escolaridade.repository.EscolaridadeRepository;
import school.sptech.apicodando.api.domain.turma.Turma;
import school.sptech.apicodando.api.utils.constantes.Constantes;
import school.sptech.apicodando.configuration.security.aluno.jwt.GerenciadorTokenJwt;
import school.sptech.apicodando.api.domain.aluno.Aluno;
import school.sptech.apicodando.api.mapper.AlunoMapper;
import school.sptech.apicodando.api.domain.aluno.repository.AlunoRepository;
import school.sptech.apicodando.service.alunoService.dto.AlunoAtualizadoDTO;
import school.sptech.apicodando.service.alunoService.dto.AlunoCadastroDTO;
import school.sptech.apicodando.service.alunoService.dto.dtoAuthAluno.AlunoLoginDTO;
import school.sptech.apicodando.service.alunoService.dto.dtoAuthAluno.AlunoTokenDto;
import school.sptech.apicodando.service.turmaService.TurmaService;

import java.util.*;

@Service
@RequiredArgsConstructor
public class AlunoService {
    //TODO - Trocar os @Autowired por @RequiredArgsConstructor
    //TODO - Evitar de usar Repository's diretamente no Service, criar um Service para cada Repository.

    private final AlunoRepository repository;
    private final EscolaridadeRepository escolaridadeRepository;
    private final TurmaService turmaService;
    private final PasswordEncoder passwordEncoder;
    private final GerenciadorTokenJwt gerenciadorTokenJwt;
    private final AuthenticationManager authenticationManager;

    public Aluno criar(AlunoCadastroDTO alunoCadastroDTO) {
        if(alunoCadastroDTO == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Dados inválidos.");
        }

        Turma turmaEncontrada = turmaService.findBySenha(alunoCadastroDTO.getSenhaTurma());

        if (turmaEncontrada == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Turma não encontrada.");
        }

        // TODO - Criar a service do EscolaridadeRepository e chamar o método findById.
        Escolaridade escolaridadeEncontrada = escolaridadeRepository.findById(turmaEncontrada.getEscolaridade().getIdEscolaridade()).get();

        if(escolaridadeEncontrada == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Escolaridade não encontrada.");
        }

        Aluno novoAluno = AlunoMapper.toEntity(alunoCadastroDTO);
        novoAluno.setEscolaridade(escolaridadeEncontrada);
        novoAluno.setTurma(turmaEncontrada);
        String senhaCriptografada = passwordEncoder.encode(novoAluno.getSenha());
        novoAluno.setSenha(senhaCriptografada);
        novoAluno.addAvatar(setBasicAvatar());
        novoAluno.setMoedas(Constantes.MOEDAS_INICIAIS);
        novoAluno.setIdAvatar(Constantes.ID_AVATAR_INICIAL);

        if (existePorApelido(alunoCadastroDTO.getApelido())){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Aluno já cadastrado.");
        }

        return repository.save(novoAluno);
    }
    public void excluir(int id) {

        if (!existePorId(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        repository.deleteById(id);
    }

    public void atualizar(AlunoAtualizadoDTO alunoAtualizado, int id) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Não encontrado aluno com o id informado.");
        }

        Aluno alunoAtual = repository.findById(id).get();

        alunoAtual.setNome(alunoAtualizado.getNome());
        alunoAtual.setSobrenome(alunoAtualizado.getSobrenome());
        alunoAtual.setApelido(alunoAtualizado.getApelido());
        alunoAtual.setSenha(alunoAtualizado.getSenha());

        repository.save(alunoAtual);
    }

    public void atualizar(Aluno aluno, int id) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Não encontrado aluno com o id informado.");
        }

        aluno.setIdAluno(id);
        repository.save(aluno);
    }

    public AlunoTokenDto autenticar(AlunoLoginDTO usuarioLoginDto) {

        final UsernamePasswordAuthenticationToken credentials = new UsernamePasswordAuthenticationToken(
                usuarioLoginDto.getApelido(), usuarioLoginDto.getSenha());

        final Authentication authentication = this.authenticationManager.authenticate(credentials);

        Aluno usuarioAutenticado =
                repository.findByApelido(usuarioLoginDto.getApelido())
                        .orElseThrow(
                                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Email do usuário não cadastrado", null)
                        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        final String token = gerenciadorTokenJwt.generateToken(authentication);

        return AlunoMapper.of(usuarioAutenticado, token);
    }

    private Avatar setBasicAvatar() {
        Avatar avatar = new Avatar();
        avatar.setId(Constantes.ID_AVATAR_INICIAL);
        avatar.setDescricao(Constantes.DESCRICAO_AVATAR_INICIAL);
        avatar.setPreco(Constantes.VALOR_AVATAR_INICIAL);
        avatar.setImagemURL(Constantes.IMAGEM_AVATAR_INICIAL);
        return avatar;
    }

    public boolean existePorId(int id) {
        return repository.existsById(id);
    }

    public Boolean existePorApelido(String apelido) {
        if (repository.findByApelido(apelido).isEmpty()) {
            return false;
        }
        return true;
    }

    public Optional<Aluno> listarUmPorId(int id) {
        if (!existePorId(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Aluno não encontrado");
        }
        return repository.findById(id);
    }

    public List<Aluno> listarTodos() {
        if (repository.findAll().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Não há alunos cadastrados.");
        }
        return repository.findAll();
    }

    public void excluirLista(List<Integer> ids) {
        List<Integer> idsDuplicados = verificarIdsDuplicados(ids);
        List<Integer> idsInvalidos = verificarIdsInvalidos(ids);

        if (!idsInvalidos.isEmpty() || !idsDuplicados.isEmpty()) {
            StringBuilder mensagemErro = new StringBuilder("Erro ao excluir IDs: ");
            if (!idsInvalidos.isEmpty()) {
                mensagemErro.append("IDs não encontrados: ").append(idsInvalidos).append(". ");
            }
            if (!idsDuplicados.isEmpty()) {
                mensagemErro.append("IDs duplicados: ").append(idsDuplicados).append(". ");
            }
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, mensagemErro.toString());
        }

        for (Integer id : ids) {
            repository.deleteById(id);
        }
    }

    private List<Integer> verificarIdsDuplicados(List<Integer> ids) {
        List<Integer> idsDuplicados = new ArrayList<>();
        List<Integer> idsVerificados = new ArrayList<>();

        for (Integer id : ids) {
            if (idsVerificados.contains(id)) {
                idsDuplicados.add(id);
            } else {
                idsVerificados.add(id);
            }
        }

        return idsDuplicados;
    }


    private List<Integer> verificarIdsInvalidos(List<Integer> ids) {
        List<Integer> idsInvalidos = new ArrayList<>();

        for (Integer id : ids) {
            if (!existePorId(id)) {
                idsInvalidos.add(id);
            }
        }

        return idsInvalidos;
    }
}
