package br.edu.ifsul.cstsi_tads_2023.api.lobby;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/v1/lobbys")
public class LobbyController {
    @Autowired
    private LobbyService service;

    @GetMapping
    public ResponseEntity<List<LobbyDTO>> selectAll() {
        return ResponseEntity.ok(service.getLobbys());
    }

    @GetMapping("{id}")
    public ResponseEntity<LobbyDTO> selectById(@PathVariable("id") Long id) {
        LobbyDTO l = service.getlobbyById(id);
        return l != null ?
            ResponseEntity.ok(l) :
            ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<LobbyDTO> insert(@RequestBody Lobby lobby) {
        LobbyDTO j = service.insert(lobby);
        URI location = getUri(lobby.getId());
        return ResponseEntity.created(location).build();
    }

    @PutMapping("{id}")
    public ResponseEntity<LobbyDTO> update(@PathVariable("id") Long id, @RequestBody Lobby lobby) {
        lobby.setId(id);
        LobbyDTO l = service.update(lobby, id);
        return l != null ?
            ResponseEntity.ok(l) :
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
