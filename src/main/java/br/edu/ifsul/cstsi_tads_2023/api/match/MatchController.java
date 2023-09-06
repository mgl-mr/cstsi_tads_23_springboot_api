package br.edu.ifsul.cstsi_tads_2023.api.match;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/v1/matches")
public class MatchController {
    @Autowired
    private MatchService service;

    @GetMapping
    public ResponseEntity<List<MatchDTO>> selectAll(){
        return ResponseEntity.ok(service.getMatches());
    }

    @GetMapping("{id}")
    public ResponseEntity<MatchDTO> selectById(@PathVariable("id") Long id) {
        MatchDTO m = service.getMatchById(id);
        return m != null ?
            ResponseEntity.ok(m) :
            ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<MatchDTO> insert(@RequestBody Match match) {
        MatchDTO m = service.insert(match);
        URI location = getUri(m.getId());
        return ResponseEntity.created(location).build();
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
