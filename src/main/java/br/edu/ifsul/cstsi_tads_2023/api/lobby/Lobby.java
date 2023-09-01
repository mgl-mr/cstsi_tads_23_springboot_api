package br.edu.ifsul.cstsi_tads_2023.api.lobby;

import br.edu.ifsul.cstsi_tads_2023.api.jogador.Jogador;
import br.edu.ifsul.cstsi_tads_2023.api.jogo.Jogo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "lobbys", schema = "gc_tads", catalog = "")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Lobby {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String nome;
    private String descricao;
    private Integer numJogadores;
    private Integer maxJogadores;
    private Byte convidar;

    @ManyToOne
    @JoinColumn(name = "id_jogo", referencedColumnName = "id", nullable = false)
    private Jogo jogo;

    @OneToOne
    @JoinColumn(name = "id_dono", referencedColumnName = "id", nullable = false)
    private Jogador jogador;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "lobby",
        joinColumns = @JoinColumn(name = "id_lobby"),
        inverseJoinColumns = @JoinColumn(name = "id_jogador")
    )
    Set<Jogador> jogadores;
}

