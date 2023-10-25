package br.edu.ifsul.cstsi_tads_2023.api.jogador;

import br.edu.ifsul.cstsi_tads_2023.CstsiTads2023Application;
import br.edu.ifsul.cstsi_tads_2023.api.infra.security.jwt.JwtUtil;
import org.junit.Before;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import static junit.framework.TestCase.assertNotNull;
import static org.springframework.http.HttpMethod.*;

@SpringBootTest(classes = CstsiTads2023Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class BaseAPITest {
    @Autowired
    protected TestRestTemplate rest;

    @Autowired
    @Qualifier("userDetailsService")
    protected UserDetailsService userDetailsService;

    private String jwtToken = "";


    HttpHeaders getHeaders() {
        UserDetails user = userDetailsService.loadUserByUsername("ana@email.com");
        assertNotNull(user);

        jwtToken = JwtUtil.createToken(user);
        System.out.println("jwtToken gerado:");
        System.out.println(jwtToken);
        assertNotNull(jwtToken);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + jwtToken);
        return headers;
    }

//    @Before
//    public void setupTest() {
//        System.out.println("setupTest:");
//        UserDetails user = userDetailsService.loadUserByUsername("ana@email.com");
//        assertNotNull(user);
//
//        jwtToken = JwtUtil.createToken(user);
//        System.out.println("jwtToken gerado:");
//        System.out.println(jwtToken);
//        assertNotNull(jwtToken);
//    }

    <T> ResponseEntity<T> post(String url, Object body, Class<T> responseType) {
        HttpHeaders headers = getHeaders();
        return rest.exchange(url, POST, new HttpEntity<>(body, headers), responseType);
    }

    <T> ResponseEntity<T> put(String url, Object body, Class<T> responseType) {
        HttpHeaders headers = getHeaders();

        return rest.exchange(url, PUT, new HttpEntity<>(body, headers), responseType);
    }

    <T> ResponseEntity<T> get(String url, Class<T> responseType) {
        HttpHeaders headers = getHeaders();

        return rest.exchange(url, GET, new HttpEntity<>(headers), responseType);
    }

    <T> ResponseEntity<T> delete(String url, Class<T> responseType) {
        HttpHeaders headers = getHeaders();

        return rest.exchange(url, DELETE, new HttpEntity<>(headers), responseType);
    }
}
