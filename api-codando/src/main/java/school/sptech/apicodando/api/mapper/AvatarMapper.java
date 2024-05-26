package school.sptech.apicodando.api.mapper;

import school.sptech.apicodando.api.domain.avatar.Avatar;
import school.sptech.apicodando.service.avatarService.dto.AvatarListagemDTO;

import java.util.List;
import java.util.stream.Collectors;

public class AvatarMapper {

    public static AvatarListagemDTO toListagemDTO(Avatar avatar) {
        AvatarListagemDTO dto = new AvatarListagemDTO();
        dto.setId(avatar.getId());
        dto.setDescricao(avatar.getDescricao());
        dto.setPreço(avatar.getPreco());
        return dto;
    }

    public static Avatar toEntity(AvatarListagemDTO dto) {
        Avatar avatar = new Avatar();
        avatar.setId(dto.getId());
        avatar.setDescricao(dto.getDescricao());
        avatar.setPreco(dto.getPreço());
        return avatar;
    }

    public static List<AvatarListagemDTO> toListagem(List<Avatar> avatares) {
        return avatares.stream().map(AvatarMapper::toListagemDTO).collect(Collectors.toList());
    }



}
