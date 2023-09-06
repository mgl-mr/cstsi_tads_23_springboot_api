package br.edu.ifsul.cstsi_tads_2023.api.match;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MatchService {
    @Autowired
    private MatchRepository rep;

    public List<MatchDTO> getMatches() {
        return rep.findAll()
            .stream()
            .map(MatchDTO::create)
            .collect(Collectors.toList());
    }

    public MatchDTO getMatchById(Long id) {
        Optional<Match> match = rep.findById(id);
        return match.map(MatchDTO::create).orElse(null);
    }

    public MatchDTO insert(Match match) {
        Assert.isNull(match.getId(), "Não foi possível inserir o registro.");
        return MatchDTO.create(rep.save(match));
    }

    public Boolean delete(Long id) {
        Optional<Match> optional = rep.findById(id);
        if (optional.isPresent()) {
            rep.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

}
