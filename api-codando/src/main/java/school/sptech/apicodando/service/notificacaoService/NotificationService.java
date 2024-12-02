package school.sptech.apicodando.service.notificacaoService;

import com.google.firebase.messaging.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import school.sptech.apicodando.api.domain.aluno.Aluno;
import school.sptech.apicodando.api.domain.aluno.repository.AlunoRepository;
import school.sptech.apicodando.api.domain.turma.repository.TurmaRepository;

import java.util.List;

@Service
public class NotificationService {

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private TurmaRepository turmaRepository;

    public void sendNotificationToUser(String title, String body) {
        Notification notification = Notification.builder()
                .setTitle(title)
                .setBody(body)
                .build();

        Message message = Message.builder()
                .setNotification(notification)
                .setToken("e0JPX3z-RvaugEJLzp09pf:APA91bFcmepqQaqw6uX_iBgAy2oY0xxnrXOrjAWB1POcSvfw9bGBcpQw7y7FzmhNBUwr9XO8NEzESIwkQvUYtIHMogaLtE9XtOY8p1hw9JkQ_EPVQd5VPYQ")
                .build();

        try {
            String response = FirebaseMessaging.getInstance().send(message);
            System.out.println("Notificação enviada com sucesso: " + response);
        } catch (FirebaseMessagingException e) {
            e.printStackTrace();
            System.err.println("Erro ao enviar a notificação: " + e.getMessage());
        }
    }

    public void sendNotificationToClass(String title, String body, Integer idTurma) {

        System.out.println("Enviando notificação para a turma " + idTurma);

        List<Aluno> listaAlunos = alunoRepository.findAllByTurmaIdTurma(idTurma);
        List<String> tokens = listaAlunos.stream().map(Aluno::getFcmToken).toList();

        MulticastMessage message = MulticastMessage.builder()
                .setNotification(Notification.builder()
                        .setTitle(title)
                        .setBody(body)
                        .build())
                .addAllTokens(tokens)
                .build();
        
        try {
            BatchResponse response = FirebaseMessaging.getInstance().sendMulticast(message);
            System.out.println("Notificação enviada com sucesso para " + response.getSuccessCount() + " dispositivos.");
        } catch (FirebaseMessagingException e) {
            e.printStackTrace();
            System.err.println("Erro ao enviar a notificação: " + e.getMessage());
        }
    }
}