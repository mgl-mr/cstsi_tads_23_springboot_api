package br.edu.ifsul.cstsi_tads_2023.api.jogador;

import br.edu.ifsul.cstsi_tads_2023.api.jogo.Jogo;
import br.edu.ifsul.cstsi_tads_2023.api.lobby.Lobby;
import br.edu.ifsul.cstsi_tads_2023.api.match.Match;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.util.Set;

@Entity
@Table(name = "jogadores", schema = "gc_tads", catalog = "")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Jogador {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private Byte admin;
    private String nome;
    private String email;
    private String senha;
    private Date dataNasc;
    private String bio;
    private String urlFoto;
    private Time horarioInicio;
    private Time horarioFim;
    @OneToOne(mappedBy = "jogador")
    private Match match;
    @OneToOne(mappedBy = "jogador")
    private Lobby lobby;
    @ManyToMany
    @JoinTable(
        name = "jogador_jogo",
        joinColumns = @JoinColumn(name = "id_jogador"),
        inverseJoinColumns = @JoinColumn(name = "id_jogo")
    )
    Set<Jogo> jogos;
}
