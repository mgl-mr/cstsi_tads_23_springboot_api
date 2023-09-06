package br.edu.ifsul.cstsi_tads_2023.api.jogador;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JogadorRepository extends JpaRepository<Jogador, Long> {
    Jogador findByEmail(String email);
}
