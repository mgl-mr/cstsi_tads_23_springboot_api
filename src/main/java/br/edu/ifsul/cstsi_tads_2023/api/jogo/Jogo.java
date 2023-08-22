package br.edu.ifsul.cstsi_tads_2023.api.jogo;

import br.edu.ifsul.cstsi_tads_2023.api.lobby.Lobby;
import br.edu.ifsul.cstsi_tads_2023.api.match.Match;
import br.edu.ifsul.cstsi_tads_2023.api.jogador.Jogador;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "jogos", schema = "gc_tads", catalog = "")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Jogo {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String nome;
    private String urlFoto;
    @OneToOne(mappedBy = "jogo")
    private Match match;
    @OneToMany(mappedBy = "jogo")
    private Collection<Lobby> lobbys;
    @ManyToMany(mappedBy = "jogos")
    Set<Jogador> jogadores;
}
