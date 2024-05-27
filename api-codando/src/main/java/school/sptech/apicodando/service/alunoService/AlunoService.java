package school.sptech.apicodando.service.alunoService;

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
import school.sptech.apicodando.api.domain.turma.Turma;
import school.sptech.apicodando.configuration.security.aluno.jwt.GerenciadorTokenJwt;
import school.sptech.apicodando.api.domain.aluno.Aluno;
import school.sptech.apicodando.api.mapper.AlunoMapper;
import school.sptech.apicodando.api.domain.aluno.repository.AlunoRepository;
import school.sptech.apicodando.service.alunoService.dto.AlunoAtualizadoDTO;
import school.sptech.apicodando.service.alunoService.dto.AlunoCadastroDTO;
import school.sptech.apicodando.service.alunoService.dto.dtoAuthAluno.AlunoLoginDTO;
import school.sptech.apicodando.service.alunoService.dto.dtoAuthAluno.AlunoTokenDto;
import school.sptech.apicodando.service.turmaService.TurmaService;

import java.util.List;
import java.util.Optional;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private AvatarRepository avatarRepository;

    @Autowired
    private TurmaService turmaService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private GerenciadorTokenJwt gerenciadorTokenJwt;

    @Autowired
    private AuthenticationManager authenticationManager;


    public Aluno criar(AlunoCadastroDTO alunoCadastroDTO) {
        if(alunoCadastroDTO == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Dados inválidos.");
        }

        Turma turmaEncontrada = turmaService.findBySenha(alunoCadastroDTO.getSenhaTurma());

        if (turmaEncontrada == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Turma não encontrada.");
        }

        Aluno novoAluno = AlunoMapper.toEntity(alunoCadastroDTO);
        novoAluno.setTurma(turmaEncontrada);
        String senhaCriptografada = passwordEncoder.encode(novoAluno.getSenha());
        novoAluno.setSenha(senhaCriptografada);

        if (existePorApelido(alunoCadastroDTO.getApelido())){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Aluno já cadastrado.");
        }

        return alunoRepository.save(novoAluno);
    }

    public void excluir(int id) {

        if (!existePorId(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        alunoRepository.deleteById(id);
    }

    public void atualizar(AlunoAtualizadoDTO alunoAtualizado, int id) {
        if (!alunoRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Não encontrado aluno com o id informado.");
        }

        Aluno alunoAtual = alunoRepository.findById(id).get();

        alunoAtual.setNome(alunoAtualizado.getNome());
        alunoAtual.setSobrenome(alunoAtualizado.getSobrenome());
        alunoAtual.setApelido(alunoAtualizado.getApelido());
        alunoAtual.setSenha(alunoAtualizado.getSenha());

        alunoRepository.save(alunoAtual);
    }

    public void atualizar(Aluno aluno, int id) {
        if (!alunoRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Não encontrado aluno com o id informado.");
        }

        aluno.setIdAluno(id);
        alunoRepository.save(aluno);
    }

    public AlunoTokenDto autenticar(AlunoLoginDTO usuarioLoginDto) {

        final UsernamePasswordAuthenticationToken credentials = new UsernamePasswordAuthenticationToken(
                usuarioLoginDto.getApelido(), usuarioLoginDto.getSenha());

        final Authentication authentication = this.authenticationManager.authenticate(credentials);

        Aluno usuarioAutenticado =
                alunoRepository.findByApelido(usuarioLoginDto.getApelido())
                        .orElseThrow(
                                () -> new ResponseStatusException(404, "Email do usuário não cadastrado", null)
                        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        final String token = gerenciadorTokenJwt.generateToken(authentication);

        return AlunoMapper.of(usuarioAutenticado, token);
    }

    //metodos de apoio

    public boolean existePorId(int id) {
        return alunoRepository.existsById(id);
    }

    public Boolean existePorApelido(String apelido) {
        if (alunoRepository.findByApelido(apelido).isEmpty()) {
            return false;
        }
        return true;
    }

    public Optional<Aluno> listarUmPorId(int id) {
        if (!existePorId(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Aluno não encontrado");
        }
        return alunoRepository.findById(id);
    }

    public List<Aluno> listarTodos() {
        if (alunoRepository.findAll().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Não há alunos cadastrados.");
        }
        return alunoRepository.findAll();
    }



}
