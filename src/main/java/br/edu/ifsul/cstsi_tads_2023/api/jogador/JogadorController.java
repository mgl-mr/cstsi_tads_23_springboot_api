package br.edu.ifsul.cstsi_tads_2023.api.jogador;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/jogadores")
public class JogadorController {
    @GetMapping
    public ResponseEntity<String> selectAll() {
        return ResponseEntity.ok("selectAll()");
    }

    @GetMapping("{id}")
    public ResponseEntity<String> selectById(@PathVariable("id") Long id) {
        return ResponseEntity.ok("selectById() " + id);
    }

    @PostMapping
    public ResponseEntity<String> insert(@RequestBody Jogador jogador) {
        return ResponseEntity.ok("insert() " + jogador);
    }

    @PutMapping("{id}")
    public ResponseEntity<String> update(@PathVariable("id") Long id, @RequestBody Jogador jogador){
        return ResponseEntity.ok("update() " + id);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok("Delete() " + id);
    }

}