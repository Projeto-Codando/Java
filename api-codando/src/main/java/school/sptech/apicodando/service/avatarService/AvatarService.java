package school.sptech.apicodando.service.avatarService;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import school.sptech.apicodando.api.domain.aluno.repository.AlunoRepository;
import school.sptech.apicodando.api.domain.avatar.Avatar;
import school.sptech.apicodando.api.domain.avatar.repository.AvatarRepository;
import school.sptech.apicodando.service.alunoService.AlunoService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AvatarService {
    private final AvatarRepository repository;
    private final AlunoService alunoService;

    @PostConstruct
    public void inserirDadosIniciaisSeNecessario() {
        if (repository.count() == 0) {
            List<Avatar> dadosIniciais = criarAvatares();
            repository.saveAll(dadosIniciais);
            System.out.println("Dados iniciais de avatar inseridos.");
        } else {
            System.out.println("Dados de avatar já existem. Nenhuma inserção foi feita.");
        }
    }

    public List<Avatar> listar() {
        return repository.findAll();
    }

    public Avatar buscarPorId(int id){
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Avatar não encontrado."));
    }

    public Avatar atualizarAvatar(int idAluno, int idAvatar) {
        Avatar avatar = buscarPorId(idAvatar);
        alunoService.listarUmPorId(idAluno).ifPresent(aluno -> {

            if(aluno.getAvatares().contains(avatar)){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Avatar já adquirido.");
            }

            if(aluno.getMoedas() < avatar.getPreco()){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Moedas insuficientes.");
            }

            aluno.setMoedas(aluno.getMoedas() - avatar.getPreco());
            aluno.getAvatares().add(avatar);
            alunoService.atualizar(aluno, idAluno);
        });
        return avatar;
    }

    public Avatar atualizarAvatarEscolhido(int idAluno, int idAvatar) {
        Avatar avatar = buscarPorId(idAvatar);
        alunoService.listarUmPorId(idAluno).ifPresent(aluno -> {
            if(!aluno.getAvatares().contains(avatar)){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Avatar não adquirido.");
            }
            aluno.setIdAvatar(avatar.getId());
            alunoService.atualizar(aluno, idAluno);
        });
        return avatar;
    }

    private List<Avatar> criarAvatares() {
        return List.of(
                new Avatar("Chimpaze", 0, "https://qxztjedmqxjnfloewgbv.supabase.co/storage/v1/object/public/macaco/chimpaZe_default.png"),
                new Avatar("Chimpaze Bombeiro", 100, "https://qxztjedmqxjnfloewgbv.supabase.co/storage/v1/object/public/macaco/chimpaZe_bombeiro.png"),
                new Avatar("Chimpaze Pirata", 100, "https://qxztjedmqxjnfloewgbv.supabase.co/storage/v1/object/public/macaco/chimpaZe_pirata.png"),
                new Avatar("Chimpaze Policial", 100, "https://qxztjedmqxjnfloewgbv.supabase.co/storage/v1/object/public/macaco/chimpaZe_policial.png"),
                new Avatar("Chimpaze Touca", 100, "https://qxztjedmqxjnfloewgbv.supabase.co/storage/v1/object/public/macaco/chimpaZe_touca.png"),
                new Avatar("Preguiça", 350, "https://qxztjedmqxjnfloewgbv.supabase.co/storage/v1/object/public/macaco/preguica.png"),
                new Avatar("Preguiça Leal", 350, "https://qxztjedmqxjnfloewgbv.supabase.co/storage/v1/object/public/macaco/preguica_leal.png"),
                new Avatar("Preguiça Ney", 350, "https://qxztjedmqxjnfloewgbv.supabase.co/storage/v1/object/public/macaco/preguica_ney.png"),
                new Avatar("Preguiça Popo", 350, "https://qxztjedmqxjnfloewgbv.supabase.co/storage/v1/object/public/macaco/preguica_popo.png"),
                new Avatar("Preguiça Senna", 350, "https://qxztjedmqxjnfloewgbv.supabase.co/storage/v1/object/public/macaco/preguica_senna.png"),
                new Avatar("Cachorro", 100, "https://qxztjedmqxjnfloewgbv.supabase.co/storage/v1/object/public/macaco/cachorro_default.png"),
                new Avatar("Cachorro Magico", 100, "https://qxztjedmqxjnfloewgbv.supabase.co/storage/v1/object/public/macaco/cachorro_cartola.png"),
                new Avatar("Cachorro da Roça", 100, "https://qxztjedmqxjnfloewgbv.supabase.co/storage/v1/object/public/macaco/cachorro_chapeu.png"),
                new Avatar("Cachorra FruFru", 100, "https://qxztjedmqxjnfloewgbv.supabase.co/storage/v1/object/public/macaco/cachorro_laco.png"),
                new Avatar("Cachorro Touca", 100, "https://qxztjedmqxjnfloewgbv.supabase.co/storage/v1/object/public/macaco/cachorro_touca.png"),
                new Avatar("Gato", 150, "https://qxztjedmqxjnfloewgbv.supabase.co/storage/v1/object/public/macaco/gato_default.png"),
                new Avatar("Gato Aeromoça", 150, "https://qxztjedmqxjnfloewgbv.supabase.co/storage/v1/object/public/macaco/gato_aeromoco.png"),
                new Avatar("Gato Bruxa", 150, "https://qxztjedmqxjnfloewgbv.supabase.co/storage/v1/object/public/macaco/gato_bruxa.png"),
                new Avatar("Gato Marinheiro", 150, "https://qxztjedmqxjnfloewgbv.supabase.co/storage/v1/object/public/macaco/gato_marinheiro.png"),
                new Avatar("Gato Medico", 150, "https://qxztjedmqxjnfloewgbv.supabase.co/storage/v1/object/public/macaco/gato_medico.png"),
                new Avatar("Urubu", 200, "https://qxztjedmqxjnfloewgbv.supabase.co/storage/v1/object/public/macaco/urubu_default.png"),
                new Avatar("Urubu Potter", 200, "https://qxztjedmqxjnfloewgbv.supabase.co/storage/v1/object/public/macaco/urubu_frio.png"),
                new Avatar("Urubu Judoca", 200, "https://qxztjedmqxjnfloewgbv.supabase.co/storage/v1/object/public/macaco/urubu_judoca.png"),
                new Avatar("Urubu Praiano", 200, "https://qxztjedmqxjnfloewgbv.supabase.co/storage/v1/object/public/macaco/urubu_oculos.png"),
                new Avatar("Urubu Vilão", 200, "https://qxztjedmqxjnfloewgbv.supabase.co/storage/v1/object/public/macaco/urubu_vilao.png"),
                new Avatar("Zebra", 100, "https://qxztjedmqxjnfloewgbv.supabase.co/storage/v1/object/public/macaco/zebra_default.png"),
                new Avatar("Zebra Cartola", 100, "https://qxztjedmqxjnfloewgbv.supabase.co/storage/v1/object/public/macaco/zebra_cartola.png"),
                new Avatar("Zebra Vaqueira", 100, "https://qxztjedmqxjnfloewgbv.supabase.co/storage/v1/object/public/macaco/zebra_cowboy.png"),
                new Avatar("Zebra Mexicana", 100, "https://qxztjedmqxjnfloewgbv.supabase.co/storage/v1/object/public/macaco/zebra_mexicana.png"),
                new Avatar("Zebra Rei", 100, "https://qxztjedmqxjnfloewgbv.supabase.co/storage/v1/object/public/macaco/zebra_rei.png")
        );
    }

}
