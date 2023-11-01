package br.edu.ifsul.cstsi_tads_2023.api.jogador;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/v1/jogadores")
@Api(value = "Jogadores")
public class JogadorController {
    @Autowired
    private JogadorService service;

    @GetMapping
    @ApiOperation(value = "Retorna todos os jogadores cadastrados")
    public ResponseEntity<List<JogadorDTO>> selectAll() {
        return ResponseEntity.ok(service.getJogadores());
    }

    @GetMapping("{id}")
    @ApiOperation(value = "Retorna um jogador pelo campo identificador")
    public ResponseEntity<JogadorDTO> selectById(@PathVariable("id") Long id) {
        JogadorDTO j = service.getJogadorById(id);
        return j != null ?
            ResponseEntity.ok(j) :
            ResponseEntity.notFound().build();
    }

    @GetMapping("/info")
    @ApiOperation(value = "Retorna os dados do usuário logado")
    public JogadorDTO userInfo(@AuthenticationPrincipal Jogador jogador) {
        return JogadorDTO.create(jogador);
    }

    @PostMapping("/register")
    @ApiOperation(value = "Insere um novo jogador")
    public ResponseEntity<String> insert(@RequestBody Jogador jogador) {
        JogadorDTO j = service.insert(jogador);
        URI location = getUri(j.getId());
        return ResponseEntity.created(location).build();
    }

    @PutMapping("{id}")
    @ApiOperation(value = "Altera um jogador existente.")
    public ResponseEntity<JogadorDTO> update(@PathVariable("id") Long id, @RequestBody Jogador jogador){
        jogador.setId(id);
        JogadorDTO j = service.update(jogador, id);
        return  j != null ?
            ResponseEntity.ok(j) :
            ResponseEntity.notFound().build();
    }

    @DeleteMapping("{id}")
    @ApiOperation(value = "Deleta um jogador.")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        return service.delete(id) ?
            ResponseEntity.ok().build() :
            ResponseEntity.notFound().build();
    }
    //utilitário
    private URI getUri(Long id) {
        return ServletUriComponentsBuilder
            .fromCurrentRequest()
            .replacePath("/api/v1/jogadores/{id}")
            .buildAndExpand(id)
            .toUri();
    }
}