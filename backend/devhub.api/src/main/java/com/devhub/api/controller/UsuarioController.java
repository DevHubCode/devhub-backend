package com.devhub.api.controller;

import com.devhub.api.domain.contratante.ContratanteRepository;
import com.devhub.api.domain.freelancer.FreelancerRepository;
import com.devhub.api.domain.usuario.Usuario;
import com.devhub.api.infra.security.TokenJWTData;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import com.devhub.api.infra.security.TokenService;
@RestController
@RequestMapping("/login")
public class UsuarioController {

    @Autowired
    private ContratanteRepository contratanteRepository;
    @Autowired
    private FreelancerRepository freelancerRepository;
    @Autowired
    private AuthenticationManager manager;
    @Autowired
    private TokenService tokenService;
    @PostMapping
    public ResponseEntity login(@PathVariable String email, @PathVariable String senha) {
        var contaContratante = contratanteRepository.findByEmailAndSenha(email, senha);
        var contaFreelancer = freelancerRepository.findByEmailAndSenha(email, senha);
        if (contaContratante != null) {
            var authenticationToken = new UsernamePasswordAuthenticationToken(email, senha);
            var authentication = manager.authenticate(authenticationToken);
            var tokenJWT = tokenService.gerarToken((Usuario) authentication.getPrincipal());

            return ResponseEntity.ok(new TokenJWTData(tokenJWT));
        }

        if (contaFreelancer != null) {
            var authenticationToken = new UsernamePasswordAuthenticationToken(email, senha);
            var authentication = manager.authenticate(authenticationToken);
            var tokenJWT = tokenService.gerarToken((Usuario) authentication.getPrincipal());

            return ResponseEntity.ok(new TokenJWTData(tokenJWT));
        }

        return ResponseEntity.status(404).body("Email e/ou senha incorretos");
    }
}
