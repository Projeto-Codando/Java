package school.sptech.apicodando.configuration;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import school.sptech.apicodando.service.aulaService.AulaService;
import school.sptech.apicodando.service.moduloService.ModuloService;
import school.sptech.apicodando.service.perguntaService.PerguntaService;
import school.sptech.apicodando.service.respostaService.RespostaService;
import school.sptech.apicodando.service.temaService.TemaService;

@Component
public class DadosIniciais {

    @Autowired
    private ModuloService moduloService;

    @Autowired
    private TemaService temaService;

    @Autowired
    private AulaService aulaService;

    @Autowired
    private PerguntaService perguntaService;

    @Autowired
    private RespostaService respostaService;

//    @Autowired
//    private AvatarService avatarService;
//
//    @Autowired
//    private EscolaridadeInitializer escolaridadeInitializer;

    @PostConstruct
    public void inicializarDados() {
        moduloService.inserirDadosIniciaisSeNecessario();
        temaService.inserirDadosIniciaisSeNecessario();
//        aulaService.inserirDadosIniciaisSeNecessario();
//        perguntaService.inserirDadosIniciaisSeNecessario();
//        respostaService.inserirDadosIniciaisSeNecessario();
//        avatarService.inserirDadosIniciaisSeNecessario();
//        escolaridadeInitializer.inserirDadosIniciaisSeNecessario();

    }

    public void inserirPerguntaResposta() {
//        perguntaService.inserirDadosIniciaisSeNecessario();
//        respostaService.inserirDadosIniciaisSeNecessario();
    }

}
