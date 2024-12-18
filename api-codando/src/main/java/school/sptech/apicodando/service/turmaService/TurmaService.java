package school.sptech.apicodando.service.turmaService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import school.sptech.apicodando.api.domain.educador.Educador;
import school.sptech.apicodando.api.domain.educador.repository.EducadorRepository;
import school.sptech.apicodando.api.domain.escolaridade.Escolaridade;
import school.sptech.apicodando.api.domain.escolaridade.repository.EscolaridadeRepository;
import school.sptech.apicodando.api.domain.modulo.Modulo;
import school.sptech.apicodando.api.domain.modulo.repository.ModuloRepository;
import school.sptech.apicodando.api.domain.turma.Turma;
import school.sptech.apicodando.api.domain.turma.repository.TurmaRepository;
import school.sptech.apicodando.api.mapper.TurmaMapper;
import school.sptech.apicodando.configuration.DadosIniciais;
import school.sptech.apicodando.service.aulaService.AulaService;
import school.sptech.apicodando.service.perguntaService.PerguntaService;
import school.sptech.apicodando.service.respostaService.RespostaService;
import school.sptech.apicodando.service.turmaService.dto.TurmaAtualizaDTO;
import school.sptech.apicodando.service.turmaService.dto.TurmaCadastroDTO;
import school.sptech.apicodando.service.turmaService.dto.TurmaListagemDTO;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TurmaService {

    // TODO Trocar as repositories por services

    private final TurmaRepository turmaRepository;
    private final EscolaridadeRepository escolaridadeRepository;
    private final EducadorRepository educadorRepository;
    private final ModuloRepository moduloRepository;
    private final AulaService aulaService;
    private final PerguntaService perguntaService;
    private final RespostaService respostaService;
//    private final DadosIniciais dadosIniciais;

    public Turma criar(TurmaCadastroDTO turmaCadastro) {
        Escolaridade escolaridade = escolaridadeRepository.findById(turmaCadastro.getFkEscolaridade())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Escolaridade não encontrada."));

        Educador educador = educadorRepository.findById(turmaCadastro.getFkEducador())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Educador não encontrado."));

        Modulo modulo = moduloRepository.findById(turmaCadastro.getFkModulo())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Modulo não encontrado."));

        final Turma novaTurma = TurmaMapper.toEntity(turmaCadastro, escolaridade, educador, modulo);

        if (existeTurmaByCodigo(turmaCadastro.getSenha())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Turma já criada.");
        }

        turmaRepository.save(novaTurma);

        // Inserindo dados iniciais
        var responseAula = aulaService.inserirDadosIniciaisSeNecessario(novaTurma.getIdTurma());
        var responsePergunta = perguntaService.inserirDadosIniciaisSeNecessario(responseAula);
        respostaService.inserirDadosIniciaisSeNecessario(responsePergunta);
        return novaTurma;
    }

    public Turma desativar(int id, int idProfessor) {
        Turma turma = buscarPorIdTurmaAndIdProfessor(id, idProfessor);
        if (turma == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Turma não encontrada.");
        } else {
            turma.setStatusTurma(false);
            return this.turmaRepository.save(turma);
        }
    }

    public Turma buscarPorId(int id) {
        Optional<Turma> turma = this.turmaRepository.findById(id);
        if (turma.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Turma não encontrada.");
        }
        return turma.get();
    }

    public Turma atualizar(TurmaAtualizaDTO turmaAtualizada, int id) {
        Turma turma = buscarPorIdTurmaAndIdProfessor(id, turmaAtualizada.getFkEducador().getIdEducador());
        if (turma == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Turma não encontrada.");
        } else {

            turma.setNome(turmaAtualizada.getNome());
            turma.setSenha(turmaAtualizada.getSenha());
            turma.setEscolaridade(turmaAtualizada.getFkEscolaridade());
            turma.setEducador(turmaAtualizada.getFkEducador());

            return turmaRepository.save(turma);
        }
    }

    public List<TurmaListagemDTO> listarTodasTurmasPorProfessor(int idProfessor) {
        List<Turma> turmas = this.listarPorProfessor(idProfessor);

        final List<TurmaListagemDTO> turmasListagem = TurmaMapper.toDto(turmas);

        if (turmas.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return turmasListagem;
    }

    public TurmaListagemDTO listarPorIdAndProfessor(int idTurma, int idProfessor) {
        Optional<Turma> turma = this.turmaRepository.findByIdTurmaAndEducadorIdEducador(idTurma, idProfessor);

        if (turma.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Turma não encontrada.");
        }

        return TurmaMapper.toDto(turma.get());
    }

    public List<Turma> listarPorProfessor(int idProfessor) {
        return this.turmaRepository.findByEducadorIdEducador(idProfessor);
    }

    // Metodo para verificar se a Turma já existe.
    public boolean existeTurmaByCodigo(String codigo) {
        return this.turmaRepository.findBySenha(codigo).isPresent();
    }

    public boolean existeTurma(int idTurma) {
        return this.turmaRepository.existsById(idTurma);
    }

    public Optional<Turma> buscarTurma(int idTurma) {
        return this.turmaRepository.findById(idTurma);
    }

    public Integer getIdPorSenha(String senha) {
        Optional<Turma> turma = this.turmaRepository.findBySenha(senha);
        if (turma.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Turma não encontrada.");
        }
        return turma.get().getIdTurma();
    }

    public Turma findBySenha(String senha) {
        Optional<Turma> turma = this.turmaRepository.findBySenha(senha);
        if (turma.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Turma não encontrada.");
        }
        return turma.get();
    }

    public Turma buscarPorIdTurmaAndIdProfessor(int idTurma, int idProfessor) {
        Optional<Turma> turma = this.turmaRepository.findByIdTurmaAndEducadorIdEducador(idTurma, idProfessor);
        if (turma.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Turma não encontrada.");
        }
        return turma.get();
    }


}
