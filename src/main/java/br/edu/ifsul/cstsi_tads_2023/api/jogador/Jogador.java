package br.edu.ifsul.cstsi_tads_2023.api.jogador;

import br.edu.ifsul.cstsi_tads_2023.api.jogo.Jogo;
import br.edu.ifsul.cstsi_tads_2023.api.lobby.Lobby;
import br.edu.ifsul.cstsi_tads_2023.api.match.Match;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.sql.Date;
import java.sql.Time;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "jogadores", schema = "gc_tads", catalog = "")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Jogador implements UserDetails {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    @NotBlank(message = "O nome não pode ser nulo ou vazio")
    @Size(min = 3, max = 30, message = "Tamanho mínimo de 3 e máximo de 30")
    private String nome;
    @NotBlank(message = "O email não pode ser nulo ou vazio")
    @Email(message = "O email deve estar em um formato válido")
    private String email;
    @NotBlank(message = "A senha não pode ser nula ou vazia")
    private String senha;
    @NotNull(message = "A data de nascimento não deve ser nula")
    @Past(message = "A data de nascimento deve estar no passado")
    private Date dataNasc;
    @Size(max = 255, message = "Descrição deve ter no máximo 255 caracteres")
    private String bio;
    private String urlFoto;
    @NotNull(message = "O horário inicial não deve ser nulo")
    private Time horarioInicio;
    @NotNull(message = "O horário final deve ser nulo")
    private Time horarioFim;

    @OneToOne(mappedBy = "jogador")
    private Match match;

    @OneToOne(mappedBy = "jogador")
    private Lobby lobby;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "jogador_jogo",
        joinColumns = @JoinColumn(name = "id_jogador"),
        inverseJoinColumns = @JoinColumn(name = "id_jogo")
    )
    Set<Jogo> jogos;

    @ManyToMany(mappedBy = "jogadores")
    Set<Lobby> lobbys;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "jogador_roles",
        joinColumns = @JoinColumn(name = "id_jogador", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "id_role", referencedColumnName = "id")
    )
    private List<Role> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
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
