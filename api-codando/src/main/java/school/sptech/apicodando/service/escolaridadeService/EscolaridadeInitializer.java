package school.sptech.apicodando.service.escolaridadeService;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import school.sptech.apicodando.api.domain.escolaridade.Escolaridade;
import school.sptech.apicodando.api.domain.escolaridade.repository.EscolaridadeRepository;

import java.util.List;

@Service
public class EscolaridadeInitializer {

    private final EscolaridadeRepository escolaridadeRepository;

    @Autowired
    public EscolaridadeInitializer(EscolaridadeRepository escolaridadeRepository) {
        this.escolaridadeRepository = escolaridadeRepository;
    }

    @PostConstruct
    public void inserirDadosIniciaisSeNecessario() {
        if (escolaridadeRepository.count() == 0) {
            List<Escolaridade> dadosIniciais = List.of(
                new Escolaridade("6º Ano"),
                new Escolaridade("7º Ano"),
                new Escolaridade("8º Ano"),
                new Escolaridade("9º Ano")
            );
            escolaridadeRepository.saveAll(dadosIniciais);
            System.out.println("Dados iniciais de escolaridade inseridos.");
        } else {
            System.out.println("Dados de escolaridade já existem. Nenhuma inserção foi feita.");
        }
    }
}
