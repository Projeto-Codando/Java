package school.sptech.apicodando.service.autenticacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import school.sptech.apicodando.domain.educador.Educador;
import school.sptech.apicodando.domain.educador.repository.EducadorRepository;
import school.sptech.apicodando.service.alunoService.dto.dtoAluno.AlunoDetalhesDto;
import school.sptech.apicodando.service.educadorService.dto.dtoEducador.EducadorDetalhesDto;

import java.util.Optional;
@Service
public class AutentificacaoEducadorService implements UserDetailsService {


    @Autowired
    private EducadorRepository educadorRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Educador> educadorOptional = educadorRepository.findByEmail(email);
        if (educadorOptional.isEmpty()) {
            throw new UsernameNotFoundException(String.format("Aluno %s não encontrado", email));
        }
        return new EducadorDetalhesDto(educadorOptional.get());
    }

}
