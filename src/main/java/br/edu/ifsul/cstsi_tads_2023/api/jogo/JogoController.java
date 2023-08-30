package br.edu.ifsul.cstsi_tads_2023.api.jogo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/v1/jogos")
public class JogoController {
    @Autowired
    private JogoService service;

    @GetMapping
    public ResponseEntity<List<JogoDTO>> selectAll() {
        return ResponseEntity.ok(service.getJogos());
    }

    @GetMapping("{id}")
    public ResponseEntity<JogoDTO> selectById(@PathVariable("id") Long id) {
        JogoDTO j = service.getJogoById(id);
        return j != null ?
            ResponseEntity.ok(j) :
            ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<JogoDTO> insert(@RequestBody Jogo jogo) {
        JogoDTO j = service.insert(jogo);
        URI location = getUri(j.getId());
        return ResponseEntity.created(location).build();
    }

    @PutMapping("{id}")
    public ResponseEntity<JogoDTO> update(@PathVariable("id") Long id, @RequestBody Jogo jogo) {
        jogo.setId(id);
        JogoDTO j = service.update(jogo, id);
        return j != null ?
            ResponseEntity.ok(j) :
            ResponseEntity.notFound().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        return service.delete(id) ?
            ResponseEntity.ok().build() :
            ResponseEntity.notFound().build();
    }

    // utilitário
    private URI getUri(Long id) {
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
    }
}
