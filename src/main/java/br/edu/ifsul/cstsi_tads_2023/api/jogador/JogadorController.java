package br.edu.ifsul.cstsi_tads_2023.api.jogador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/v1/jogadores")
public class JogadorController {
    @Autowired
    private JogadorService service;

    @GetMapping
    public ResponseEntity<List<JogadorDTO>> selectAll() {
        return ResponseEntity.ok(service.getJogadores());
    }

    @GetMapping("{id}")
    public ResponseEntity<JogadorDTO> selectById(@PathVariable("id") Long id) {
        JogadorDTO j = service.getJogadorById(id);
        return j != null ?
            ResponseEntity.ok(j) :
            ResponseEntity.notFound().build();
    }

    @GetMapping("/info")
    public JogadorDTO userInfo(@AuthenticationPrincipal Jogador jogador) {
        return JogadorDTO.create(jogador);
    }

    @PostMapping("/register")
    public JogadorDTO insert(@RequestBody Jogador jogador) {
        return service.insert(jogador);
    }

    @PutMapping("{id}")
    public ResponseEntity<JogadorDTO> update(@PathVariable("id") Long id, @RequestBody Jogador jogador){
        jogador.setId(id);
        JogadorDTO j = service.update(jogador, id);
        return  j != null ?
            ResponseEntity.ok(j) :
            ResponseEntity.notFound().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        return service.delete(id) ?
            ResponseEntity.ok().build() :
            ResponseEntity.notFound().build();
    }
    //utilitário
    private URI getUri(Long id) {
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
    }
}