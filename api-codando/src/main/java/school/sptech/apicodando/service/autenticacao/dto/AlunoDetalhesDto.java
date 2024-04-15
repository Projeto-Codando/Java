package school.sptech.apicodando.service.autenticacao.dto;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import school.sptech.apicodando.entity.Aluno;

import java.util.Collection;

public class AlunoDetalhesDto implements UserDetails {



    private final String nome;
    private final String apelido;
    private final String senha;

    public AlunoDetalhesDto(Aluno aluno) {
        this.nome = aluno.getNome();
        this.apelido = aluno.getApelido();
        this.senha = aluno.getSenha();
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
        return null;
    }

    @Override
    public String getUsername() {
        return null;
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
