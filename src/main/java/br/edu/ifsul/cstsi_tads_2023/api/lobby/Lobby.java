package br.edu.ifsul.cstsi_tads_2023.api.lobby;

import br.edu.ifsul.cstsi_tads_2023.api.jogador.Jogador;
import br.edu.ifsul.cstsi_tads_2023.api.jogo.Jogo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "lobbys", schema = "gc_tads", catalog = "")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Lobby {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private Integer numJogadores;
    private Integer maxJogadores;
    private Byte convidar;
    private Byte privacidade;
    @ManyToOne
    @JoinColumn(name = "id_jogo", referencedColumnName = "id", nullable = false)
    private Jogo jogo;
    @OneToOne
    @JoinColumn(name = "id_dono", referencedColumnName = "id", nullable = false)
    private Jogador jogador;

}

