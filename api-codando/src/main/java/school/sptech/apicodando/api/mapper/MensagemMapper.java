package school.sptech.apicodando.api.mapper;

import lombok.Data;
import school.sptech.apicodando.api.domain.mensagem.Mensagem;
import school.sptech.apicodando.service.mensagemService.dto.MensagemCadastroDTO;
import school.sptech.apicodando.service.mensagemService.dto.MensagemListagemDTO;

@Data
public class MensagemMapper {

    public static MensagemListagemDTO toDto (Mensagem mensagem) {
        MensagemListagemDTO mensagemListagemDTO = new MensagemListagemDTO();
        mensagemListagemDTO.setIdMensagem(mensagem.getIdMensagem());
        mensagemListagemDTO.setMensagem(mensagem.getTexto());
        mensagemListagemDTO.setDataEnvio(mensagem.getDataEnvio().toString());
        mensagemListagemDTO.setEducador(mensagem.getEducador().getNome());
        mensagemListagemDTO.setMural(mensagem.getMural().toString());
        return mensagemListagemDTO;
    }

    public static Mensagem toEntity (MensagemCadastroDTO mensagemCadastroDTO) {
        Mensagem mensagem = new Mensagem();
        mensagem.setTexto(mensagemCadastroDTO.getTexto());
        return mensagem;
    }

}
