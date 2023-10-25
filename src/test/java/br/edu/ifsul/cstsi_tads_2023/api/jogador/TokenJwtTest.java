package br.edu.ifsul.cstsi_tads_2023.api.jogador;

import br.edu.ifsul.cstsi_tads_2023.CstsiTads2023Application;
import br.edu.ifsul.cstsi_tads_2023.api.infra.security.jwt.JwtUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static junit.framework.TestCase.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CstsiTads2023Application.class)
public class TokenJwtTest {
    @Autowired
    @Qualifier("userDetailsService")
    private UserDetailsService userDetailsService;

    @Test
    public void testToken() {

        // Le usuário
        UserDetails user = userDetailsService.loadUserByUsername("ana@email.com");
        assertNotNull(user);
        System.out.println("User lido:");
        System.out.println(user);

        // Gera token
        String jwtToken = JwtUtil.createToken(user);
        System.out.println("jwtToken gerado:");
        System.out.println(jwtToken);
        assertNotNull(jwtToken);

        // Valida Token
        boolean ok = JwtUtil.isTokenValid(jwtToken);
        assertTrue(ok);

        // Valida login
        String login = JwtUtil.getLogin(jwtToken);
        assertEquals("ana@email.com",login);

        // Valida roles
        List<GrantedAuthority> roles = JwtUtil.getRoles(jwtToken);
        assertNotNull(roles);
        System.out.println("roles:");
        System.out.println(roles);
        String role = roles.get(0).getAuthority();
        assertEquals(role,"ROLE_ADMIN");
    }

}
