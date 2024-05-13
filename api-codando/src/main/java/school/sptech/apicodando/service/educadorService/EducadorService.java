package school.sptech.apicodando.service.educadorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import school.sptech.apicodando.configuration.security.aluno.jwt.GerenciadorTokenJwt;
//import school.sptech.apicodando.configuration.security.educador.jwt.GerenciadorTokenEducadorJwt;
import school.sptech.apicodando.api.domain.educador.Educador;
import school.sptech.apicodando.api.mapper.EducadorMapper;
import school.sptech.apicodando.api.domain.educador.repository.EducadorRepository;
import school.sptech.apicodando.service.educadorService.dto.EducadorCadastroDTO;
import school.sptech.apicodando.service.educadorService.dto.EducadorLoginDTO;
import school.sptech.apicodando.service.educadorService.dto.dtoEducador.EducadorTokenDto;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.ResponseEntity.ok;

@Service
public class EducadorService {

    @Autowired
    private EducadorRepository educadorRepository;
    @Qualifier("passwordEncoder")
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private GerenciadorTokenJwt gerenciadorTokenJwt;

    @Autowired
    private AuthenticationManager authenticationManager;


    public void criar(EducadorCadastroDTO educadorCadastroDTO) {
        final Educador novoEducador = EducadorMapper.toEntity(educadorCadastroDTO);
        String senhaCriptografada = passwordEncoder.encode(novoEducador.getSenha());
        novoEducador.setSenha(senhaCriptografada);

        if (existePorEmail(educadorCadastroDTO.getEmail())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Educador já cadastrado.");
        }

        this.educadorRepository.save(novoEducador);
    }

    public void excluir(int id) {

        if (!existePorId(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        educadorRepository.deleteById(id);
    }

    public void atualizar(EducadorCadastroDTO educadorAtualizado, int id) {
        if (!educadorRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Educador não encontrado.");
        }
        Educador educador = EducadorMapper.toEntity(educadorAtualizado);
        educador.setIdEducador(id);
        educadorRepository.save(educador);
    }

    public EducadorTokenDto autenticar(EducadorLoginDTO usuarioLoginDto) {

        final UsernamePasswordAuthenticationToken credentials = new UsernamePasswordAuthenticationToken(
                usuarioLoginDto.getEmail(), usuarioLoginDto.getSenha());

        final Authentication authentication = this.authenticationManager.authenticate(credentials);

        final Educador usuarioAutenticado =
                educadorRepository.findByEmail(usuarioLoginDto.getEmail())
                        .orElseThrow(
                                () -> new ResponseStatusException(404, "Email do educador não cadastrado", null)
                        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        final String token = gerenciadorTokenJwt.generateToken(authentication);

        return EducadorMapper.of(usuarioAutenticado, token);
    }

    public Optional<Educador> listarUmPorId(int id) {
        if (!educadorRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Educador não encontrado.");
        }
        return educadorRepository.findById(id);
    }

    public List<Educador> listarTodos() {
        if (educadorRepository.findAll().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Nenhum educador cadastrado.");
        }
        return educadorRepository.findAll();
    }

    public Boolean existePorEmail(String email) {
        if (educadorRepository.findByEmail(email).isEmpty()) {
            return false;
        }
        return true;
    }

    public boolean existePorId(int id) {
        return educadorRepository.findByIdEducador(id).isPresent();
    }


}
