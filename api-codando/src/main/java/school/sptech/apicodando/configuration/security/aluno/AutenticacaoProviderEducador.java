package school.sptech.apicodando.configuration.security.aluno;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import school.sptech.apicodando.service.autenticacao.AutenticacaoAlunoService;

public class AutenticacaoProvider implements AuthenticationProvider {
//    @Autowired
    private final AutenticacaoAlunoService alunoAutentitacacaoService;
//    @Autowired
    private final PasswordEncoder passwordEncoder;

    public AutenticacaoProvider(AutenticacaoAlunoService alunoAutentitacacaoService, PasswordEncoder passwordEncoder) {
        this.alunoAutentitacacaoService = alunoAutentitacacaoService;
        this.passwordEncoder = passwordEncoder;
    }





    @Override
    public Authentication authenticate(final Authentication authentication) throws AuthenticationException {

        final String username = authentication.getName();
        final String password = authentication.getCredentials().toString();

        UserDetails userDetails = this.alunoAutentitacacaoService.loadUserByUsername(username);

        if (this.passwordEncoder.matches(password, userDetails.getPassword())){
            return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        } else throw new BadCredentialsException("Usuário ou senha inválidos");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
