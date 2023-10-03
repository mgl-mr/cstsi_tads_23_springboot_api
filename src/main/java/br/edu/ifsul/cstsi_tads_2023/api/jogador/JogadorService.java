package br.edu.ifsul.cstsi_tads_2023.api.jogador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JogadorService {
    @Autowired
    private JogadorRepository rep;

    @Autowired
    private RoleRepository roleRep;

    @Autowired
    private BCryptPasswordEncoder encoder;

    public List<JogadorDTO> getJogadores() {
        return rep.findAll()
            .stream()
            .map(JogadorDTO::create)
            .collect(Collectors.toList());
    }

    public JogadorDTO getJogadorById(Long id) {
        Optional<Jogador> jogador = rep.findById(id);
        return jogador.map(JogadorDTO::create).orElse(null);
    }

    @Transactional
    public JogadorDTO insert(Jogador jogador) {
        Assert.isNull(jogador.getId(), "Não foi possível inserir o registro");
        jogador.setSenha(encoder.encode(jogador.getSenha()));
        System.out.println(jogador);
        jogador.setRoles(Arrays.asList(new Role(1L, "USER")));
        return JogadorDTO.create(rep.save(jogador));
    }

    public JogadorDTO update(Jogador jogador, Long id) {
        Assert.notNull(id,"Não foi possível atualizar o registro");

        Optional<Jogador> optional = rep.findById(id);
        if (optional.isPresent()) {
            Jogador db = optional.get();


            db.setNome(jogador.getNome());
            db.setEmail(jogador.getEmail());
            db.setSenha(encoder.encode(jogador.getSenha()));
            db.setDataNasc(jogador.getDataNasc());
            db.setBio(jogador.getBio());
            db.setUrlFoto(jogador.getUrlFoto());
            db.setHorarioInicio(jogador.getHorarioInicio());
            db.setHorarioFim(jogador.getHorarioFim());

            rep.save(db);

            return JogadorDTO.create(db);
        } else {
            return null;
        }
    }

    public boolean delete(Long id) {
        Optional<Jogador> optional = rep.findById(id);
        if (optional.isPresent()) {
            rep.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
