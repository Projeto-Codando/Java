package school.sptech.apicodando.configuration.security.aluno;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import school.sptech.apicodando.service.autenticacao.AutenticacaoAlunoService;
import school.sptech.apicodando.service.autenticacao.AutenticacaoEducadorService;

public class AutenticacaoProviderEducador implements AuthenticationProvider {
//    @Autowired
    private final AutenticacaoEducadorService autenticacaoEducadorService;
//    @Autowired
    private final PasswordEncoder passwordEncoder;

    public AutenticacaoProviderEducador(AutenticacaoEducadorService autenticacaoEducadorService, PasswordEncoder passwordEncoder) {
        this.autenticacaoEducadorService = autenticacaoEducadorService;
        this.passwordEncoder = passwordEncoder;
    }





    @Override
    public Authentication authenticate(final Authentication authentication) throws AuthenticationException {

        final String username = authentication.getName();
        final String password = authentication.getCredentials().toString();

        UserDetails userDetails = this.autenticacaoEducadorService.loadUserByUsername(username);

        if (this.passwordEncoder.matches(password, userDetails.getPassword())){
            return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        } else throw new BadCredentialsException("Usuário ou senha inválidos");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
