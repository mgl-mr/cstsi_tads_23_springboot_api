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
        Jogador jogador = new Jogador();
        jogador.setNome("Carl");
        jogador.setEmail("carl@email.com");
        jogador.setSenha("Teste123");
        jogador.setDataNasc(Date.valueOf("2010-05-20"));
        jogador.setBio("Apenas um Carl");
        jogador.setUrlFoto("https://carl.png");
        jogador.setHorarioInicio(Time.valueOf("10:00:00"));
        jogador.setHorarioFim(Time.valueOf("12:00:00"));

        ResponseEntity response = post("/api/v1/jogadores/register", jogador, null);
        System.out.println("response:");
        System.out.println(response);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());

        String location = response.getHeaders().get("location").get(0);
        JogadorDTO j = getJogador(location).getBody();


        assertNotNull(j);
        assertEquals("Carl", j.getNome());

        delete(location, null);

        assertEquals(HttpStatus.NOT_FOUND, getJogador(location).getStatusCode());
    }

    @Test
    void update() {
        Jogador jogador = new Jogador();
        jogador.setNome("Carl");
        jogador.setEmail("carl@email.com");
        jogador.setSenha("Teste123");
        jogador.setDataNasc(Date.valueOf("2010-05-20"));
        jogador.setBio("Apenas um Carl");
        jogador.setUrlFoto("https://carl.png");
        jogador.setHorarioInicio(Time.valueOf("10:00:00"));
        jogador.setHorarioFim(Time.valueOf("12:00:00"));

        ResponseEntity response = post("/api/v1/jogadores/register", jogador, null);
        System.out.println("post response:");
        System.out.println(response);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());

        String location = response.getHeaders().get("location").get(0);
        System.out.println("location:");
        System.out.println(location);
        JogadorDTO j = getJogador(location).getBody();
        System.out.println("JogadorDTO j:");
        System.out.println(j);

        assertNotNull(j);
        assertEquals("Carl", j.getNome());

        Jogador ja = new Jogador();
        ja.setNome("Carlitos");
        ja.setEmail(j.getEmail());
        ja.setSenha("Teste123");
        ja.setDataNasc(j.getDataNasc());
        ja.setBio(j.getBio());
        ja.setUrlFoto(j.getUrlFoto());
        ja.setHorarioInicio(j.getHorarioInicio());
        ja.setHorarioFim(j.getHorarioFim());

        response = put("/api/v1/jogadores/" + j.getId(), ja, null);
        System.out.println("put response");
        System.out.println(response);
        assertEquals("Carlitos", ja.getNome());

        delete(location, null);

        assertEquals(HttpStatus.NOT_FOUND, getJogador(location).getStatusCode());
    }

    @Test
    void delete() {
    }
}