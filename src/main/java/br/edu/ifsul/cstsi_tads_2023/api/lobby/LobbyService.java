package br.edu.ifsul.cstsi_tads_2023.api.lobby;

import org.modelmapper.internal.util.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LobbyService {
    @Autowired
    private LobbyRepository rep;

    public List<LobbyDTO> getLobbys() {
        return rep.findAll()
            .stream()
            .map(LobbyDTO::create)
            .collect(Collectors.toList());
    }

    public LobbyDTO getlobbyById(Long id) {
        Optional<Lobby> lobby = rep.findById(id);
        return lobby.map(LobbyDTO::create).orElse(null);
    }

    public LobbyDTO insert(Lobby lobby) {
        Assert.isNull(lobby.getId(), "Não foi possível inserir o registro");
        Assert.isNull(lobby.getNumJogadores(), "Não foi possível atualizar o registro");

        lobby.setNumJogadores(1);
        return LobbyDTO.create(rep.save(lobby));
    }

    public LobbyDTO update(Lobby lobby, Long id){
        Assert.notNull(id, "Não foi possível atualizar o registro");

        Optional<Lobby> optional = rep.findById(id);
        if (optional.isPresent()) {
            Lobby db = optional.get();
            db.setNome(lobby.getNome());
            db.setDescricao(lobby.getDescricao());
            db.setConvidar(lobby.getConvidar());
            db.setMaxJogadores(lobby.getMaxJogadores());
            db.setNumJogadores(lobby.getNumJogadores());
            db.setJogo(lobby.getJogo());
            db.setJogador(lobby.getJogador());

            rep.save(db);

            return LobbyDTO.create(db);
        } else {
            return null;
        }
    }

    public boolean delete(Long id) {
        Optional<Lobby> optional = rep.findById(id);
        if (optional.isPresent()) {
            rep.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

}
