package br.edu.ifsul.cstsi_tads_2023.api.infra.security;

import br.edu.ifsul.cstsi_tads_2023.api.jogador.JogadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service(value = "userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private JogadorRepository userRep;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return  userRep.findByEmail(username);
    }

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//        if (username.equals("user")) {
//            return User.withUsername(username).password(encoder.encode("123")).roles("USER").build();
//        } else if (username.equals("admin")) {
//            return User.withUsername(username).password(encoder.encode("123")).roles("USER", "ADMIN").build();
//        }
//
//        throw new UsernameNotFoundException("Usuario inexistente.");
//    }
}
