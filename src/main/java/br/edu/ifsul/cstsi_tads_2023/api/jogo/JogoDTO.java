package br.edu.ifsul.cstsi_tads_2023.api.jogo;

import lombok.Data;
import org.modelmapper.ModelMapper;

@Data
public class JogoDTO {
    private Long id;
    private String nome;
    private String urlFoto;

    public static JogoDTO create(Jogo j) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(j, JogoDTO.class);
    }
}
