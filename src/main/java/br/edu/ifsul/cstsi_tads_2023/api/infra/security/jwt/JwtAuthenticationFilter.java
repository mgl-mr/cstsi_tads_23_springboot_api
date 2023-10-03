package br.edu.ifsul.cstsi_tads_2023.api.infra.security.jwt;

import br.edu.ifsul.cstsi_tads_2023.api.jogador.Jogador;
import br.edu.ifsul.cstsi_tads_2023.api.jogador.JogadorDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.util.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    public static final String AUTH_URL = "/api/v1/login";

    private final AuthenticationManager authenticationManager;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
        setFilterProcessesUrl(AUTH_URL);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
        try {
            JwtLoginInput login = new ObjectMapper().readValue(request.getInputStream(), JwtLoginInput.class);

            String email = login.getEmail();
            String senha = login.getSenha();

            if (StringUtils.isEmpty(email) || StringUtils.isEmpty(senha)) {
                throw new BadCredentialsException("Invalid email/senha.");
            }

            Authentication auth = new UsernamePasswordAuthenticationToken(email, senha);

            return authenticationManager.authenticate(auth);
        } catch (IOException e) {
            throw new BadCredentialsException(e.getMessage());
        }
    }

    @Override
    protected void successfulAuthentication(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain,
            Authentication authentication
    ) throws IOException {
        Jogador jogador = (Jogador) authentication.getPrincipal();
        String jwtToken = JwtUtil.createToken(jogador);
        String json = JogadorDTO.create(jogador, jwtToken).toJson();

        ServletUtil.write(response, HttpStatus.OK, json);
    }

    protected void unsuccessfulAuthentication(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException error
    ) throws IOException, ServletException {
        String json = ServletUtil.getJson("error", "Login incorreto");
        ServletUtil.write(response, HttpStatus.UNAUTHORIZED, json);
    }

}
