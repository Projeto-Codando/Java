package school.sptech.apicodando.service.alunoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import school.sptech.apicodando.configuration.security.jwt.GerenciadorTokenJwt;
import school.sptech.apicodando.entity.Aluno;
import school.sptech.apicodando.entity.Educador;
import school.sptech.apicodando.mapper.AlunoMapper;
import school.sptech.apicodando.mapper.EducadorMapper;
import school.sptech.apicodando.repository.AlunoRepository;
import school.sptech.apicodando.service.alunoService.dto.AlunoCadastroDTO;
import school.sptech.apicodando.service.autenticacao.dto.AlunoLoginDTO;
import school.sptech.apicodando.service.autenticacao.dto.AlunoTokenDto;
import school.sptech.apicodando.service.educadorService.dto.EducadorCadastroDTO;

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
        this.alunoRepository.save(novoAluno);
    }

    public void excluir(int id) {
        alunoRepository.deleteById(id);
    }

    public void atualizar(Aluno alunoAtualizado, int id){
        if (!alunoRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Não encontrado");
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

    public boolean existePorId(int id) {
        return alunoRepository.existsById(id);
    }

    public Optional<Aluno> listarUmPorId(int id){
        return alunoRepository.findById(id);
    }

    public List<Aluno> listarTodos(){
        return alunoRepository.findAll();
    }
}
