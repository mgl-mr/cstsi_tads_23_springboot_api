package br.edu.ifsul.cstsi_tads_2023.api.match;

import br.edu.ifsul.cstsi_tads_2023.api.jogador.Jogador;
import br.edu.ifsul.cstsi_tads_2023.api.jogo.Jogo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "matches", schema = "gc_tads", catalog = "")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Match {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String tipo;
    private String patente;
    @OneToOne
    @JoinColumn(name = "id_jogador", referencedColumnName = "id", nullable = false)
    private Jogador jogador;
    @OneToOne
    @JoinColumn(name = "id_jogo", referencedColumnName = "id", nullable = false)
    private Jogo jogo;
}
