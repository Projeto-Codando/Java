package school.sptech.apicodando.api.mapper;

import lombok.Data;
import school.sptech.apicodando.api.domain.mensagem.Mensagem;
import school.sptech.apicodando.service.mensagemService.dto.MensagemCadastroDTO;
import school.sptech.apicodando.service.mensagemService.dto.MensagemListagemDTO;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class MensagemMapper {

    public static MensagemListagemDTO toDto (Mensagem mensagem) {
        MensagemListagemDTO mensagemListagemDTO = new MensagemListagemDTO();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDateTime = mensagem.getDataEnvio().format(formatter);

        mensagemListagemDTO.setIdMensagem(mensagem.getIdMensagem());
        mensagemListagemDTO.setMensagem(mensagem.getTexto());
        mensagemListagemDTO.setDataEnvio(formattedDateTime);
        mensagemListagemDTO.setIdTurma(mensagem.getTurma().getIdTurma());
        // mensagemListagemDTO.setMural(mensagem.getTurma().toString());
        return mensagemListagemDTO;
    }

    public static Mensagem toEntity (MensagemCadastroDTO mensagemCadastroDTO) {
        Mensagem mensagem = new Mensagem();
        mensagem.setTexto(mensagemCadastroDTO.getTexto());
        return mensagem;
    }

    public static List<MensagemListagemDTO> toDto (List<Mensagem> mensagens) {
        return mensagens.stream().map(MensagemMapper::toDto).collect(Collectors.toList());
    }

}
