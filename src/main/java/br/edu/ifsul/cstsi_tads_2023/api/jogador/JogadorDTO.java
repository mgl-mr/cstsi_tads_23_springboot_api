package br.edu.ifsul.cstsi_tads_2023.api.jogador;

import lombok.Data;
import org.modelmapper.ModelMapper;

import java.sql.Date;
import java.sql.Time;

@Data
public class JogadorDTO {
    private Long id;
    private String nome;
    private Date dataNasc;
    private String bio;
    private String urlFoto;
    private Time horarioInicio;
    private Time horarioFim;

    public static JogadorDTO create(Jogador j) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(j, JogadorDTO.class);
    }

}
