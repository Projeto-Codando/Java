package school.sptech.apicodando.service.alunoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import school.sptech.apicodando.entity.Aluno;
import school.sptech.apicodando.entity.Educador;
import school.sptech.apicodando.mapper.AlunoMapper;
import school.sptech.apicodando.mapper.EducadorMapper;
import school.sptech.apicodando.repository.AlunoRepository;
import school.sptech.apicodando.service.alunoService.dto.AlunoCadastroDTO;
import school.sptech.apicodando.service.educadorService.dto.EducadorCadastroDTO;

import java.util.List;
import java.util.Optional;

@Service
public class AlunoService {


    @Autowired
    private AlunoRepository alunoRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

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
        alunoAtualizado.setIdAluno(id);
        alunoRepository.save(alunoAtualizado);
    }

    public Optional<Aluno> listarUmPorId(int id){
        return alunoRepository.findById(id);
    }

    public List<Aluno> listarTodos(){
        return alunoRepository.findAll();
    }
}
