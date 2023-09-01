package br.edu.ifsul.cstsi_tads_2023.api.lobby;

import br.edu.ifsul.cstsi_tads_2023.api.jogo.Jogo;
import lombok.Data;
import org.modelmapper.ModelMapper;

@Data
public class LobbyDTO {
    private Long id;
    private String nome;
    private String descricao;
    private Integer numJogadores;
    private Integer maxJogadores;
    private Byte convidar;

    public static LobbyDTO create(Lobby l) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(l, LobbyDTO.class);
    }
}
