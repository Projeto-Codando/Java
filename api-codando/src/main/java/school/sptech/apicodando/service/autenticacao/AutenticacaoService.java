package school.sptech.apicodando.service.autenticacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import school.sptech.apicodando.entity.Aluno;
import school.sptech.apicodando.repository.AlunoRepository;
import school.sptech.apicodando.service.autenticacao.dto.AlunoDetalhesDto;

import java.util.Optional;

public class AutenticacaoService implements UserDetailsService {

    @Autowired
    private AlunoRepository alunoRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Aluno> alunoOpt = alunoRepository.findByApelido(username);
        if (alunoOpt.isEmpty()){
            throw new UsernameNotFoundException(String.format("Aluno %s não encontrado", username));
        }
        return new AlunoDetalhesDto(alunoOpt.get());
    }
}
