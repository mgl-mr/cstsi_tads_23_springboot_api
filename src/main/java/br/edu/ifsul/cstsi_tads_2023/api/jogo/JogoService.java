package br.edu.ifsul.cstsi_tads_2023.api.jogo;

import br.edu.ifsul.cstsi_tads_2023.api.jogador.Jogador;
import br.edu.ifsul.cstsi_tads_2023.api.jogador.JogadorDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JogoService {
    @Autowired
    private JogoRepository rep;

    public List<JogoDTO> getJogos() {
        return rep.findAll()
            .stream()
            .map(JogoDTO::create)
            .collect(Collectors.toList());
    }

    public JogoDTO getJogoById(Long id) {
        Optional<Jogo> jogo = rep.findById(id);
        return jogo.map(JogoDTO::create).orElse(null);
    }

    public JogoDTO insert(Jogo jogo) {
        Assert.isNull(jogo.getId(), "Não foi possivel inserir o registro");
        return JogoDTO.create(rep.save(jogo));
    }

    public JogoDTO update(Jogo jogo, Long id) {
        Assert.notNull(id, "Não foi possível atualizar o registro");

        Optional<Jogo> optional = rep.findById(id);
        if (optional.isPresent()) {
            Jogo db = optional.get();
            db.setNome(jogo.getNome());
            db.setUrlFoto(jogo.getUrlFoto());

            rep.save(db);

            return JogoDTO.create(db);
        } else {
            return null;
        }
    }

    public boolean delete(Long id) {
        Optional<Jogo> optional = rep.findById(id);
        if (optional.isPresent()) {
            rep.deleteById(id);
            return true;
        } else {
            return false;
        }

    }
}
