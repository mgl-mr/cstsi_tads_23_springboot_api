package br.edu.ifsul.cstsi_tads_2023.api.jogador;

import lombok.Data;
import org.modelmapper.ModelMapper;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class JogadorDTO {
    private Long id;
    private String nome;
    private Date dataNasc;
    private String bio;
    private String urlFoto;
    private Time horarioInicio;
    private Time horarioFim;

    private List<String> roles;

    public static JogadorDTO create(Jogador j) {
        ModelMapper modelMapper = new ModelMapper();
        JogadorDTO dto = modelMapper.map(j, JogadorDTO.class);

        dto.roles = j.getRoles()
            .stream()
            .map(Role::getNome)
            .collect(Collectors.toList());

        return dto;
    }

}
