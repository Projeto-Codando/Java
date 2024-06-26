package school.sptech.apicodando.service.autenticacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import school.sptech.apicodando.api.domain.aluno.Aluno;
import school.sptech.apicodando.api.domain.aluno.repository.AlunoRepository;
import school.sptech.apicodando.api.domain.educador.Educador;
import school.sptech.apicodando.api.domain.educador.repository.EducadorRepository;
import school.sptech.apicodando.service.alunoService.dto.dtoAuthAluno.AlunoDetalhesDto;
import school.sptech.apicodando.service.educadorService.dto.dtoEducador.EducadorDetalhesDto;

import java.util.Optional;
@Service
    public class AutenticacaoService implements UserDetailsService {

    @Autowired
    private AlunoRepository alunoRepository;
    @Autowired
    private EducadorRepository educadorRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Aluno> alunoOpt = alunoRepository.findByApelido(username);
        Optional<Educador> educadorOpt = educadorRepository.findByEmail(username);

        if (alunoOpt.isEmpty() && educadorOpt.isEmpty()){
            throw new UsernameNotFoundException(String.format("Usuário %s não encontrado, verifique se o usuário dono do Token está válido.", username));
        }

        if (alunoOpt.isPresent()){
            return new AlunoDetalhesDto(alunoOpt.get());
        } else {
            return new EducadorDetalhesDto(educadorOpt.get());
        }
    }


}
