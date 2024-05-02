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
import school.sptech.apicodando.configuration.security.aluno.jwt.GerenciadorTokenJwt;
import school.sptech.apicodando.domain.aluno.Aluno;
import school.sptech.apicodando.mapper.AlunoMapper;
import school.sptech.apicodando.domain.aluno.repository.AlunoRepository;
import school.sptech.apicodando.service.alunoService.dto.AlunoCadastroDTO;
import school.sptech.apicodando.service.alunoService.dto.dtoAuthAluno.AlunoLoginDTO;
import school.sptech.apicodando.service.alunoService.dto.dtoAuthAluno.AlunoTokenDto;

import java.util.List;
import java.util.Optional;

@Service
public class AlunoService {


    @Autowired
    private AlunoRepository alunoRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private GerenciadorTokenJwt gerenciadorTokenJwt;

    @Autowired
    private AuthenticationManager authenticationManager;

    public void criar(AlunoCadastroDTO alunoCadastroDTO) {
        final Aluno novoAluno = AlunoMapper.toEntity(alunoCadastroDTO);
        String senhaCriptografada = passwordEncoder.encode(novoAluno.getSenha());
        novoAluno.setSenha(senhaCriptografada);

        if (existePorApelido(alunoCadastroDTO.getApelido())){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Aluno já cadastrado.");
        }

        this.alunoRepository.save(novoAluno);
    }

    public void excluir(int id) {

        if (!existePorId(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        alunoRepository.deleteById(id);
    }

    public void atualizar(Aluno alunoAtualizado, int id) {
        if (!alunoRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Não encontrado");
        }
        alunoAtualizado.setIdAluno(id);
        alunoRepository.save(alunoAtualizado);
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
        return alunoRepository.findById(id);
    }

    public List<Aluno> listarTodos() {
        return alunoRepository.findAll();
    }



}
