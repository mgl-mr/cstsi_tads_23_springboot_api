package br.edu.ifsul.cstsi_tads_2023.api.infra.security.jwt;

import lombok.Data;

@Data
public class JwtLoginInput {
    private String email;
    private String senha;
}
