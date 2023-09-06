package br.edu.ifsul.cstsi_tads_2023.api.match;

import lombok.Data;
import org.modelmapper.ModelMapper;

@Data
public class MatchDTO {
    private Long id;
    private String tipo;
    private String patente;

    public static MatchDTO create(Match m) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(m, MatchDTO.class);
    }
}
