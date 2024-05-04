package school.sptech.apicodando.service.educadorService.dto.dtoEducador;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import school.sptech.apicodando.domain.educador.Educador;

import java.util.Collection;

public class EducadorDetalhesDto implements UserDetails {



    private final String nome;
    private final String email;
    private final String senha;

    public EducadorDetalhesDto(Educador educador) {
        this.nome = educador.getNome();
        this.email = educador.getEmail();
        this.senha = educador.getSenha();
    }

    public String getNome() {
        return nome;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
