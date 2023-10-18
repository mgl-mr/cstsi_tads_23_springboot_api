package br.edu.ifsul.cstsi_tads_2023.api.jogador;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class JogadorServiceTest {
    @Autowired
    private JogadorService service;

    @Test
    void getJogadores() {
        List<JogadorDTO> jogadores = service.getJogadores();
        assertEquals(2, jogadores.size());
    }

    @Test
    void getJogadorById() {
        JogadorDTO jogador = service.getJogadorById(1L);
        assertNotNull(jogador);
        assertEquals("Ana", jogador.getNome());
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

        JogadorDTO j = service.insert(jogador);

        assertNotNull(j);

        Long id = j.getId();
        assertNotNull(id);

        j = service.getJogadorById(id);
        assertNotNull(j);

        assertEquals("Carl", j.getNome());
        assertEquals("carl@email.com", j.getEmail());
        assertEquals(Date.valueOf("2010-05-20"), j.getDataNasc());
        assertEquals("Apenas um Carl", j.getBio());
        assertEquals("https://carl.png", j.getUrlFoto());
        assertEquals(Time.valueOf("10:00:00"), j.getHorarioInicio());
        assertEquals(Time.valueOf("12:00:00"), j.getHorarioFim());

        service.delete(id);

        if(service.getJogadorById(id) != null){
            fail("O jogador não foi excluído");
        }
    }

    @Test
    void update() {
        JogadorDTO jDTO = service.getJogadorById(1l);
        String nome = jDTO.getNome();
        jDTO.setNome("Ana Skywalker");

        ModelMapper modelMapper = new ModelMapper();
        Jogador j = modelMapper.map(jDTO, Jogador.class);
        j.setSenha("Teste123");

        jDTO = service.update(j, j.getId());
        assertNotNull(jDTO);
        assertEquals("Ana Skywalker", jDTO.getNome());

        j.setNome(nome);
        jDTO = service.update(j, j.getId());
        assertNotNull(jDTO);
    }

    @Test
    void delete() {
        Jogador jogador = new Jogador();
        jogador.setNome("Carl");
        jogador.setEmail("carl@email.com");
        jogador.setSenha("Teste123");
        jogador.setDataNasc(Date.valueOf("2010-05-20"));
        jogador.setBio("Apenas um Carl");
        jogador.setUrlFoto("https://carl.png");
        jogador.setHorarioInicio(Time.valueOf("10:00:00"));
        jogador.setHorarioFim(Time.valueOf("12:00:00"));

        JogadorDTO j = service.insert(jogador);

        assertNotNull(j);

        Long id = j.getId();
        assertNotNull(id);
        j = service.getJogadorById(id);
        assertNotNull(j);

        service.delete(id);

        if(service.getJogadorById(id) != null){
            fail("O jogador não foi excluído");
        }
    }
}