package school.sptech.apicodando.service.notificacaoService;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.MulticastMessage;
import com.google.firebase.messaging.FirebaseMessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import school.sptech.apicodando.api.domain.aluno.repository.AlunoRepository;

import java.util.List;

@Service
public class NotificationService {

    @Autowired
    private AlunoRepository repository; // Assumindo que você tenha um repositório para buscar tokens dos usuários

    /**
     * Envia uma notificação para todos os alunos de uma turma específica.
     *
     * @param title   Título da notificação
     * @param body    Conteúdo da notificação
     * @param turmaId ID da turma para buscar os alunos
     */
    public void sendNotificationToTurma(String title, String body, Integer turmaId) {
        // Busca todos os tokens de notificação dos alunos da turma
        List<String> tokens = repository.findTokensByTurmaId(turmaId);

        if (tokens.isEmpty()) {
            System.out.println("Nenhum token encontrado para essa turma.");
            return;
        }

        // Cria a mensagem multicast com o título e conteúdo da notificação
        MulticastMessage message = MulticastMessage.builder()
                .putData("title", title)
                .putData("body", body)
                .addAllTokens(tokens)
                .build();

        try {
            // Envia a mensagem multicast para os dispositivos
            FirebaseMessaging.getInstance().sendMulticast(message);
            System.out.println("Notificação enviada com sucesso para os alunos da turma " + turmaId);
        } catch (FirebaseMessagingException e) {
            e.printStackTrace();
            System.err.println("Erro ao enviar a notificação: " + e.getMessage());
        }
    }

    /**
     * Envia uma notificação para um único usuário, caso seja necessário.
     *
     * @param title   Título da notificação
     * @param body    Conteúdo da notificação
     * @param token   Token do dispositivo para onde enviar a notificação
     */
    public void sendNotificationToUser(String title, String body, String token) {
        // Cria uma mensagem simples com título e conteúdo
        Message message = Message.builder()
                .putData("title", title)
                .putData("body", body)
                .setToken(token)
                .build();

        try {
            // Envia a mensagem para o token especificado
            FirebaseMessaging.getInstance().send(message);
            System.out.println("Notificação enviada com sucesso para o usuário.");
        } catch (FirebaseMessagingException e) {
            e.printStackTrace();
            System.err.println("Erro ao enviar a notificação: " + e.getMessage());
        }
    }
}
