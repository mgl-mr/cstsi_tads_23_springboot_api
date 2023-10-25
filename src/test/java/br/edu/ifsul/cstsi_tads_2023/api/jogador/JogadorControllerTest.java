package br.edu.ifsul.cstsi_tads_2023.api.jogador;

import br.edu.ifsul.cstsi_tads_2023.CstsiTads2023Application;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CstsiTads2023Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class JogadorControllerTest extends BaseAPITest {

    private ResponseEntity<JogadorDTO> getJogador(String url) {
        return get(url, JogadorDTO.class);
    }

    private ResponseEntity<List<JogadorDTO>> getJogadores(String url) {
        HttpHeaders headers = getHeaders();

        return rest.exchange(
            url,
            HttpMethod.GET,
            new HttpEntity<>(headers),
            new ParameterizedTypeReference<List<JogadorDTO>>() {}
        );
    }

    @Test
    void selectAll() {
        List<JogadorDTO> jogadores = getJogadores("/api/v1/jogadores").getBody();
        assertNotNull(jogadores);
        assertEquals(2, jogadores.size());
    }

    @Test
    void selectById() {
        assertNotNull(getJogador("/api/v1/jogadores/1"));
        assertNotNull(getJogador("/api/v1/jogadores/2"));

        assertEquals(HttpStatus.NOT_FOUND, getJogador("/api/v1/jogadores/1000").getStatusCode());
    }


    @Test
    void insert() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}